package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class CompanyEmployees implements Serializable{
    private static final long serialVersionUID = 1L;
    private HashMap<ID, Driver> drivers;
    private HashMap<ID, Deliverer> deliverers;
    private HashMap<ID, Manager> managers;
    private HashSet<Permission> SetTest;
    private Permission permission;
    // TODO tudo com os 3 mapas !!
    public CompanyEmployees() {
        // TODO Resolve the issue with the heritage!!
        Permission permission = new Permission();
        HashMap<ID, Driver> drivers = new HashMap<ID, Driver>();
        HashMap<ID, Deliverer> deliverers = new HashMap<ID, Deliverer>();
        HashMap<ID, Manager> managers = new HashMap<ID, Manager>();
        HashSet<Permission> SetTest = new HashSet<Permission>();
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
}

