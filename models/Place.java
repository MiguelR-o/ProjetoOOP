package models;

import java.io.Serializable;

public class Place implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private ID ID;
    public Place(String name , ID placeID){
        this.name = name;
        this.ID = placeID;
    }

    public ID getPlaceID(){
        return this.ID;
    }
    public String getName() {
        return this.name;
    }
}
