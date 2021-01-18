package models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private ID ID;
    private String name;
    private ArrayList<String> permissions;
    private int amount;
    private HashMap<ID,Deposit> deposits;
    private HashMap<ID,Delivery> deliveries;


    public Item(String name, ID IdNumber,ID clientID, String[] permissions){
        this.name = name;
        this.ID = IdNumber;
        this.permissions = this.permissionsToArrayList(permissions);
        this.deposits = new HashMap<ID,Deposit>();
    }

    public ID getItemID() {
        return this.ID;
    }

    public HashMap<ID,Deposit> getDepositsMap(){
        return this.deposits;
    }

    public ArrayList<String> getPermissions(){
        return this.permissions;
    }

    public void withdrawAmount(int value){
        this.amount -= value;
    }
    public HashMap<ID,Delivery> getDeliveries(){
        return this.deliveries;
    }

    public void addDelivery(Delivery delivery){

        deliveries.put(delivery.getID(),delivery);
    }
    public HashMap<ID,Deposit> getDeposits(){
        return this.deposits;
    }

    public void addDeposit(Deposit deposit){
        deposits.put(deposit.getDepositID(),deposit);
    }

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
