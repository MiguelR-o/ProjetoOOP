package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CompanyEmployees {
    private HashMap<ID, Driver> drivers;
    private HashMap<ID, Deliverer> deliverers;
    private HashMap<ID, Manager> managers;
    // TODO tudo com os 3 mapas !!
    public CompanyEmployees() {
        // TODO Resolve the issue with the heritage!!
        HashMap<ID, Driver> drivers = new HashMap<ID, Driver>();
        HashMap<ID, Deliverer> deliverers = new HashMap<ID, Deliverer>();
        HashMap<ID, Manager> managers = new HashMap<ID, Manager>();
    }


    public void addDriver(ID ID, Driver driver){
        drivers.put( ID,driver);
    }
    public void addDeliverer(ID ID,Deliverer deliverer){
        deliverers.put(ID,deliverer);
    }
    public void addManager(ID ID ,Manager manager){
        managers.put(ID,manager);
    }
    public boolean hasEmployeesInCategory(String category, String name) {
        switch (category){
            case "Condutor":
                Collection<Driver> coll = drivers.values();
                for(Driver driver : coll){
                    if (driver.getName().equals(name)){
                        return true;
                    }
                }
                return  false;
            case "Carregador":
                Collection<Deliverer> delivererColl = deliverers.values();
                for(Deliverer deliverer : delivererColl){
                    if (deliverer.getName().equals(name)){

                        return true;
                    }
                }
                return  false;
            case "Gestor":
                Collection<Manager> managerColl = managers.values();
                for(Manager manager : managerColl){
                    if (manager.getName().equals(name)){
                        return true;
                    }
                }
                return  false;
        }
        return false;
    }

    public boolean isEmpty() {
        if (drivers.isEmpty() && deliverers.isEmpty() && managers.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public boolean hasDriver(ID driverID){
        return drivers.containsKey(driverID);
    }

    public Driver getDriver(ID driverID){
        return drivers.get(driverID);
    }

    public boolean hasEmployeeByID(ID employeeID) {
        if(drivers.containsKey(employeeID)){
            return true;
        }else if(deliverers.containsKey(employeeID)){
            return true;
        }else if(managers.containsKey((employeeID))){
            return true;
        }else{
            return false;
        }
    }

    public boolean hasDeliverer(ID employeeID) {
        return deliverers.containsKey(employeeID);
    }

    public Deliverer getDeliverer(ID employeeID) {
        return deliverers.get(employeeID);
    }

    public Employee getEmployee(ID employeeID) {
        if(drivers.containsKey(employeeID)){
            return drivers.get(employeeID);
        }else if (deliverers.containsKey(employeeID)){
            return deliverers.get(employeeID);
        }else{
            return managers.get(employeeID);
        }
    }

    public ArrayList<String> getKeys() {
        ArrayList<String> keys = new ArrayList<>(List.of("Condutor","Gestor","Carregador"));
        return keys;
    }
}

