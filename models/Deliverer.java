package models;

import java.util.HashMap;

public class Deliverer extends Employee {
    private HashMap<ID, Deposit> deposits;
    private HashMap<ID, Delivery> deliveries;

    public Deliverer(String name, models.ID ID, String type) {
        super(name, ID, type);
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
