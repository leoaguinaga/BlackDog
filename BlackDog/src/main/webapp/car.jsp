<%@ page import="pe.edu.utp.blackdog.model.Product" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Obtener la lista de productos y las cantidades
    List<Product> products = (List<Product>) request.getAttribute("products");
    List<Integer> quantities = (List<Integer>) request.getAttribute("quantities");
    HttpSession session1 = request.getSession(false);
    double totalPrice = 0.0;
%>

<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />
<br><br>
<section class="products" id="products">
    <jsp:include page="modal.jsp" />
    <jsp:include page="singin.jsp" />

    <%
        if (products != null && quantities != null && !products.isEmpty()) {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                int quantity = quantities.get(i);
                double productTotal = product.getPrice() * quantity;
                totalPrice += productTotal;
    %>

    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

        <thead>
        <tr>
            <th colspan="2">Producto</th>
            <th>Cantidad</th>
            <th>Precio unitario</th>
            <th>Monto</th>
            <th>Acciones</th>
        </tr>
        </thead>

        <tbody>

        <tr>
            <td><img src="${pageContext.request.contextPath}/image?img=<%= product.getImage() %>" class="card-img-top" alt="Image" height="100px"/></td>
            <td><%= product.getName() %></td>
            <td><%= quantity %></td>
            <td><%= product.getPrice() %></td>
            <td><%= productTotal %></td>
            <td>
                <form action="${pageContext.request.contextPath}/car" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                    <button type="submit" class="btn btn-danger">Eliminar</button>
                </form>
            </td>
        </tr>

        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">Total</td>
            <td colspan="2"><%= totalPrice %></td>
        </tr>
        </tfoot>
    </table>
    <%
        }
    } else {
    %>
    <h2>No se encontraron productos agregados al carrito.</h2>
    <%
        }
    %>
    <a href="${pageContext.request.contextPath}/RegisterOrderRedirect?amount=<%= totalPrice %>" class="btn btn-primary">Registrar compra!</a>

    <jsp:include page="components/footer.jsp" />
</section>