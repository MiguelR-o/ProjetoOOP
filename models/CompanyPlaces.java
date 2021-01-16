package models;


import java.util.HashMap;
import java.util.List;

public class CompanyPlaces implements CompanyStorage{
    private HashMap<IdPlace , Place> places;

    public CompanyPlaces(){
        this.places = new HashMap<IdPlace,Place>();
    }


    @Override
    //TODO
    public List getKeys() {
        return null;
    }
}
