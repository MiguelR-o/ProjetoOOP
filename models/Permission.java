package models;

import java.util.HashMap;
import java.util.List;

public class Permission{
    private HashMap<String, List<String>> permissions;

    public Permission() {
        this.permissions = new HashMap<String,List<String>>();

    }
}
