package models;

import java.io.Serializable;
import java.util.HashMap;

public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    private int place;
    private int ID;
    private HashMap<Integer,String> items;

    public Delivery(int deliveryID, int placeID , HashMap<Integer,String> items) {
        this.place = placeID;
        this.ID = deliveryID;
        this.items = items;
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
