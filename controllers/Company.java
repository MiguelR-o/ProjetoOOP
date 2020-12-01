package controllers;

import models.Employee;

public interface Company {
    boolean hasCategory(String category);
    //searches if there is a key equal to Category inside Professionals Dic

    boolean hasProfessional(String name, String category);
    //searches for the key Category in Professionals Dic
    // and then for the name key in the given Dic

    boolean validPermission(String permission, String category);
    //Don't know how to implement the hard code part of the rule set yet ,
    // but it should then search for the Permission inside the Category class/interface/Dic

    void registerEmployee(String category, String permission, String name);
    //Creates instance of one of the classes that implements/extends Employee based on its
    //category and adds the object to the Employees Dic in Company.

    boolean hasClient(String nameClient);


    void registerClient(String nameClient);

    String getClientID(String nameClient);

    int getEmployeeID(Employee employee);

    Employee getProfessional(String name, String category);
}
