package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.ProductDAO;
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

        try {
            ProductDAO productDAO = new ProductDAO();
            if(!type.equals("HAMBURGER")) {
                BufferedImage bufferedImage = ImageIO.read(imagePart.getInputStream());
                Product product = Product.createProductWithoutId(name, bufferedImage, price, Product_Type.valueOf(type));
                productDAO.registerProduct(product);
                resp.sendRedirect("admin/products.jsp");
            }
            resp.sendRedirect("admin/addBurger.jsp");

        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}


