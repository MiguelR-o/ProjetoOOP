package models;

import java.io.Serializable;
import java.util.HashMap;

public class Place implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int ID;
    private HashMap<Integer,Delivery> deliverys;


    public Place(String name , int placeID){
        this.name = name;
        this.ID = placeID;
    }

    public int getPlaceID(){
        return this.ID;
    }
    public String getName() {
        return this.name;
    }

    public Delivery getDeliveryByID(int deliveryID){
        return deliverys.get(deliveryID);
    }


}
