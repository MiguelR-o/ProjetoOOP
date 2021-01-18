package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CompanyEmployees implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<Integer, Driver> drivers;
    private HashMap<Integer, Deliverer> deliverers;
    private HashMap<Integer, Manager> managers;
    public CompanyEmployees() {
         this.drivers = new HashMap<Integer, Driver>();
        this.deliverers = new HashMap<Integer, Deliverer>();
       this.managers = new HashMap<Integer, Manager>();
    }


    public void addDriver(int ID, Driver driver){
        drivers.put( ID,driver);
    }
    public void addDeliverer(int ID,Deliverer deliverer){
        deliverers.put(ID,deliverer);
    }
    public void addManager(int ID ,Manager manager){
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

    public boolean hasDriver(int driverID){
        return drivers.containsKey(driverID);
    }

    public Driver getDriver(int driverID){
        return drivers.get(driverID);
    }

    public boolean hasEmployeeByID(int employeeID) {
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

    public boolean hasDeliverer(int employeeID) {
        return deliverers.containsKey(employeeID);
    }

    public Deliverer getDeliverer(int employeeID) {
        return deliverers.get(employeeID);
    }

    public Employee getEmployee(int employeeID) {
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

