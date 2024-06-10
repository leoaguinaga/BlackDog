package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.IngredientDAO;
import pe.edu.utp.blackdog.dao.ProductDAO;
import pe.edu.utp.blackdog.model.Ingredient;
import pe.edu.utp.blackdog.model.Product;
import pe.edu.utp.blackdog.model.Product_Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

@WebServlet("admin/registerProduct")
@MultipartConfig
public class RegisterProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        Part imagePart = req.getPart("image");
        double price = Double.parseDouble(req.getParameter("price"));

        BufferedImage bufferedImage = ImageIO.read(imagePart.getInputStream());
        try {
            Product product = Product.createProductWithoutId(name, bufferedImage, price, Product_Type.valueOf(type));

            ProductDAO productDAO = new ProductDAO();

            IngredientDAO ingredientDAO = new IngredientDAO();
            List<Ingredient> ingredients = ingredientDAO.getAllIngredients();
            ingredientDAO.close();

            if(type.equals("HAMBURGER")) {
                String imageBase64 = Base64.getEncoder().encodeToString(product.getImage());

                req.setAttribute("product", product);
                req.setAttribute("imageBase64", imageBase64);
                req.setAttribute("ingredients", ingredients);
                req.getRequestDispatcher("setIngredients.jsp").forward(req, resp);
            }else {
                productDAO.registerProduct(product);
                productDAO.close();
                resp.sendRedirect("products.jsp");
            }

        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}


