package pe.edu.utp.blackdog.interfaces;

import pe.edu.utp.blackdog.model.Product_ingredient;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Product_IngredientCrud {
    public void registerProductIngredient(Product_ingredient productIngredient) throws SQLException, NamingException;

    public List<Product_ingredient> getProductIngredients() throws SQLException, NamingException;

    public List<Product_ingredient> getProductIngredientsByProductId(long product_id) throws SQLException, NamingException;

    public Product_ingredient getProductIngredientByIngredientId(long ingredient_id) throws SQLException, NamingException;

    public void DeleteProductIngredient(long product_id) throws SQLException, NamingException;
}
