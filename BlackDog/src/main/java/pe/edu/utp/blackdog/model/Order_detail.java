package pe.edu.utp.blackdog.model;

public class Order_detail {
    private Customer_order customerOder;
    private Product product;

    public Order_detail(Customer_order customerOder, Product product) {
        this.customerOder = customerOder;
        this.product = product;
    }

    public Order_detail(Builder builder) {
        this.customerOder = builder.customerOder;
        this.product = builder.product;
    }

    //INNER CLASS: BUILDER
    public static class Builder {
        private Customer_order customerOder;
        private Product product;

        public Builder(Customer_order customerOder, Product product) {
            this.customerOder = customerOder;
            this.product = product;
        }

        public Order_detail build() {
            return new Order_detail(this);
        }
    }

    // GETTERS
    public Customer_order getCustomerOder() {
        return customerOder;
    }
    public Product getProduct() {
        return product;
    }

    // CREATE INGREDIENT
    public static Order_detail createOrderDetail(Customer_order customerOder, Product product){
        return new Order_detail.Builder(customerOder, product).build();
    }

    @Override
    public String toString() {
        return "Order_detail{" +
                "customerOder=" + customerOder +
                ", product=" + product +
                '}';
    }
}
