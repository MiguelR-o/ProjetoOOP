package controllers;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadFile{
   public CompanyClass readFile(String readFileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(readFileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            CompanyClass c = (CompanyClass) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
