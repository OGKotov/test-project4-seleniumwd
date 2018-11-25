package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


/*Класс читает значение тега в конфигурационном файле XML*/
public class ReaderContentFromConfigXML {
    //Путь конфигурационного файла
    private static String pathXmlConfig = "src/main/resources/ConfigDataForTest.xml";
    //Название атрибута тега
    private static String attributeTagName="id";

    private String tagContent =null;


/*Метод читает тег в конфигурационном файле XML, принимая при этом параметы:название тега, значение атрибута id*/
    public String readTagContent(String parTagName, String parAttributeTagValue) {
        try {
            File file = new File(pathXmlConfig);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Element root = document.getDocumentElement();
            NodeList tags = root.getElementsByTagName(parTagName);
            for (int i=0; i<tags.getLength(); i++) {
                Element tag=(Element)tags.item(i);
                if(tag.getAttribute(attributeTagName).equals(parAttributeTagValue)){
                    tagContent=tag.getTextContent();
                    break;
                }
            }
        }catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return tagContent;
    }
}

