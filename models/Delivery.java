package models;

public class Delivery {
    private ID ID;

    public Delivery(ID deliveryID) {
        this.ID = deliveryID;
    }

    public ID getDeliveryID() {
        return this.ID;
    }
}
