package returner;

//Этот класс вынимает название браузера из конфигурационного файла для инициализации ВебДрайвера
import utils.ReaderContentFromConfigXML;

public class ReturnBrowserNameFromConfig extends ReaderContentFromConfigXML {
    //Название Тега, содержащего необходимый браузер
    private static String tagName="browser";
    //Значение атрибута тега
    private static String attributeTagNameValue ="browserForTest";
    //Браузер дрвайвера по умолчанию
    private static String browserDefaultName="FIREFOX";

    private String returnTagContant;

    /*
    Этот метод вынимает название браузера из конфигурационного файла XML для инициализации ВебДрайвера, передавая
    идентефикаторы для пойска нужного тега, значение которого и является названием браузера. Если с конфигурационным файлом
    имеются каки-ето проблемы, то возвращает назвние браузера, определенного переменной browserDefaultName по умолчаиню
     */
    public String returnBrowserName(){
        returnTagContant=readTagContent(tagName, attributeTagNameValue);
        if (returnTagContant==null){
            returnTagContant=browserDefaultName;
        }
        return returnTagContant;
    }
}
