package pe.edu.utp.blackdog.model;

public class Order_detail {
    private Customer_oder customerOder;
    private Product product;

    public Order_detail(Customer_oder customerOder, Product product) {
        this.customerOder = customerOder;
        this.product = product;
    }

    public Order_detail(Builder builder) {
    }

    //INNER CLASS: BUILDER
    public static class Builder {
        private Customer_oder customerOder;
        private Product product;

        public Builder(Customer_oder customerOder, Product product) {
            this.customerOder = customerOder;
            this.product = product;
        }

        public Order_detail build() {
            return new Order_detail(this);
        }
    }

    // GETTERS

    public Customer_oder getCustomerOder() {
        return customerOder;
    }
    public Product getProduct() {
        return product;
    }

    // CREATE INGREDIENT
    public static Order_detail createOrder(Customer_oder customerOder, Product product){
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
