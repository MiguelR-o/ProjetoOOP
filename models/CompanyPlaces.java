package models;


import java.util.HashMap;

public class CompanyPlaces implements CompanyStorage{
    private HashMap<IdPlace , Place> places;

    public CompanyPlaces(){
        this.places = new HashMap<IdPlace,Place>();
    }

    @Override //verifcar se é de facto necessario
    //TODO fazer o hasName
    public boolean hasName(String name) {

        return false;
    }
}
