package models;

import java.io.Serializable;

public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    private int place;
    private int ID;

    public Delivery(int deliveryID, int placeID) {
        this.place = placeID;
        this.ID = deliveryID;
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
}
