package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import returner.*;
import utils.*;

import org.openqa.selenium.WebDriver;

/*Класс загрружает главную страницу тестируемого сайта, инициализирует необходимые для работы с ней переменные,
осуществает необходимую для задач тестирования работу с этой страницей
 */
public class MainPage {

    //Во время прогонов иногда не подгружалась область запуска формы авторизации, поэтому вайтер такой не маленький
    private static int timeForExplicitWait =40;

    //Название Тега, содержащего Title главной страницы
    private static String tagNameTitle="titlepage";
    //Значение атрибута id тега, содержащего Title главной страницы
    private static String attributeIdValueTitle ="titlemainpage";
    //Название Тега, содержащее ключевое слово для проверки выхода из аккаунта
    private static String tagNameExitKeyword ="keyword";
    //Значение атрибута тега, содержащего ключевое слов для проверки выхода из аккаунта
    private static String attributeIdValueExitKeyword ="exitkeyword";

    //Url главной страницы сайта
    private static String urlPath=new ReturnUrlTestSiteFromConfig().returnTestSite();
    //Title главной страницы для проверки
    private static String expTitle = new ReaderContentFromConfigXML().readTagContent(tagNameTitle, attributeIdValueTitle);
    //Ключевое слово для провеки выхода из аккаунта
    private static String expExitKeyword = new ReaderContentFromConfigXML().readTagContent(tagNameExitKeyword, attributeIdValueExitKeyword);

    private WebDriver driver;

    private By seletctorButtonEntry=selectorExitKeyword =By.xpath("//descendant::*[@id='Header__Authentication']//span[@class='chzn-txt-sel']");
    private By selectorExitKeyword =By.xpath("//descendant::*[@id='Header__Authentication']//span[@class='chzn-txt-sel']");

    public MainPage() {
        driver = new BrowserSingleton().getInstanceDriwer();
        driver.get(urlPath);
    }

    /*Метод находит на главной странице кнопку "Вход", необходимую для открытия формы авторизации на этом сайте*/
    public void clickButtonEntry(){
        WebElement buttonEntry=(new WebDriverWait(driver, timeForExplicitWait))
                .until(ExpectedConditions.presenceOfElementLocated(seletctorButtonEntry));
        buttonEntry.click();
    }

    public String title() {
        new WebDriverWait(driver, timeForExplicitWait).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
        return driver.getTitle();
    }

    public String expectedTitle(){return expTitle;};

    public String getExpExitKeyword() {
        return expExitKeyword;
    }

    //Метод извлекает ключевое слово для провеки выхода из аккаунта
    public String exitKeywordForCheck() {
        WebElement exitKeyword =(new WebDriverWait(driver, timeForExplicitWait))
                .until(ExpectedConditions.presenceOfElementLocated(selectorExitKeyword));
        return exitKeyword.getText();
    }
}