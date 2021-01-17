package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Permission{
    private HashMap<String, ArrayList<String>> permissions;

    public Permission() {
        this.permissions = new HashMap<String, ArrayList<String>>();
        permissions.put("Condutor",new ArrayList<String>(List.of("N","P")));
        permissions.put("Carregador",new ArrayList<String>(List.of("N","S")));
        permissions.put("Gestor",new ArrayList<String>(List.of("N")));

    }

    public ArrayList getCategory(String category) {
        return  permissions.get(category);
    }
}
