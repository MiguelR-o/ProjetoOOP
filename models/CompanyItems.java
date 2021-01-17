package models;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyItems{
    private HashMap<ID,Item> items = new HashMap<ID, Item>();

    public Collection<String> getKeys() {
        Collection keys =items.keySet();
        return keys;
    }
}
