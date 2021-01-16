package models;

public class IdPlace implements ID {
    private int ID;
    private String type;
    public IdPlace(int IdNumber){
        this.ID = IdNumber;
        this.type = "Place";
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
