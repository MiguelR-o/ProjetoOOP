package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ID ID;
    private String permission;


    public Employee(String name , ID ID , String permission){
        this.name = name;
        this.ID = ID;
        this.permission = permission;

    }

    public String getPermission() {
        return this.permission;
    }

    public String getName(){
        return this.name;
    }

    public ID getID(){
        return this.ID;
    }

}
