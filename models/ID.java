package models;

import java.io.Serializable;

public class ID implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public void subtractOne() {
        this.value -=1;
    }
}
