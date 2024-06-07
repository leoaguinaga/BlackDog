package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.AdministradorDAO;
import pe.edu.utp.blackdog.model.Product;
import pe.edu.utp.blackdog.service.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Auth auth = new Auth();
            String userType = auth.getTipoUsuario(email, password);

            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            session.setAttribute("userType", userType);
            if ("admin".equals(userType)) {
                AdministradorDAO administradorDAO =  new AdministradorDAO();
                String name = administradorDAO.getAdministratorNameByEmail(email);
                administradorDAO.close();
                session.setAttribute("name", name);
                resp.sendRedirect("admin/Dashboard.jsp");
            } else if ("client".equals(userType)) {
                resp.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            String credenciales = String.format("E:%s  P:%s", password, email);
            req.setAttribute("message", e.getMessage() + credenciales);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
