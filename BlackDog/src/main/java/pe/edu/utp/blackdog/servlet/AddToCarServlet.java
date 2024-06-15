package pe.edu.utp.blackdog.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addToCart")
public class AddToCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        Long productId = Long.parseLong(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Obtener la sesión actual
        HttpSession session = request.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Actualizar la cantidad del producto en el carrito
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);

        // Guardar el carrito en la sesión
        session.setAttribute("cart", cart);

        // Redireccionar a la página del menu
        response.sendRedirect(request.getContextPath() + "/menu?type=HAMBURGER");
    }
}

