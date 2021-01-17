package models;

public class ID{
    private int value;
    private String type;
    public ID(int value) {
        this.value = value;
    }

    public void addOne(){
        this.value +=1;
    }

    public int getID(){
        return this.value;
    }
}
