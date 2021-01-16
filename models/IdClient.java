package models;

public class IdClient implements ID {
    private int ID;
    private String Type;

    public IdClient(int IdNumber) {
        this.ID = IdNumber;
        this.Type = "Client";
    }

    public int getID() {
        return this.ID;
    }

    public String getType() {
        return this.Type;
    }
}
