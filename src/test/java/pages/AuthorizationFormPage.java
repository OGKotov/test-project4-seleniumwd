package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserSingleton;
import utils.ReaderContentFromConfigXML;

/*Класс предназначен для работы с авторизационной формой, инициализирует необходимые для работы с ней переменные,
осуществает необходимую для задач тестирования работу
 */
public class AuthorizationFormPage {

    //Название тега и значение атрибута id содержащего EMail для регистрации
    private static String tagNameEmail="email";
    private static String attributeIdValueEmail="emailforautorization";
    //Название тега и значение атрибута id содержащего телефонный номер для регистрации
    private static String tagNameTelNumber="telnumber";
    private static String attributeIdTelNumber="telnumberforautorization";
    //Название тега и значение атрибута id содержащего Password для регистрации
    private static String tagNamePassword="password";
    private static String attributeIdValuePassword="passwordforautorization";

    //Email, номер телефона и Password из конфигурационного файла, необходимые для регистрации
    private static String eMail=new ReaderContentFromConfigXML().readTagContent(tagNameEmail, attributeIdValueEmail);
    private static String telNumber=new ReaderContentFromConfigXML().readTagContent(tagNameTelNumber, attributeIdTelNumber);
    private static String password=new ReaderContentFromConfigXML().readTagContent(tagNamePassword, attributeIdValuePassword);


    private WebDriver driver;

    private By selectorSelectEMail=By.id("email-tab");
    private By selectorFieldEMail=By.id("LLoginForm_email");
    private By selectorFieldTelNumber=By.id("LLoginForm_phone");
    private By selectorFieldPassword=By.id("LLoginForm_password");
    private By selectorButtonEntry=By.xpath("//descendant::div[@id='Auth__ServiceEmail']//input[@type='submit']");


    public AuthorizationFormPage() {
        driver= BrowserSingleton.getInstanceDriwer();
    }

    public void selectEMail(){
        driver.findElement(selectorSelectEMail).click();
    }

    /*Метод предназначен для заполнения авторизационной формы и отправление ее для авторизации*/
    public void recordAndClickAuthorizationForm() {
        System.out.format("Вводим номер телефона: %s и Password: %s в форму для авторизации.  %n", telNumber, password);
        WebElement FieldEmail=driver.findElement(selectorFieldTelNumber);
        FieldEmail.clear();
        FieldEmail.sendKeys(telNumber);
        WebElement FieldPassword=driver.findElement(selectorFieldPassword);
        FieldPassword.clear();
        FieldPassword.sendKeys(password);
        driver.findElement(selectorButtonEntry).click();
    }
}
