package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserSingleton;
import utils.CreateAndWriteCSV;
import utils.ReaderContentFromConfigXML;
import utils.ReturnListSubstrsFromStrRegExp;

import java.util.ArrayList;
import java.util.List;

/*Класс загрружает главную страницу тестируемого сайта после процедуры авторизации, инициализирует необходимые
для работы с ней переменные, осуществает необходимую для задач тестирования работу с этой страницей
 */

public class MainAfterAutorizationPage {

    private static int timeForExplicitWait =40;

    //Название тега и значение атрибута id в конфигурационном файле содержащего псевдоним для проверки авторизции
    private static String tagNameNickName = "nickname";
    private static String attributeIdValueNickName = "nickname";
    /*Название тега содержащего верхнее и нижнее регулярное выражение для выбора
      подстроки с отзывами покупателей из строки c версткой страницы;
      подстроки с отзывом покупателя из строки с отзывами покупателей*/
    private static String tagNamePattern="pattern";
    /*Значение атрибута id тега содержащего верхнее регулярное выражение для выбора подстроки с
     отзывами покупателей из строки c версткой страницы*/
    private static String attributeIdValueСustomerFeedback="customerfeedback";
    /*Значение атрибута id тега содержащего нижнее регулярное выражение для выбора подстроки с
     отзывами покупателей из строки c версткой страницы*/
    private static String attributeIdValueWatchingNow="watchingnow";
    /*Значение атрибута id тега содержащего верхнее регулярное выражениие для выбора подстроки с
     отзывом покупателя из строки c отзывами покупателей*/
    private static String attributeIdValueBeforeFeedback="beforefeedback";
    /*Значение атрибута id тега содержащего нижнее регулярное выражениие для выбора подстроки с
    отзывом покупателя из строки c отзывами покупателей*/
    private static String attributeIdValueAfterFeedback="afterfeedback";
    /*Название тега и значение атрибута id в конфигурационном файле пути файла csv с результатом товарнов,
     на которые есть отзывы на главной странице*/
    private static String tagNamePathFeedbackCsv = "pathresult";
    private static String attributeIdPathFeedbackCsv = "pathfeedbaackcsv";

    //Псевдоним из конфигурационного файла
    private static String nickName = new ReaderContentFromConfigXML().readTagContent(tagNameNickName, attributeIdValueNickName);
    //Верхнее регулярное выражение из кофигурациооного файла для выбора подстроки с отзывами покупателей из строки c версткой страницы
    private static String customerFeedback = new ReaderContentFromConfigXML().readTagContent(tagNamePattern, attributeIdValueСustomerFeedback);
    //Нижнее регулярное выражение из кофигурациооного файла для выбора подстроки с отзывами покупателей из строки c версткой страницы
    private static String watchingNow = new ReaderContentFromConfigXML().readTagContent(tagNamePattern, attributeIdValueWatchingNow);
    /*Верхнее регулярное выражение из кофигурациооного файла для выбора подстроки с отзывом покупателя из строки с отзыывами покупателей
    Должно быть так:     NameBlockModel\">\n.+?desc\">
    */
    private static String beforeFeedback = new ReaderContentFromConfigXML().readTagContent(tagNamePattern, attributeIdValueBeforeFeedback);
    /*Нижнее регулярное выражение из кофигурациооного файла для выбора подстроки с отзывом покупателя из строки с отзыывами покупателей
    Должно быть так:   </a><a href(\\d|\\D)+?AuthorBlockModel
     */
    private static String afterFeedback = new ReaderContentFromConfigXML().readTagContent(tagNamePattern, attributeIdValueAfterFeedback);
    //Путь к файлу csv с результатом товарнов, на которые есть отзывы на главной странице
    private static String pathFeedbackCsv = new ReaderContentFromConfigXML().readTagContent(tagNamePathFeedbackCsv, attributeIdPathFeedbackCsv);


    private WebDriver driver;

    private String expecteNameOfCatalog;

    private By selectorAuthorizationCheck = By.cssSelector("#Header__Authentication  div.Header__BlockNameUser");
    private By selectorCatalog = By.xpath("//*[@id='Catalog']//span[@class='Catalog__LinkIcon']/following-sibling::" +
            "span[@class='Catalog__LinkText Catalog__LinkHaveChild']/span[@class='Catalog__LinkTextBlock']");

    public MainAfterAutorizationPage() {
        driver = BrowserSingleton.getInstanceDriwer();
    }

    public static String getNickName() {
        return nickName;
    }

    public String getExpecteNameOfCatalog() {
        return expecteNameOfCatalog;
    }

    /*Метод возвращает текстовое значение имени пользователь, необходимое для проверки авторизации*/
    public String autorizationCheck() {
        WebElement blockNameUser = (new WebDriverWait(driver, timeForExplicitWait))
                .until(ExpectedConditions.presenceOfElementLocated(selectorAuthorizationCheck));
        return blockNameUser.getText();
    }

    /*Метод получает список всех разделов каталога и переходит в случайный из них*/
    public void randomDirectoryCatalog(){
        List<WebElement> varCatalogList=driver.findElements(selectorCatalog);
        int i =(int) (Math.random() * varCatalogList.size());
        WebElement randomDirectory=varCatalogList.get(i);
        expecteNameOfCatalog =randomDirectory.getText();
        System.out.format("Переходим в каталог \"%s\", выбранный случайным образом.%n", expecteNameOfCatalog);
        randomDirectory.click();
    }

    /*Метод с помощью регулярныйх вражаний извлекает товары, на которые есть отзывы на главной странице из верстки.*/
    private ArrayList<String> findCustomerFeedback(){
        new WebDriverWait(driver, timeForExplicitWait).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
        //Реализована двойная выемка подстроки из верстки потому что, при одкнократном появлялась ошибка переполнения стека
        String strHtml=driver.getPageSource();
        ArrayList<String> substrFeedbacks= new ReturnListSubstrsFromStrRegExp().returnListSubstrsWithStrRegExp(customerFeedback,
                watchingNow, strHtml);
        String strFeedbacks=substrFeedbacks.get(0);
        System.out.format("Извлекаем товары, на которые есть отзывы на главной странице. %n");
        ArrayList<String> ListFeedbacks=new ReturnListSubstrsFromStrRegExp().returnListSubstrsWithStrRegExp(beforeFeedback,
                afterFeedback, strFeedbacks);
        return ListFeedbacks;
    }

    //Метод отправляет список ArrayList, с товарами на которые есть отзыв, в файл csv  с помощью вспомогательного метода
    public void sendFeedbackCsv(){
        ArrayList<String> ListFeedbacks=findCustomerFeedback();
        System.out.format("Помещаем эти товары в файл data.csv в папку target. %n");
        new CreateAndWriteCSV().createAndWriteCSV(ListFeedbacks, pathFeedbackCsv);
    }

    // Метод открыает выпадающее аккаугт меню
    public void openAccountMenu(){
        WebElement accontMenu=new WebDriverWait(driver, timeForExplicitWait)
                .until(ExpectedConditions.presenceOfElementLocated(selectorAuthorizationCheck));
        accontMenu.click();
    }
}

