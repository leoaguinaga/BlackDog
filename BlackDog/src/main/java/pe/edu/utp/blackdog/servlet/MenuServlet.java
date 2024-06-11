package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.ProductDAO;
import pe.edu.utp.blackdog.dao.Product_ingredientDAO;
import pe.edu.utp.blackdog.dao.IngredientDAO;
import pe.edu.utp.blackdog.model.Product;
import pe.edu.utp.blackdog.model.Product_Type;
import pe.edu.utp.blackdog.model.Product_ingredient;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product_Type productType = Product_Type.valueOf(req.getParameter("type"));
        try {
            ProductDAO productDAO = new ProductDAO();
            Product_ingredientDAO productIngredientDAO = new Product_ingredientDAO();
            IngredientDAO ingredientDAO = new IngredientDAO();

            List<Product> products = productDAO.getProductsByType(productType);
            Map<Long, String> productIngredientsMap = new HashMap<>();

            for (Product product : products) {
                List<Product_ingredient> ingredients = productIngredientDAO.getProductIngredientsByProductId(product.getProduct_id());
                StringBuilder ingredientList = new StringBuilder();
                for (int i = 0; i < ingredients.size(); i++) {
                    String ingredientName = ingredientDAO.getIngredientNameById(ingredients.get(i).getIngredient_id());
                    ingredientList.append(ingredientName);
                    if (i < ingredients.size() - 1) {
                        ingredientList.append(", ");
                    } else {
                        ingredientList.append(".");
                    }
                }
                productIngredientsMap.put(product.getProduct_id(), ingredientList.toString());
            }

            req.setAttribute("products", products);
            req.setAttribute("productIngredientsMap", productIngredientsMap);
            req.getRequestDispatcher("menu.jsp").forward(req, resp);

        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
