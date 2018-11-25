package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserSingleton;

//Класс для работы со страницей случайно открытого каталога продукции
public class RandomDirectoryCatalogPage {

    private static int timeForExplicitWait = 10;

    private WebDriver driver;

    private By selectorIdDirecroryCatalog= By.xpath("//h1[@class='Page__TitleActivePage PageSection__TitleActivePage']");
    private By selectorShopBy=By.xpath("//div[@id='Header__Logo']//*[@class='header-logo']");

    public RandomDirectoryCatalogPage(){
        driver = BrowserSingleton.getInstanceDriwer();
    }

    //Метод возвращает имя случайно открытого каталога
    public String nameOfCatalog() {
        WebElement RandomDirectory=(new WebDriverWait(driver, timeForExplicitWait))
                .until(ExpectedConditions.presenceOfElementLocated(selectorIdDirecroryCatalog));
        return RandomDirectory.getText();
    }

    //Метод переходит на главную страницу сайта
    public void goToMainAfterAutorizationPage() {
        WebElement ShopBy = driver.findElement(selectorShopBy);
        System.out.format("Переходим на главную страницу сайта. %n");
        ShopBy.click();
    }
}
