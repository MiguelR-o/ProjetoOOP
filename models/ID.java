package models;

import java.util.WeakHashMap;

public class ID{
    private int value;
    private String type;
    public ID(int value) {
        this.value = value;
    }

    public void addOne(){
        this.value +=1;
    }

    public int getIDValue(){
        return this.value;
    }

    public void subtractOne() {
        this.value -=1;
    }

    @Override
    public boolean equals(Object compare){
        if(!(compare instanceof ID)){
            return false;
        }
        else if(compare == this){
            return true;
        }
        ID compareID = (ID) compare;
        if(compareID.getIDValue() == this.value){
            return true;
        }
        return false;
    }
}
