package pe.edu.utp.blackdog.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Product_Type {
    HAMBURGER("Burgers"), DRINK("Bebidas"), SALCHIPAPA("Salchipapas"), CHAUFA("Arroz chaufa");

    private final String displayName;

    Product_Type(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<Product_Type> getProductTypes() {
        return new ArrayList<>(Arrays.asList(Product_Type.values()));
    }

    public static Product_Type getProductType(String displayName) {
        for (Product_Type type : Product_Type.values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No Product_Type found with displayName: " + displayName);
    }
}
