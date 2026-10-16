package src.core.model;

import src.core.model.Feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModelBuilder {

    public static String readFile(String fileName) throws FileNotFoundException {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        String data = myReader.nextLine();
        return data;
    }

    public void test(String filePath) throws FileNotFoundException {
        String myArray = readFile(filePath);
        int i = 0;
        while (i <= myArray.length()) {
            if (myArray.charAt(i) == '(') {
                String name = null;
                String isMandatory = null;
                int j = i + 1;
                while (myArray.charAt(j) != ',') {
                    name += myArray.charAt(j);
                }
                while (myArray.charAt(j) != ')') {
                    isMandatory += myArray.charAt(j);
                }
                //a finir (voir le code car rien ne fonctionne)
                //Feature feature = new Feature(name, isMandatory);

            }
            System.out.println(myArray);
        }
    }
}
