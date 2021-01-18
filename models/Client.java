package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

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

    public HashMap<Integer, Deposit> getDepositMap() {
        return this.deposits;
    }
}
