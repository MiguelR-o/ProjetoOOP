package models;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.HashMap;

public class Deposit implements Serializable{
    private static final long serialVersionUID = 1L;

import java.util.HashMap;

public class Deposit {
    private ID ID;
    private Client client;
    private HashMap<ID,Item> items;
    private HashMap<ID,Employee> employees;
    private Place place;

    public Deposit(ID depositID, Place place , Client client, HashMap<ID,Item> itemMap , HashMap<ID,Employee> employeeMap) {
        this.ID = depositID;
        this.place = place;
        this.client = client;
        this.items = itemMap;
        this.employees = employeeMap;


    }
}
