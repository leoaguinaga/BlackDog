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

    public Customer_oder(Builder builder) {
    }

    //INNER CLASS: BUILDER
    public static class Builder {
        private long customer_order_id;
        private Client client;
        private LocalDateTime order_date;
        private String address;
        private double amount;
        private State state;
        private String evidence_image;

        public Builder(long customer_order_id, Client client, LocalDateTime order_date, String address, double amount, State state, String evidence_image) {
            this.customer_order_id = customer_order_id;
            this.client = client;
            this.order_date = order_date;
            this.address = address;
            this.amount = amount;
            this.state = state;
            this.evidence_image = evidence_image;
        }

        public Customer_oder build() {
            return new Customer_oder(this);
        }
    }

    // GETTERS
    public long getCustomer_order_id() {
        return customer_order_id;
    }
    public Client getClient() {
        return client;
    }
    public LocalDateTime getOrder_date() {
        return order_date;
    }
    public String getAddress() {
        return address;
    }
    public double getAmount() {
        return amount;
    }
    public State getState() {
        return state;
    }
    public String getEvidence_image() {
        return evidence_image;
    }

    // CREATE ORDER
    public static Customer_oder createOrder(long customer_order_id, Client client, LocalDateTime order_date, String address, double amount, State state, String evidence_image){
        return new Customer_oder.Builder(customer_order_id,client,order_date,address,amount,state,evidence_image).build();
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
}
