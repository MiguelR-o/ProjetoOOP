package models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface CompanyStorage<T>{
    // TODO complete the Interface if a new default method is found
    boolean hasCaseName(String name);

     ArrayList<T> getKeys();
    // given a string it searchs the values for value.getName()
    // and returns True if name == value.getname()

}
