package pe.edu.utp.blackdog.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Product {
    private long product_id;
    private String name;
    private byte[] image;
    private double price;
    private Product_Type product_type;

    public Product(Builder builder) {
        this.product_id = builder.product_id;
        this.name = builder.name;
        this.image = builder.image;
        this.price = builder.price;
        this.product_type = builder.product_type;
    }

    public static class Builder {
        private long product_id;
        private String name;
        private byte[] image;
        private double price;
        private Product_Type product_type;

        public Builder(String name, byte[] image, double price, Product_Type product_type) {
            this.product_id = 0;
            this.name = name;
            this.image = image;
            this.price = price;
            this.product_type = product_type;
        }

        public Builder withProduct_id(long product_id) {
            this.product_id = product_id;
            return this;
        }

        public Product build() { return new Product(this); }
    }

    // GETTERS
    public long getProduct_id() { return product_id; }
    public String getName() { return name; }
    public byte[] getImage() { return image; }
    public double getPrice() { return price; }
    public Product_Type getProduct_type() { return product_type; }

    public static byte[] imageToByteArray(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        }
    }

    public static BufferedImage byteArrayToImage(byte[] bytes) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            return ImageIO.read(bais);
        }
    }

    public static Product createProductWithoutId(String name, BufferedImage image, double price, Product_Type product_type) throws IOException {
        byte[] imageBytes = imageToByteArray(image);
        return new Product.Builder(name, imageBytes, price, product_type).build();
    }

    public static Product createProduct(long product_id, String name, byte[] image, double price, Product_Type product_type) {
        return new Product.Builder(name, image, price, product_type).withProduct_id(product_id).build();
    }
}
