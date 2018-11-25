package utils;

import org.openqa.selenium.WebDriver;
import returner.ReturnBrowserNameFromConfig;

//Реализация шаблона Одиночка, инициализазия ВемДрайвера фабричным браузером согласно настроек конфиг. файла

public final class BrowserSingleton {
    private static BrowserSingleton instance;
    private static WebDriver driver;

    public static WebDriver getInstanceDriwer() {
        if(instance==null) {
            instance=new BrowserSingleton();
            driver= BrowserFactory.getDriver(new ReturnBrowserNameFromConfig().returnBrowserName());
        }
        return instance.driver;
    }
}

