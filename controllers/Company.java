package controllers;

import models.Employee;
import models.ID;

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

    boolean hasClient(String clientID);
    //Checks in companny's clients Dic for the key clientID which contains the client obj

    boolean hasClientName(String clientName);
    //Checks in companny's clients Dic for each key clientID which contains the client obj and
    //compares Client.getName() with clientName

    void registerClient(String nameClient,String employID);
    //Creates an object client with the given values and adds it to the Clients Dic in company

    int getClientID(String nameClient);
    //Searches for the Client object inside Company's client Dic and returns client.getID();

    int getEmployeeID(Employee employee);
    // returns employee.getID();

    Employee getProfessional(String name, String category);
    // Searches in companny's Employees Dic for the Professional inside the given Category Dic with the
    // key name and returns the object as an Employee

    int registerItem(String itemName, String clientID, String[] itemPermissions);
    //register the given item and adds it to the Item DIC in company witht the itemID as a key and the obj Item as value
    //if permissions is empty register with normal permission
    //returns item.getID();

    boolean hasPlaceName(String placeName);
    //Searches inside Places Dic in company for get.placeName == placeName

    int registerPlace(String placeName);
    //Creates place object and adds it to the company Places dic with placeID as the key and the obj as value
    //returns place.getID();

    boolean hasPermission(String itemPermission);
    //Receives a permission and checks if its on of the values inside the permissions Enum;
    ID convertToID(int parseInt);
    //Receives an integer and turns into an ID object


}
