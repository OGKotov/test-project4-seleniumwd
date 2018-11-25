package utils;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*Класс для создание файла csv b помещения туда информации*/

public class CreateAndWriteCSV {

    /*Метод создает файл csv и мопещает туда данные, которые приходят в виде списка*/
    public void createAndWriteCSV(ArrayList<String> sourceDataForCsv, String pathCsvResult){
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(pathCsvResult));
            String[] str=(String[]) sourceDataForCsv.toArray(new String[sourceDataForCsv.size()]);
            writer.writeNext(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
