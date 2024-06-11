package pe.edu.utp.blackdog.servlet;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/AddToCar")
public class AddToCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity = Integer.parseInt(req.getParameter("id"));
        long id = Long.parseLong(req.getParameter("quantity"));

        HttpSession session = req.getSession();
        try {
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductoById(id);

            HashMap<Product, Integer> saleCar = (HashMap<Product, Integer>) session.getAttribute("saleCar");

            product.setStock(product.getStock() - quantity);
            productDAO.updateProduct(product, id);
            saleCar.put(product, quantity);
            resp.sendRedirect("Products");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
