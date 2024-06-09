package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.IngredientDAO;
import pe.edu.utp.blackdog.dao.ProductDAO;
import pe.edu.utp.blackdog.model.Ingredient;
import pe.edu.utp.blackdog.model.Product;
import pe.edu.utp.blackdog.model.Product_Type;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        String type = req.getParameter("type");
        double price = 0.0;
        try {
            ProductDAO productDAO = new ProductDAO();
            IngredientDAO ingredientDAO = new IngredientDAO();
            Product product = Product.createProductWithoutId(name, image, 0.0, Product_Type.valueOf(type));
            productDAO.registerProduct(product);
            productDAO.close();
            List<Ingredient> ingredients = ingredientDAO.getAllIngredients();
            ingredientDAO.close();
            req.setAttribute("product", product);
            req.setAttribute("ingredients", ingredients);
            req.getRequestDispatcher("setIngredients.jsp").forward(req, resp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
