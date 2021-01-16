package models;

import java.util.HashMap;
import java.util.List;

public class CompanyItems implements CompanyStorage {
    private HashMap<ID,Item> items = new HashMap<ID, Item>();
    @Override
    public List getKeys() {
        return null;
    }
}
