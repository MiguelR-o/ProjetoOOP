package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    private ID ID;
    private String name;
    private ID clientManagerID;
    private HashMap<ID, Item> items;
    private HashMap<ID, Deposit> deposits;
    private HashMap<ID, Delivery> deliverys;

    public Client(String name, ID ID, ID managerID) {
        this.name = name;
        this.ID = ID;
        this.clientManagerID = managerID;
    }

    public ID getID() {
        return this.ID;
    }

    public HashMap<ID, Item> getItemMap() {
        return this.items;
    }

    public Item getItemByID(ID itemID) {
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

    public HashMap<ID, Deposit> getDepositMap() {
        return this.deposits;
    }
}
