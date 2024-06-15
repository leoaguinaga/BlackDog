package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.ClientDAO;
import pe.edu.utp.blackdog.dao.Customer_orderDAO;
import pe.edu.utp.blackdog.model.Client;
import pe.edu.utp.blackdog.model.Customer_order;

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

        BufferedImage evidence = ImageIO.read(filePart.getInputStream());
        try {
            Customer_orderDAO customer_orderDAO = new Customer_orderDAO();
            ClientDAO clientDAO = new ClientDAO();
            Client client = clientDAO.getClientByEmail(email_client);

            // Crear la orden
            Customer_order customerOrder =
                    Customer_order
                            .createOrderWithoutId
                                    (client, LocalDateTime.now(), address, totalPrice, evidence);

            // Registrar la orden en la base de datos
            customer_orderDAO.registerOrder(customerOrder);

            // Redirigir a la página de confirmación
            req.setAttribute("customerOrder", customerOrder);
            req.getRequestDispatcher("orderConfirmation.jsp").forward(req, resp);

        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
    }
}