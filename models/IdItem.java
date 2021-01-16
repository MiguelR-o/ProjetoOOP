package models;

public class IdItem implements ID {
    private int ID;
    private static final String type = "Item";
    public IdItem(int IdNumber) {
        this.ID = IdNumber;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public String getType() {
        return type;
    }
}
