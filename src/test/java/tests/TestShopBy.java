package tests;

import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.AuthorizationFormPage;
import pages.MainPage;
import returner.ReturnBrowserNameFromConfig;
import returner.ReturnUrlTestSiteFromConfig;
import utils.*;
import pages.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class TestShopBy {

    private WebDriver driver;

   private static int timeForImplicitlyWait=25;
   private static String browserName=new ReturnBrowserNameFromConfig().returnBrowserName();
   private static String urlPath=new ReturnUrlTestSiteFromConfig().returnTestSite();


    @BeforeTest
    public void setUp() {
        String urlPath=new ReturnUrlTestSiteFromConfig().returnTestSite();
        String browserName=new ReturnBrowserNameFromConfig().returnBrowserName();
        driver=BrowserSingleton.getInstanceDriwer();
        System.out.format("Запустили браузер %s.  %n", browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeForImplicitlyWait, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
       System.out.format("Закрыли браузер %s.  %n", browserName);
       driver.close();

    }

    @Test
    public void testShopBy() {
        MainPage mainPage=new MainPage();
        //Тестируем открыватся ли главная страница нужного нам сайта
        String titleMainPage=mainPage.title();
        String expectedTitleMainPage=mainPage.expectedTitle();
        System.out.format("Тест на октрытие главной страницы сайта %s  %n", urlPath);
        assertEquals(titleMainPage, expectedTitleMainPage, "Главная страница не открылась.");
        //Кликаем по кнопке, чтобы появилась форма авторизации
        mainPage.clickButtonEntry();
        AuthorizationFormPage authorizationFormPage=new AuthorizationFormPage();

        //Авторизируемся и заходим на главную страницу под своим псевдонимом
        authorizationFormPage.recordAndClickAuthorizationForm();

        MainAfterAutorizationPage mainAfterAutorizationPag=new MainAfterAutorizationPage();
        //Проверяем, прошла ли авторизации
        String nickName=mainAfterAutorizationPag.autorizationCheck();
        String expectedNickName=MainAfterAutorizationPage.getNickName();
        System.out.format("Тест результатов авторизации под псевдонимом %s %n", expectedNickName);
        assertEquals(nickName, expectedNickName,  "Авторизация не прошла" );

        //Переходим в случайный раздел каталога
        mainAfterAutorizationPag.randomDirectoryCatalog();

        //Проверяем корректное открытие каталога
        String expectedNameOfCatalog=mainAfterAutorizationPag.getExpecteNameOfCatalog();
        RandomDirectoryCatalogPage randomDirectoryCatalog=new RandomDirectoryCatalogPage();
        String nameOfCatalog=randomDirectoryCatalog.nameOfCatalog();
        System.out.format("Тест открытия корректного раздела каталога \"%s\" %n", expectedNameOfCatalog);
        assertEquals(nameOfCatalog, expectedNameOfCatalog);

        //Переходим на главную страницу
        randomDirectoryCatalog.goToMainAfterAutorizationPage();
        //Извлекаем товары, на которые есть отзывы на главной странице  и помещаем их в файл data.csv в папку target
        mainAfterAutorizationPag.sendFeedbackCsv();

        //Открываем акаунт меню
        mainAfterAutorizationPag.openAccountMenu();
        //Нажимаем кнопку выход
        AccountMenuPage accountMenu=new AccountMenuPage();
        accountMenu.exitFromAccount();

        MainPage mainPage1=new MainPage();
        //Проверяем выход из аккаунта
        System.out.format("Тест выхода из аккаунта %s  %n", expectedNickName);
        String expectedExitKeyword=mainPage1.getExpExitKeyword();
        String exitKeyword=mainPage1.exitKeywordForCheck();
        assertEquals(exitKeyword, expectedExitKeyword, "Выход из аккаунта выполнен не корректно");
    }
}
