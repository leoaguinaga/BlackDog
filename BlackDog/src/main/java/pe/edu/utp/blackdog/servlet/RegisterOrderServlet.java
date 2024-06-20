package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.ClientDAO;
import pe.edu.utp.blackdog.dao.Customer_orderDAO;
import pe.edu.utp.blackdog.dao.Order_detailDAO;
import pe.edu.utp.blackdog.dao.ProductDAO;
import pe.edu.utp.blackdog.model.Client;
import pe.edu.utp.blackdog.model.Customer_order;
import pe.edu.utp.blackdog.model.Order_detail;
import pe.edu.utp.blackdog.model.Product;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

@MultipartConfig
@WebServlet("/registerOrder")
public class RegisterOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email_client = (String) session.getAttribute("email");
        double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
        String address = req.getParameter("address");
        Part filePart = req.getPart("evidence");

        Map<Long, Integer> car = (Map<Long, Integer>) session.getAttribute("car");

        BufferedImage evidence = ImageIO.read(filePart.getInputStream());

        if (email_client==null){
            String msg = "Inicia sesión para poder registrar tu pedido";
            req.setAttribute("message", msg);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else {
            try {
                Customer_orderDAO customer_orderDAO = new Customer_orderDAO();
                Order_detailDAO order_detailDAO = new Order_detailDAO();
                ProductDAO productDAO = new ProductDAO();
                ClientDAO clientDAO = new ClientDAO();


                Client client = clientDAO.getClientByEmail(email_client);

                // Crear la orden
                Customer_order customerOrder =
                        Customer_order
                                .createOrderWithoutId
                                        (client, LocalDateTime.now(), address, totalPrice, evidence);

                // Registrar la orden en la base de datos
                customer_orderDAO.registerOrder(customerOrder);
                Customer_order co = customer_orderDAO.getLastCustomer_order();

                if (car != null && !car.isEmpty()) {
                    for (Map.Entry<Long, Integer> entry : car.entrySet()) {
                        Long productId = entry.getKey();
                        Integer quantity = entry.getValue();

                        Product product = productDAO.getProductById(productId);

                        Order_detail orderDetail = Order_detail.createOrderDetail(
                                co, product, quantity);

                        // Registrar el detalle de la orden en la base de datos
                        order_detailDAO.registerOrder_detail(orderDetail);
                    }
                }

                clientDAO.close();
                productDAO.close();
                customer_orderDAO.close();
                order_detailDAO.close();

                // Redirigir a la página de confirmación
                req.setAttribute("customerOrder", co);
                req.getRequestDispatcher("orderConfirmation.jsp").forward(req, resp);


            } catch (Exception e) {
                String msg = "Error al registrar la orden";
                req.setAttribute("message", msg + ". " + e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }
    }
}