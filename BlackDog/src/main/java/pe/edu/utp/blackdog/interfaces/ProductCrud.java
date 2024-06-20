package pe.edu.utp.blackdog.interfaces;

import pe.edu.utp.blackdog.model.Product;
import pe.edu.utp.blackdog.model.Product_Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductCrud {
    public void registerProduct(Product product) throws SQLException;

    public List<Product> getAllProducts() throws SQLException;

    public List<Product> getProductsByType(Product_Type productType) throws SQLException;

    public Product getProductById(long product_id) throws SQLException;

    public void updateProduct(Product product, long product_id) throws SQLException;

    public void deleteProduct(long product_id) throws SQLException;

    public Product getLastProduct() throws SQLException;
}
