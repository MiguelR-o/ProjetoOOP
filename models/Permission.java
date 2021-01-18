package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Permission{
    private HashMap<Integer, ArrayList<String>> permissions;
    private ArrayList<String> itemPermissions;

    public Permission() {
        this.itemPermissions = new ArrayList<String>(List.of("S","N","P"));
        this.permissions = new HashMap<Integer, ArrayList<String>>();
        permissions.put(1,new ArrayList<String>(List.of("N","P")));
        permissions.put(2,new ArrayList<String>(List.of("N","S")));
        permissions.put(3,new ArrayList<String>(List.of("N")));

    }

    public ArrayList getCategory(String category) {
        if (category.equals("Gestor")){

            return  permissions.get(3);
        }else if(category.equals("Carregador")){
            return  permissions.get(2);
        }else {
            return permissions.get(1);
        }
    }

    public ArrayList<String> getItemPermissions(){
        return itemPermissions;
    }
}

