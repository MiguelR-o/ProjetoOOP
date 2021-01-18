package models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private int ID;
    private String name;
    private ArrayList<String> permissions;
    private int amount;
    private HashMap<Integer,Deposit> deposits;
    private HashMap<Integer,Delivery> deliveries;


    public Item(String name, int IdNumber,int clientID, String[] permissions){
        this.name = name;
        this.ID = IdNumber;
        this.permissions = this.permissionsToArrayList(permissions);
        this.deposits = new HashMap<Integer,Deposit>();
    }

    public String getName(){
        return this.name;
    }
    public int getItemID() {
        return this.ID;
    }

    public HashMap<Integer,Deposit> getDepositsMap(){
        return this.deposits;
    }

    public ArrayList<String> getPermissions(){
        return this.permissions;
    }

    public void withdrawAmount(int value){
        this.amount -= value;
    }
    public HashMap<Integer,Delivery> getDeliveries(){
        return this.deliveries;
    }

    public void addDelivery(Delivery delivery){

        deliveries.put(delivery.getID(),delivery);
    }
    public HashMap<Integer,Deposit> getDeposits(){
        return this.deposits;
    }

    public void addDeposit(Deposit deposit){
        deposits.put(deposit.getDepositID(),deposit);
    }

    public void subAmount(int value) {this.amount -= value;}

    public void addAmount(int value){
        this.amount += value;
    }

    public int getAmount(){
        return this.amount;
    }

    public String getItemName(){
        return this.name;
    }

    private ArrayList<String> permissionsToArrayList(String[] permissions){
        ArrayList<String> list2 = new ArrayList<String>();
        for(String string:permissions){
            list2.add(string);
        }
        return list2;
    }
}
