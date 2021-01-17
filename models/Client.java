package models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Client {
    private ID ID;
    private String name;
    private HashMap<ID,Item> items;
    private HashMap<ID,Delivery> Deliverys;

    //TODO getID code !!
    public ID getID() {
        //returns this.ID
        return this.ID;
    }

    public Collection<Item> getItems(){
       return items.values();
    }

    public void addItem(Item item){
        items.put(item.getID(), item);
    }
}
