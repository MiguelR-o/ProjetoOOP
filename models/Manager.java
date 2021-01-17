package models;

public class Manager extends Employee{
    private static final long serialVersionUID = 1L;
    public Manager(String name, models.ID ID, String type) {
        super(name, ID, type);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ID getID() {
        return null;
    }

    @Override
    public String getEmployeeType() {
        return null;
    }
}
