package controllers;

import models.*;
import views.Cli;

import java.io.Serializable;
import java.lang.ref.Cleaner;
import java.lang.reflect.Array;
import java.util.*;

public class CompanyClass implements Company , Serializable {
    private static final long serialVersionUID = 1L;
    private CompanyEmployees companyEmployees;
    private HashMap<Integer,Place> companyPlaces;
    private HashMap<Integer,Client> companyClients;
    private Permission permissions;
    private int employeeID;
    private int placeID;
    private int deliveryID;
    private int depositID;
    private int clientID;

    public CompanyClass() {

        this.permissions = new Permission();
        this.companyEmployees = new CompanyEmployees();
        this.companyClients = new HashMap<Integer, Client>();
        this.companyPlaces = new HashMap<Integer,Place>();
        this.employeeID = 1;
        this.clientID = 1;
        this.placeID =1;
        this.depositID =1;
        this.deliveryID = 1;
    }


    @Override
    public boolean hasPlaceWithID(String placeID) {
        return companyPlaces.containsKey(Integer.parseInt(placeID));
    }

    @Override
    public boolean validItems(HashMap<Integer, String> itemsDeposited, int clientID) {
       //go to the given client and getItems , check for each key of the given map if exists in getItems Map
       Client client= companyClients.get(clientID);
       HashMap<Integer,Item> items = client.getItemMap();
       Set<Integer> depositedIDs =  itemsDeposited.keySet();
       for (Integer depositID : depositedIDs){
           if(!items.containsKey(depositID)){
               return false;
           }
       }
        return true;
    }

    public ArrayList<String> itemOutputCCPermissions(Client client,int ID){
        ArrayList<String> output = new ArrayList<String>();
        for (String permission : client.getItemMap().get(ID).getPermissions()){
            output.add(permission);
        }
        return output;
    }

    @Override
    public Place getPlaceByID(int place) {
        return  companyPlaces.get(place);
    }

    @Override
    public Employee getEmployeeByID(int clientManagerID) {
        return companyEmployees.getEmployee(clientManagerID);
    }


    public ArrayList<String> prepareItemOutputCC(Client client,int ID){
        ArrayList<String> output = new ArrayList<String>();
        HashMap<Integer,Item> clientItems = client.getItemMap();
        Item item = clientItems.get(ID);
        output.add(Integer.toString(item.getItemID()));
        output.add(Integer.toString(item.getAmount()));
        output.add(item.getName());
        return output;
    }

    public Employee getEmployeebyID(int employeeID) {
        Employee employee = companyEmployees.getEmployee(employeeID);
        return employee;
    }

    @Override
    public boolean validEmployeesID(String[] employeeIDs) {
        for(String id : employeeIDs){
           if(!companyEmployees.hasEmployeeByID(Integer.parseInt(id))){
               return false;
           }
        }
        return true;
    }

