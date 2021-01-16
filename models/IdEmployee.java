package models;

public class IdEmployee implements ID {
    private int ID;
    //TODO make all types in the ID classes use type as static final !!!
    private static final String type = "Employee";

    public IdEmployee(int IdNumber){
        this.ID = IdNumber;
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
