package models;

import java.io.Serializable;
import java.util.HashMap;

public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    private int place;
    private int ID;
    private Client client;
    private HashMap<Integer,String> items;
    private HashMap<Integer,Employee> employees;
    public Delivery(int deliveryID, int placeID ,Client client, HashMap<Integer,String> items, HashMap<Integer,Employee> employeeMap) {
        this.place = placeID;
        this.ID = deliveryID;
        this.items = items;
        this.client = client;
        this.employees = employeeMap;
    }

    public  int getPlace(){
        return this.place;
    }
    public int getDeliveryID() {
        return this.ID;
    }

    public int getID() {
        return this.ID;
    }

    public int getItemAmount(int key) {
        return Integer.parseInt(items.get(ID));
    }
}
