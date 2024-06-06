package pe.edu.utp.blackdog.model;

public class Order_detail {
    private Customer_oder customerOder;
    private Product product;

    public Order_detail(Customer_oder customerOder, Product product) {
        this.customerOder = customerOder;
        this.product = product;
    }

    public Order_detail() {
    }

    @Override
    public String toString() {
        return "Order_detail{" +
                "customerOder=" + customerOder +
                ", product=" + product +
                '}';
    }

    public Customer_oder getCustomerOder() {
        return customerOder;
    }

    public void setCustomerOder(Customer_oder customerOder) {
        this.customerOder = customerOder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
