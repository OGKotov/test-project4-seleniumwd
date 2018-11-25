package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserSingleton;

/*Класс предназначен для работы c выпадающим меню аккаунта, инициализирует необходимые для работы с ней переменные,
осуществает необходимую для задач тестирования работу
 */

public class AccountMenuPage {

    private static int timeForExplicitWait =40;

    private WebDriver driver;

    private By selectorExitAccount= By.xpath("//div[@class='Header__ContentBlockPersonalLink']//*[@class='Header__LinkPersonalCabinet']");

    public AccountMenuPage(){
        driver = BrowserSingleton.getInstanceDriwer();
    }

    //Метод нажимает на кнопку выход в выпадающем меню аккаунта
    public void exitFromAccount() {
        WebElement exitAccount=(new WebDriverWait(driver, timeForExplicitWait))
                .until(ExpectedConditions.elementToBeClickable(selectorExitAccount));
        exitAccount.click();
        (new WebDriverWait(driver, timeForExplicitWait))
                .until(ExpectedConditions.invisibilityOfElementLocated(selectorExitAccount));
        System.out.format("Выходим из аккаунта. %n");
    }
}
