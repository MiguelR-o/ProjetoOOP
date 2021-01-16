package models;

import java.util.HashMap;
import java.util.Map;

public class CompanyClients implements CompanyStorage<T> {
    private HashMap<IdClient,Client> clients;

    public CompanyClients(){

        this.clients = new HashMap<IdClient,Client>();
    }

    @Override
    public boolean hasName(String name) {
        return false;
    }

    public void addClient(Client client){
        this.clients.put(client.getID(), client);
    }



    public Client getClient(int clientID){
        for(Map.Entry<IdClient, Client> entry : clients.entrySet()){
            if (clientID == entry.getKey().getID()){
                return entry.getValue();
            }
        }
    }
}
