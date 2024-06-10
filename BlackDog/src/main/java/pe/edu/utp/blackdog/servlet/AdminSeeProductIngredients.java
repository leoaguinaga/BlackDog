package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.IngredientDAO;
import pe.edu.utp.blackdog.dao.ProductDAO;
import pe.edu.utp.blackdog.dao.Product_ingredientDAO;
import pe.edu.utp.blackdog.model.Ingredient;
import pe.edu.utp.blackdog.model.Product;
import pe.edu.utp.blackdog.model.Product_ingredient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("admin/seeProductIngredients")
public class AdminSeeProductIngredients extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        try{
            ProductDAO productDAO = new ProductDAO();
            IngredientDAO ingredientDAO = new IngredientDAO();
            Product_ingredientDAO product_ingredientDAO = new Product_ingredientDAO();

            Product product = productDAO.getProductById(id);
            HashMap<Ingredient, Integer> quantityIngredient = new HashMap<>();
            List<Ingredient> ingredients = new ArrayList<>();
            List<Product_ingredient> product_ingredients = product_ingredientDAO.getProductIngredientsByProductId(id);
            for(Product_ingredient productIngredient : product_ingredients){
                ingredients.add(ingredientDAO.getIngredientById(productIngredient.getIngredient_id()));
                quantityIngredient.put(ingredientDAO.getIngredientById(productIngredient.getIngredient_id()), productIngredient.getQuantity());
            }

            productDAO.close();
            ingredientDAO.close();
            product_ingredientDAO.close();

            req.setAttribute("product", product);
            req.setAttribute("ingredients", ingredients);
            req.setAttribute("quantityIngredient", quantityIngredient);


        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
