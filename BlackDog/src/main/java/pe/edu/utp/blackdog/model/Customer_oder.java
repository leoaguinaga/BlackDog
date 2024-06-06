package pe.edu.utp.blackdog.model;

import java.time.LocalDateTime;

public class Customer_oder {
    private long customer_order_id;
    private Client client;
    private LocalDateTime order_date;
    private String address;
    private double amount;
    private State state;
    private String evidence_image;

    public Customer_oder() {
    }

    public Customer_oder(long customer_order_id, Client client, LocalDateTime order_date, String address, double amount, State state, String evidence_image) {
        this.customer_order_id = customer_order_id;
        this.client = client;
        this.order_date = order_date;
        this.address = address;
        this.amount = amount;
        this.state = state;
        this.evidence_image = evidence_image;
    }

    @Override
    public String toString() {
        return "Customer_oder{" +
                "customer_order_id=" + customer_order_id +
                ", client=" + client +
                ", order_date=" + order_date +
                ", address='" + address + '\'' +
                ", amount=" + amount +
                ", state=" + state +
                ", evidence_image='" + evidence_image + '\'' +
                '}';
    }

    public long getCustomer_order_id() {
        return customer_order_id;
    }

    public void setCustomer_order_id(long customer_order_id) {
        this.customer_order_id = customer_order_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getEvidence_image() {
        return evidence_image;
    }

    public void setEvidence_image(String evidence_image) {
        this.evidence_image = evidence_image;
    }
}
