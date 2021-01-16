package models;

import java.util.HashMap;

public class CompanyEmployees implements CompanyStorage{
    private HashMap<String,Employee> employees;

    public CompanyEmployees() {
        this.employees = new HashMap<String, Employee>();
    }

    @Override
    public boolean hasName(String name) {
        return false;
    }
}
