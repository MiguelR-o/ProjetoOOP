package models;

import java.io.Serializable;

public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    private ID ID;

    public Delivery(ID deliveryID) {
        this.ID = deliveryID;
    }

    public ID getDeliveryID() {
        return this.ID;
    }

    public ID getID() {
        return this.ID;
    }
}
