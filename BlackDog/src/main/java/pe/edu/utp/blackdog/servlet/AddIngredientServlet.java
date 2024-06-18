package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.IngredientDAO;
import pe.edu.utp.blackdog.model.Ingredient;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/addIngredient")
public class AddIngredientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        try {
            IngredientDAO ingredientDAO = new IngredientDAO();
            Ingredient ingredient = Ingredient.createIngredientWithoutId(name, price);

            ingredientDAO.registerIngredient(ingredient);
            ingredientDAO.close();

            resp.sendRedirect("ingredients");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
