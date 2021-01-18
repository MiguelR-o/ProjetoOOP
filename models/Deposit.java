package models;

import java.io.Serializable;
import java.util.HashMap;

public class Deposit implements Serializable {

    private static final long serialVersionUID = 1L;
    private int ID;
    private Client client;
    private HashMap<Integer,String> items; //ItemID _ amount
    private HashMap<Integer,Employee> employees;
    private int place; //might want a PLACE object

    public Deposit(int depositID, int place , Client client, HashMap<Integer,String> itemMap , HashMap<Integer,Employee> employeeMap) {
        this.ID = depositID;
        this.place = place;
        this.client = client;
        this.items = itemMap;
        this.employees = employeeMap;

        //pensar na melhor maneira de dar store do deposit e dentro do mesmo
        //eficiencia de pesquisa do item
    }

    public int getDepositID(){
        return this.ID;
    }
}
