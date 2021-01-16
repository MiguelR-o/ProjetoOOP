package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyEmployees implements CompanyStorage{
    private HashMap<String,HashMap<ID,Employee>> employees;

    public CompanyEmployees() {
        //TODO Resolve the issue with the heritage!!
        this.employees = new HashMap<String, HashMap<ID,Employee>>();
        HashMap<ID,Employee> drivers = new HashMap<ID,Employee>();
        HashMap<ID,Employee> deliverers = new HashMap<ID,Employee>();
        HashMap<ID,Employee> managers = new HashMap<ID,Employee>();
    }

    @Override
    public boolean hasName(String name) {
        return false;
    }

    @Override
    public boolean hasCaseName(String name) {

        return false;
    }

    @Override
    public List<String> getKeys() {
        List<String> keys =employees.keySet().stream().collect(Collectors.toList());
        return keys;
    }

    public boolean hasEmployeesInCategory(String category , String name) {
        Collection coll = employees.get(category).values();
        for(Employee employee: coll){
            if(employee.getName().equals(name)){
                return true;
            }
        }
        return false;


    }
}
