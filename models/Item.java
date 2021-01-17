package models;

import java.lang.reflect.Array;
import java.util.*;

public class Item {
    //TODO classe incompleta
    private ID ID;
    private String name;
    private ArrayList<String> permissions;

    public Item(String name, ID IdNumber,ID clientID, String[] permissions){
        this.name = name;
        this.ID = IdNumber;
        this.permissions = this.permissionsToArrayList(permissions);
    }

    public ID getItemID() {
        return this.ID;
    }

    private ArrayList<String> permissionsToArrayList(String[] permissions){
        ArrayList<String> list2 = new ArrayList<String>();
        for(String string:permissions){
            list2.add(string);
        }
        return list2;
    }
}
