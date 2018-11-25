package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

//Выбор браузера для ВебДрайвера согласно настроек конфиг. файла

public final class BrowserFactory {

    private static WebDriver driver;

    public static WebDriver getDriver(String nameBrowser){
        switch (nameBrowser) {
            case "FIREFOX":
                driver=new FirefoxDriver();
                break;
            case "CHROME":
                driver=new ChromeDriver();
                break;
            case "IEEXPLORER":
                driver=new InternetExplorerDriver();
                break;
            case "OPERA":
                driver=new OperaDriver();
                break;
            case "EDGE":
                driver=new EdgeDriver();
                break;
            case "SAFARI":
                driver=new SafariDriver();
                break;
            default:
                driver=new FirefoxDriver();
                break;
        }
        return driver;
    }
}
