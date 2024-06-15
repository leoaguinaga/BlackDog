package pe.edu.utp.blackdog.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Customer_order {
    private long customer_order_id;
    private Client client;
    private LocalDateTime order_date;
    private String address;
    private double amount;
    private State state;
    private byte[] evidence_image;

    public Customer_order(Builder builder) {
        this.customer_order_id = builder.customer_order_id;
        this.client = builder.client;
        this.order_date = builder.order_date;
        this.address = builder.address;
        this.amount = builder.amount;
        this.state = builder.state;
        this.evidence_image = builder.evidence_image;
    }

    //INNER CLASS: BUILDER
    public static class Builder {
        private long customer_order_id;
        private Client client;
        private LocalDateTime order_date;
        private String address;
        private double amount;
        private State state;
        private byte[] evidence_image;

        public Builder(Client client, LocalDateTime order_date, String address, double amount, State state, byte[] evidence_image) {
            this.customer_order_id = 0;
            this.client = client;
            this.order_date = order_date;
            this.address = address;
            this.amount = amount;
            this.state = state;
            this.evidence_image = evidence_image;
        }

        public Builder withCustomer_oder_id(long customer_order_id){
            this.customer_order_id = customer_order_id;
            return this;
        }

        public Customer_order build() {
            return new Customer_order(this);
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
    public byte[] getEvidence_image() { return evidence_image;}

    public static byte[] imageToByteArray(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        }
    }

    // CREATE ORDER
    public static Customer_order createOrderWithoutId(Client client, LocalDateTime order_date, String address, double amount, BufferedImage evidence_image) throws IOException {
        byte[] imageBytes = imageToByteArray(evidence_image);
        return new Customer_order.Builder(client,order_date,address,amount, State.ON_HOLD, imageBytes).build();
    }
    public static Customer_order createOrder(long customer_order_id, Client client, LocalDateTime order_date, String address, double amount, State state, byte[] evidence_image){
        return new Customer_order.Builder(client,order_date,address,amount,state,evidence_image).withCustomer_oder_id(customer_order_id).build();
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
