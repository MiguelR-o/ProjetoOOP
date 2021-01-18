package controllers;

import models.*;
import views.Cli;

import java.lang.ref.Cleaner;
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
        return companyPlaces.containsKey(convertToID(Integer.parseInt(placeID)));
    }

    @Override
    public boolean validItems(HashMap<ID, String> itemsDeposited, ID clientID) {
       //go to the given client and getItems , check for each key of the given map if exists in getItems Map
       Client client= companyClients.get(clientID);
       HashMap<ID,Item> items = client.getItemMap();
       Set<ID> depositedIDs =  itemsDeposited.keySet();
       for (ID depositID : depositedIDs){
           if(!items.containsKey(depositID)){
               return false;
           }
       }
        return true;
    }

    @Override
    public boolean validEmployeesID(String[] employeeIDs) {
        for(String id : employeeIDs){
           if(!companyEmployees.hasEmployeeByID(convertToID(Integer.parseInt(id)))){
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

    public HashMap<ID,Employee> createEmployeeMap(String[] employeeIDs){
        HashMap<ID,Employee> employeeMap = new HashMap<>();
        for (String key : employeeIDs){
            Employee employee = companyEmployees.getEmployee(convertToID(Integer.parseInt(key)));
            employeeMap.put(convertToID(Integer.parseInt(key)), employee);
        }

        return  employeeMap;
    }

    public HashMap<ID,Item> addItemQuantity(Set<ID> itemID, Client client){
        for(ID item:itemID){
            client.getItemByID(item).addAmount();
        }
    }

    @Override
    public int registerDeposit(String clientID, String placeID,String[] employeeIDs,HashMap<ID,String> items) {
        ID clienID = convertToID(Integer.parseInt(clientID));
        ID placID = convertToID((Integer.parseInt(placeID)));
        Place place = companyPlaces.get(placID);
        Client client = companyClients.get(clienID);
        HashMap<ID,Employee> employeeMap = createEmployeeMap(employeeIDs);
        //HashMap<ID,Item> itemMap = createItemMap(items.keySet(),client);
        Deposit deposit = new Deposit(clienID,place,client, items,employeeMap);
        client.addDeposit(deposit);
        //add it to client , all employees that participate , to all items
        //increase the amount of items in client
        return deposit.getID().getIDValue();
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
        Set<String> driverSet = Collections.emptySet();
        //Create the Deliverer set
        Set<String> delivererSet = Collections.emptySet();
        //Search for Drivers and Deliverer until both sets size is = 2
        for (String element : arraylist){
            ID elementID = convertToID(Integer.parseInt(element));
            if (driverSet.size()<2){
                if(companyEmployees.hasDriver(elementID)){
                    driverSet.add(companyEmployees.getDriver(elementID).getPermission());
                }
            }else if (delivererSet.size() <2 ){
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
    public Set<String> storeItemsDepositedPermissions(HashMap<ID, String> itemsDeposited,ID clientID) {
        Set<String> summedPermission = Collections.emptySet();
        Set<ID> keys = itemsDeposited.keySet();
        HashMap<ID,Item> clientItemMap = companyClients.get(clientID).getItemMap();
        for (ID key : keys){
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
        ID itemID = convertToID(client.getItemMap().values().size()+1);
        Item registeredItem = new Item(itemName,itemID ,IDClient,itemPermissions);
        //associates item to client
        client.addItem(registeredItem);
        return registeredItem.getItemID().getIDValue();
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
        return placeToRegister.getPlaceID().getIDValue();
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
