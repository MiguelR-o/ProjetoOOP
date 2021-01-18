package models;

import java.io.Serializable;
import java.util.*;

public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    private int ID;
    private String name;
    private int clientManagerID;
    private HashMap<Integer, Item> items;
    private HashMap<Integer, Deposit> deposits;
    private HashMap<Integer, Delivery> deliveries;

    public Client(String name, int ID, int managerID) {
        this.name = name;
        this.ID = ID;
        this.clientManagerID = managerID;
        this.items = new HashMap<Integer,Item>();
        this.deposits = new HashMap<Integer,Deposit>();
        this.deliveries = new HashMap<Integer,Delivery>();
    }

    public int getID() {
        return this.ID;
    }

    public HashMap<Integer, Item> getItemMap() {
        return this.items;
    }

    public Item getItemByID(int itemID) {
        return items.get(itemID);
    }

    public void addItem(Item item) {
        items.put(item.getItemID(), item);
    }

    public String getName() {
        return this.name;
    }

    public void addDeposit(Deposit deposit) {
        deposits.put(deposit.getDepositID(), deposit);
    }

    public int getClientManagerID(){
        return this.clientManagerID;
    }

    public ArrayList<Integer> orderDepositIDs(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int key : deposits.keySet()){
            list.add(key);
        }
        Collections.sort(list);
        return list;
    }

    public ArrayList<Integer> orderDeliveryIDs(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int key : deliveries.keySet()){
            list.add(key);
        }
        Collections.sort(list);
        return list;
    }
    public HashMap<Integer, Delivery> getDeliveryMap() {
        return this.deliveries;
    }
    public HashMap<Integer, Deposit> getDepositMap() {
        return this.deposits;
    }
}
