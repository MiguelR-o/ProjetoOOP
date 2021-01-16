package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface CompanyStorage<T>{
    // TODO complete the Interface if a new default method is found
    boolean hasCaseName(String name);
    // given a string it searchs the values for value.getName()
    // and returns True if name == value.getname()

     List<T> getKeys();
     //returns a ListArray with all the keys


}
