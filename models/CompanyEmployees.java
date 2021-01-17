package models;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyEmployees implements CompanyStorage {
    private HashMap<ID, Driver> drivers;
    // TODO tudo com os 3 mapas !!
    public CompanyEmployees() {
        // TODO Resolve the issue with the heritage!!
        HashMap<ID, Driver> drivers = new HashMap<ID, Driver>();
        HashMap<ID, Deliverer> deliverers = new HashMap<ID, Deliverer>();
        HashMap<ID, Manager> managers = new HashMap<ID, Manager>();
    }

    @Override
    public boolean hasName(String name) {
        // Checks
        return false;
    }

    @Override
    public boolean hasCaseName(String name) {

        return false;
    }

    @Override
    public List<String> getKeys() {
        List<String> keys = employees.keySet().stream().collect(Collectors.toList());
        return keys;
    }

    public boolean hasEmployeesInCategory(String category, String name) {
        Collection coll = drivers.values();
        for ( driver : coll) {
            if (driver.getName().equals(name)) {
                return true;
            }
        }
        return false;

    }
}
