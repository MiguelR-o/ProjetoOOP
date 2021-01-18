package models;

import java.util.HashMap;

public class Driver extends Employee {
    private HashMap<ID, Deposit> deposits;
    private HashMap<ID, Delivery> deliveries;

    public Driver(String name, ID ID, String permission) {
        super(name, ID, permission);
        this.deposits = new HashMap<ID, Deposit>();
        this.deliveries = new HashMap<ID, Delivery>();
    }

    public HashMap<ID,Delivery> getDeliveries(){
        return this.deliveries;
    }
    public HashMap<ID,Deposit> getDeposits(){
        return this.deposits;
    }

    public void addDelivery(Delivery delivery){
        deliveries.put(delivery.getDeliveryID(),delivery);
    }

    public void addDeposit(Deposit deposit){
        deposits.put(deposit.getDepositID(),deposit);
    }
}
