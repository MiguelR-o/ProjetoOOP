package models;

public class Employee{
    private String name;
    private ID ID;
    private String type;

    public Employee(String name , ID ID , String type){
        this.name = name;
        this.ID = ID;
        this.type = type;

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
