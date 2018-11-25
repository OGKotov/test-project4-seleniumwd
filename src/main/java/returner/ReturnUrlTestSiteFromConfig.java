package returner;

//Этот класс вынимает путь до тестируемого сайта из конфигурационного файла

import utils.ReaderContentFromConfigXML;

public class ReturnUrlTestSiteFromConfig extends ReaderContentFromConfigXML {

        //Название Тега, содержащего путь к сайту
        private static String tagName="urltestsite";
        //Значение атрибута тега
        private static String attributeTagNameValue ="urlSiteForTes";

        private String returnTagContant;

    /*
    Этот метод вынимает путь к тестируемому сайту из конфигурационного файла XML, передавая
    идентефикаторы для пойска нужного тега, значение которого и является текстовым значением пути.
     */
        public String returnTestSite(){
            returnTagContant=readTagContent(tagName, attributeTagNameValue);
            return returnTagContant;
        }
}

