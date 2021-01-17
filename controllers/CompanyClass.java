package controllers;

import models.*;
import views.Cli;

import java.lang.reflect.Array;
import java.util.*;

public class CompanyClass implements Company {
    private CompanyEmployees companyEmployees;
    private HashMap<ID,Place> companyPlaces;
    private HashMap<ID,Client> companyClients;
    private Permission permissions;
    private ID employeeID;
    private ID placeID;
    private ID deliveryID;
    private ID depositID;
    private ID clientID;

    public CompanyClass() {

        this.permissions = new Permission();
        this.companyEmployees = new CompanyEmployees();
        this.companyClients = new HashMap<ID, Client>();
        this.companyPlaces = new HashMap<ID,Place>();
        this.employeeID = new ID(1);
        this.clientID = new ID(1);
        this.placeID = new ID(1);
        this.depositID = new ID(1);
        this.deliveryID = new ID(1);
    }

    public ID convertToID(int input ) {
        ID newInput = new ID(input);
        return newInput;
    }

    @Override
    public boolean hasPlaceWithID(String placeID) {
        return false;
    }

    @Override
    public boolean validItems(HashMap<ID, String> itemsDeposited) {
        return false;
    }

    @Override
    public boolean validEmployeesID(String[] employeeIDs) {
        return false;
    }

    @Override
    public boolean validDriverPermissions(HashMap<ID, Set> permissionMap) {
        return false;
    }

    @Override
    public boolean validDelivererPermissions(HashMap<ID, Set> permissionMap) {
        return false;
    }

    @Override
    public int registerDeposit() {
        return 0;
    }

    @Override
    public HashMap<ID, Set> createPermissionMap(String[] employeeIDs) {
        return null;
    }

    @Override
    public boolean hasCategory(String category) {
        List<String> keys = companyEmployees.getKeys();
        for (String key : keys) {
            if (category.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasProfessional(String name, String category) {
        return companyEmployees.hasEmployeesInCategory(name, category);
    }

    @Override
    public boolean validPermission(String permission, String category) {
        ArrayList list = this.permissions.getCategory(category);
        for(int i = 0 ; i<list.size();i++){
            if (list.get(i).equals(permission)){
                return true;
            }
        }

        return false;
    }

    @Override
    public void registerEmployee(String category, String permission, String name) {
        if (companyEmployees.isEmpty()){
            this.employeeID.subtractOne();
        }
        this.employeeID.addOne();
        if(category.equals("Condutor")){
            Driver employee = new Driver(name,employeeID,permission);
            companyEmployees.addDriver(employee.getID(), employee);

        }else if(category.equals("Carregador")){
            Deliverer employee = new Deliverer(name,employeeID,permission);
            companyEmployees.addDeliverer(employee.getID(), employee);

        }else if(category.equals("Gestor")){
            Manager employee = new Manager(name,employeeID,permission);
            companyEmployees.addManager(employee.getID(), employee);
        }


    }

    @Override
    public boolean hasClient(String clientID) {
        ID IDClient = convertToID(Integer.parseInt(clientID));
        return companyClients.containsKey(IDClient);
    }

    @Override
    public boolean hasClientName(String clientName) {
        Collection<Client> coll = companyClients.values();
        for(Client client : coll){
            if(client.getName().equals(clientName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void registerClient(String nameClient,String employeeID) {
        //TODO verificar se existe pelo menos uma entrada no dic antes de aumentar o ID para todos os casos

        if (companyClients.isEmpty()){
            ID managerID = convertToID(Integer.parseInt(employeeID));
            Client client = new Client(nameClient,clientID,managerID);
        }else{
            clientID.addOne();
            ID managerID = convertToID(Integer.parseInt(employeeID));
            Client client = new Client(nameClient,clientID,managerID);
        }

    }

    @Override
    public int getClientID(String nameClient) {
        return 0;
    }

    @Override
    public int getEmployeeID(Employee employee) {
        return 0;
    }

    @Override
    public Employee getProfessional(String name, String category) {
        return null;
    }

    @Override
    public int registerItem(String itemName, String clientID, String[] itemPermissions) {
        if (itemPermissions.length == 0) {
            //in case no permission was declared
           String[] itemPermissions2 = {"N"};
           itemPermissions = itemPermissions2;
        }
        ID IDClient = convertToID(Integer.parseInt(clientID));
        Client client = companyClients.get(IDClient);
        //creates the ID to item and generates the item value
        ID itemID = convertToID(client.getItems().size()+1);
        Item registeredItem = new Item(itemName,itemID ,IDClient,itemPermissions);
        //associates item to client
        client.addItem(registeredItem);
        return registeredItem.getItemID().getID();
    }

    @Override
    public boolean hasPlaceName(String placeName) {
        Collection<Place> places = companyPlaces.values();
        for(Place place : places){
            if(place.getName().equals(placeName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int registerPlace(String placeName) {
        if(companyPlaces.isEmpty()){
            //to start @ 1
            placeID.subtractOne();
        }
        placeID.addOne();
        Place placeToRegister = new Place(placeName,placeID);
        //adding the place to company places
        companyPlaces.put(placeID,placeToRegister);
        return placeToRegister.getPlaceID().getID();
    }

    @Override
    public boolean hasPermission(String itemPermission) {
        List<String> list = permissions.getItemPermissions();
        for (String string : list){
            if(string.equals(itemPermission)){
                return true;
            }
        }
        return false;
    }
}
