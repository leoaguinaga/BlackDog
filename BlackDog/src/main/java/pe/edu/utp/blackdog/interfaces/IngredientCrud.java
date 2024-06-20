package pe.edu.utp.blackdog.interfaces;

import pe.edu.utp.blackdog.model.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IngredientCrud {
    public void registerIngredient(Ingredient ingredient) throws SQLException;

    public List<Ingredient> getAllIngredients() throws SQLException;

    public Ingredient getIngredientById(long ingredient_id) throws SQLException;

    public void updateIngredient(Ingredient ingredient, long ingredient_id) throws SQLException;

    public void deleteIngredient(long ingredient_id) throws SQLException;

    public String getIngredientNameById(long ingredient_id) throws SQLException;
}
