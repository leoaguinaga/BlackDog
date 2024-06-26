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
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strProductType = req.getParameter("type");
        Product_Type productType = (strProductType == null || strProductType.isEmpty() || strProductType.isBlank()) ? Product_Type.HAMBURGER : Product_Type.valueOf(strProductType);
         
        try {
            ProductDAO productDAO = new ProductDAO();
            Product_ingredientDAO productIngredientDAO = new Product_ingredientDAO();
            IngredientDAO ingredientDAO = new IngredientDAO();

            List<Product> products = productDAO.getProductsByType(productType);
            Map<Long, String> productIngredientsMap = new HashMap<>();

            if(!products.isEmpty() && productType == Product_Type.HAMBURGER) {
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
            }

            req.setAttribute("products", products);
            req.setAttribute("productIngredientsMap", productIngredientsMap);
            req.setAttribute("productType", productType);
            req.getRequestDispatcher("menu.jsp").forward(req, resp);

            productDAO.close();
            productIngredientDAO.close();
            ingredientDAO.close();

        } catch (Exception e) {
            String msg = "No se puede ver el menú en este momento. Inténtalo más tarde";
            req.setAttribute("message", msg + ". " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}

