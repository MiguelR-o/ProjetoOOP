package models;

import java.io.Serializable;

public class Deposit implements Serializable{
    private static final long serialVersionUID = 1L;
    private ID ID;

    public Deposit(ID depositID){
        this.ID = depositID;
    }
}
