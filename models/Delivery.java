package models;

import java.io.Serializable;

public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    private int ID;

    public Delivery(int deliveryID) {
        this.ID = deliveryID;
    }

    public int getDeliveryID() {
        return this.ID;
    }

    public int getID() {
        return this.ID;
    }
}
