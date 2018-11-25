package utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Класс извлекает подстроку из строки, используя регулярные выражения
public class ReturnListSubstrsFromStrRegExp {

    /*Метов извлекает подстроку из строки которя находиться меожу beforeRegExp и afterRegExp регулярными выражениями
    Регулярные выражения и строка передаются в качестве параметров
    Возвращает извлеченную посдтроку
     */
    public ArrayList<String> returnListSubstrsWithStrRegExp(String beforeRegExp, String afterRegExp, String str) {
        ArrayList<String> subStrs = new ArrayList<String>();
        Pattern beforePat = Pattern.compile(beforeRegExp);
        Matcher beforeMat = beforePat.matcher(str);
        Pattern afterPat = Pattern.compile(afterRegExp);
        Matcher afterMat = afterPat.matcher(str);
        while (beforeMat.find() && afterMat.find()) {
            int start = beforeMat.end();
            int end = afterMat.start();
            subStrs.add(str.substring(start, end));
        }
        return subStrs;
    }
}