    @Override
    public boolean validDriverPermissions(HashMap<String, Set> permissionMap, Set<String> summedPermissions) {
        for (String permission : summedPermissions){
            if(!permission.equals("S")){
                if(!permissionMap.get("Driver").contains(permission)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean validDelivererPermissions(HashMap<String, Set> permissionMap, Set<String> summedPermissions) {
        for (String permission : summedPermissions){
            if(!permissionMap.get("Deliverer").contains(permission)){
                return false;
            }
        }
        return true;
    }

    public HashMap<Integer,Employee> createEmployeeMap(String[] employeeIDs){
        HashMap<Integer,Employee> employeeMap = new HashMap<Integer,Employee>();
        for (String key : employeeIDs){
            Employee employee = companyEmployees.getEmployee(Integer.parseInt(key));
            employeeMap.put(Integer.parseInt(key), employee);
        }

        return  employeeMap;
    }

    public void subItemQuantity(Client client, HashMap<Integer,String> items){
        for( int key : items.keySet()){
            client.getItemMap().get(key).subAmount(Integer.parseInt(items.get(key)));
        }
    }

    public boolean hasItem(Client client, int itemID){
        for(int key : client.getItemMap().keySet()){
            if(key == itemID){
                return true;
            }
        }
        return false;
    }

    public void addItemQuantity(Client client, HashMap<Integer,String> items){
        for( int key : items.keySet()){
            client.getItemMap().get(key).addAmount(Integer.parseInt(items.get(key)));
        }
    }

    @Override
    public int registerDeposit(String clientID, String placeID,String[] employeeIDs,HashMap<Integer,String> items) {
        int clienID = Integer.parseInt(clientID);
        int placID = Integer.parseInt(placeID);
        Place place = companyPlaces.get(placID);
        Client client = companyClients.get(clienID);
        HashMap<Integer,Employee> employeeMap = createEmployeeMap(employeeIDs);
        Deposit deposit = new Deposit(clienID,placID,client, items,employeeMap);
        client.addDeposit(deposit);
        addItemQuantity(companyClients.get(clienID),items);
        return deposit.getDepositID();
    }

    @Override
    public HashMap<String, Set> createPermissionMap(String[] employeeIDs) {
        //convert array to arrayList
        ArrayList<String> arraylist = new ArrayList<String>();
        for (String string : employeeIDs){
            arraylist.add(string);
        }
        //create the map
        HashMap<String,Set> permissionMap = new HashMap<String,Set>();
        //Create the Driver set
        Set<String> driverSet = new HashSet<String>();
        //Create the Deliverer set
        Set<String> delivererSet = new HashSet<String>();
        //Search for Drivers and Deliverer until both sets size is = 2
        for (String element : arraylist){
            int elementID = Integer.parseInt(element);
            if (driverSet.size()<2){
                if(companyEmployees.hasDriver(elementID)){
                    System.out.println(companyEmployees.getDriver(elementID).getPermission());
                    driverSet.add(companyEmployees.getDriver(elementID).getPermission());
                }
            }if (delivererSet.size() <2 ){
                if(companyEmployees.hasDeliverer(elementID)){
                    delivererSet.add(companyEmployees.getDeliverer(elementID).getPermission());
                }
            }

        }
        //add the items to the map
        permissionMap.put("Driver",driverSet);
        permissionMap.put("Deliverer",delivererSet);
        //return the map
        return permissionMap;
    }

    @Override
    public Set<String> storeItemsDepositedPermissions(HashMap<Integer, String> itemsDeposited,Integer clientID) {
        Set<String> summedPermission = new HashSet<String>();
        Set<Integer> keys = itemsDeposited.keySet();
        HashMap<Integer,Item> clientItemMap = companyClients.get(clientID).getItemMap();
        for (int key : keys){
            if (summedPermission.size()<3){
               ArrayList<String> itemPermissions = clientItemMap.get(key).getPermissions();
               for(String permission : itemPermissions){
                   summedPermission.add(permission);
               }
            }
        }
        return summedPermission;
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
    public int registerEmployee(String category, String permission, String name) {
        if (companyEmployees.isEmpty()){
            this.employeeID -= 1;
        }
        this.employeeID += 1;
        if(category.equals("Condutor")){
            Driver employee = new Driver(name,employeeID,permission);
            companyEmployees.addDriver(employee.getID(), employee);
            return employee.getID();

        }else if(category.equals("Carregador")){
            Deliverer employee = new Deliverer(name,employeeID,permission);
            companyEmployees.addDeliverer(employee.getID(), employee);

            return employee.getID();

        }else if(category.equals("Gestor")){
            Manager employee = new Manager(name,employeeID,permission);
            companyEmployees.addManager(employee.getID(), employee);
            return employee.getID();
        }
        return 0;


    }

    @Override
    public boolean hasClient(String clientID) {
        int IDClient = Integer.parseInt(clientID);
        Set<Integer> coll = companyClients.keySet();
        for (int key : coll){
            if(key== IDClient){
               return true;
            }
        }
        return false;
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
    public int registerClient(String nameClient,String employeeID) {

        if (companyClients.isEmpty()){
            int managerID = Integer.parseInt(employeeID);
            Client client = new Client(nameClient,clientID,managerID);
            companyClients.put(client.getID(),client);
            return client.getID();
        }else{
            clientID += 1;
            int managerID = Integer.parseInt(employeeID);
            Client client = new Client(nameClient,clientID,managerID);
            companyClients.put(client.getID(),client);
            return client.getID();
        }

    }

    @Override
    public Client getClientByID(int clientID) {
        return companyClients.get(clientID);
    }


    @Override
    public int registerItem(String itemName, String clientID, String[] itemPermissions) {
        int IDClient = Integer.parseInt(clientID);
        Client client = companyClients.get(IDClient);
        //creates the ID to item and generates the item value
        int itemID = client.getItemMap().values().size()+1;
        Item registeredItem = new Item(itemName,itemID ,IDClient,itemPermissions);
        //associates item to client
        client.addItem(registeredItem);
        return registeredItem.getItemID();
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
            placeID -= 1;
        }
        placeID += 1;
        Place placeToRegister = new Place(placeName,placeID);
        //adding the place to company places
        companyPlaces.put(placeID,placeToRegister);
        return placeToRegister.getPlaceID();
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
