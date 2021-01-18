package models;

import java.io.Serializable;
import java.util.HashMap;

public class Deposit implements Serializable {

    private static final long serialVersionUID = 1L;
    private ID ID;
    private Client client;
    private HashMap<ID,String> items; //ItemID _ amount
    private HashMap<ID,Employee> employees;
    private Place place; //might want a PLACE object

    public Deposit(ID depositID, Place place , Client client, HashMap<ID,String> itemMap , HashMap<ID,Employee> employeeMap) {
        this.ID = depositID;
        this.place = place;
        this.client = client;
        this.items = itemMap;
        this.employees = employeeMap;

        //pensar na melhor maneira de dar store do deposit e dentro do mesmo
        //eficiencia de pesquisa do item
    }

    public ID getDepositID(){
        return this.ID;
    }
}
