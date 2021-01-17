package controllers;

import models.Employee;
import models.ID;

import java.util.HashMap;
import java.util.Set;

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

    boolean hasPlaceWithID(String placeID);
    //Checks in companyPlaces for the key placeID

    boolean validItems(HashMap<ID, String> itemsDeposited, ID clientID);
    //Checks if for each key of itemsDeposited if its in the client Item list

    boolean validEmployeesID(String[] employeeIDs);
    //Checks for each ID inside employeeIDs array if its registered in Company

    boolean validDriverPermissions(HashMap<String, Set> permissionMap, Set<String> summedPerm);
    //Checks the value for the Driver key inside the permissionMap to see if it contais the
    //item permissions

    boolean validDelivererPermissions(HashMap<String, Set> permissionMap, Set<String> summedPerm);
    //Checks the value for the Driver key inside the permissionMap to see if it contais the
    //item permissions

    int registerDeposit(String clientID, String placeID, String[] employeeIDs ,HashMap<ID,String> items);
    //Creates a Deposit object and stores it inside the each item Deposit map , client deposit map
    //aswell as each employee deposit map

    HashMap<String, Set> createPermissionMap(String[] employeeIDs);
    //receives Employee IDs and creates a map with the key being the Class name and the value a
    //Set with the given employee permissions

    Set<String> storeItemsDepositedPermissions(HashMap<ID, String> itemsDeposited, ID clientID);
    //Sums up the items requierments for the whole deposit
}
