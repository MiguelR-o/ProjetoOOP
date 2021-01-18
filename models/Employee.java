package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int ID;
    private String permission;
    private HashMap<Integer, Deposit> deposits;
    private HashMap<Integer, Delivery> deliveries;

    public Employee(String name , int ID , String permission){
        this.name = name;
        this.ID = ID;
        this.permission = permission;
        this.deposits = new HashMap<Integer, Deposit>();
        this.deliveries = new HashMap<Integer, Delivery>();


    }


    public HashMap<Integer,Delivery> getDeliveries(){
        return this.deliveries;
    }
    public HashMap<Integer,Deposit> getDeposits(){
        return this.deposits;
    }

    public void addDelivery(Delivery delivery){
        deliveries.put(delivery.getDeliveryID(),delivery);
    }

    public void addDeposit(Deposit deposit){
        deposits.put(deposit.getDepositID(),deposit);
    }
    public String getPermission() {
        return this.permission;
    }

    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.ID;
    }

}
