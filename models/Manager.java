package models;

import views.Cli;

public class Manager extends Employee{
    private static final long serialVersionUID = 1L;
    private Client client;
    public Manager(String name, int ID, String type) {
        super(name, ID, type);
        this.client = null;
    }

    public void addClient(Client client){
        this.client = client;
    }


}
