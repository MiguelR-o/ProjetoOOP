package models;

import java.util.ArrayList;

public class Employee{
    private String name;
    private ID ID;
    private String type;
    private String permission;

    public Employee(String name , ID ID , String permission){
        this.name = name;
        this.ID = ID;
        this.permission = permission;

    }



    public String getEmployeeType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public ID getID(){
        return this.ID;
    }


}
