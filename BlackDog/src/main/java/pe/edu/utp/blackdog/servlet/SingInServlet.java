package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.ClientDAO;
import pe.edu.utp.blackdog.model.Client;
import pe.edu.utp.blackdog.service.Auth;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

@WebServlet("/singin")
public class SingInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        try {
            ClientDAO clientDAO = new ClientDAO();
            Client client = Client.createClientWithoutId(firstName, lastName, phone, email, Auth.md5(pwd));
            System.out.println(client);
            clientDAO.registerClient(client);
            clientDAO.close();

            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            session.setAttribute("userType", "client");
            session.setAttribute("name", firstName);
            resp.sendRedirect("index.jsp");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
