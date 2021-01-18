package models;

import java.util.HashMap;

public class Driver extends Employee {
    private HashMap<Integer, Deposit> deposits;
    private HashMap<Integer, Delivery> deliveries;

    public Driver(String name, int ID, String permission) {
        super(name, ID, permission);
        this.deposits = new HashMap<Integer, Deposit>();
        this.deliveries = new HashMap<Integer, Delivery>();
    }

    public HashMap<Integer,Delivery> getDeliveries(){
        return this.deliveries;
    }
    public HashMap<Integer,Deposit> getDeposits(){
        return this.deposits;
    }

    public void addDelivery(Delivery delivery){
        deliveries.put(delivery.getDeliveryID(),delivery);
    }

    public void addDeposit(Deposit deposit){
        deposits.put(deposit.getDepositID(),deposit);
    }
}
