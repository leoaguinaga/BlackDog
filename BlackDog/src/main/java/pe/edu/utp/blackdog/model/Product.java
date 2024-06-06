package pe.edu.utp.blackdog.model;


public class Product {
    private long product_id;
    private String name;
    private String image;
    private double price;
    private Product_Type type;

    public Product(Builder builder) {
        this.product_id = builder.product_id;
        this.name = builder.name;
        this.image = builder.image;
        this.price = builder.price;
        this.type = builder.type;
    }


    public static class Builder{
        private long product_id;
        private String name;
        private String image;
        private double price;
        private Product_Type type;

        public Builder(String name, String image, double price, Product_Type type){
            //SECCIÓN CRÍTICA
            this.product_id = 0;
            //
            this.name = name;
            this.image = image;
            this.price = price;
            this.type = type;
        }

        public Builder withProductId(long product_id){
            this.product_id = product_id;
            return this;
        }

        public Product build(){
            return new Product(this);
        }

    }

    public long getProduct_id() { return product_id;}

    public String getName() { return name;}

    public String getImage() { return image;}

    public double getPrice() { return price;}

    public Product_Type getType() { return type;}


    public static Product createProductWithoutId(String name, String image, double price, Product_Type product_type){
        return new Product.Builder(name, image, price, product_type).build();
    }

    public static Product createProduct(long product_id, String name, String image, double price, Product_Type product_type){
        return new Builder(name, image, price, product_type).withProductId(product_id).build();
    }
}
