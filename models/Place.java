package models;

public class Place {
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
