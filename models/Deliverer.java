package models;

import java.util.HashMap;

public class Deliverer extends Employee {
    private static final long serialVersionUID = 1L;
    private HashMap<Integer, Deposit> deposits;
    private HashMap<Integer, Delivery> deliveries;

    public Deliverer(String name, int ID, String type) {
        super(name, ID, type);
        this.deposits = new HashMap<Integer,Deposit>();
        this.deliveries = new HashMap<Integer,Delivery>();
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
