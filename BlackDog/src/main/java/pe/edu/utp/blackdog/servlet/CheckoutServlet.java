package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.ProductDAO;
import pe.edu.utp.blackdog.model.Product;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<Long, Integer> car = (Map<Long, Integer>) session.getAttribute("car");

        try {
                ProductDAO productDAO = new ProductDAO();
                List<Product> productList = new ArrayList<>();
                List<Integer> quantities = new ArrayList<>();

                for (Map.Entry<Long, Integer> entry : car.entrySet()) {
                    Long productId = entry.getKey();
                    Integer quantity = entry.getValue();
                    Product product = productDAO.getProductById(productId);

                    productList.add(product);
                    quantities.add(quantity);
                }
                productDAO.close();
                req.setAttribute("products", productList);
                req.setAttribute("quantities", quantities);

                // Enviar los datos a car.jsp
                req.getRequestDispatcher("/car.jsp").forward(req, resp);
        } catch (Exception e) {
            String msg = "El carrito está vacío";
            req.setAttribute("message", msg + ". " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
