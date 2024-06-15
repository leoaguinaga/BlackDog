<%@ page import="pe.edu.utp.blackdog.model.Product" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Obtener la lista de productos y las cantidades
    List<Product> products = (List<Product>) request.getAttribute("products");
    List<Integer> quantities = (List<Integer>) request.getAttribute("quantities");

    double totalPrice = 0.0;
%>

<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />

<section class="products" id="products">
    <jsp:include page="modal.jsp" />
    <jsp:include page="singin.jsp" />

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
        <%
            if (products != null && quantities != null && !products.isEmpty()) {
                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    int quantity = quantities.get(i);
                    double productTotal = product.getPrice() * quantity;
                    totalPrice += productTotal;
                    String base64Image = Base64.getEncoder().encodeToString(product.getImage());
        %>
        <tr>
            <td><img src="data:image/jpg;base64,<%= base64Image %>" alt="productImage" height="100px"/></td>
            <td><%= product.getName() %></td>
            <td><%= quantity %></td>
            <td><%= product.getPrice() %></td>
            <td><%= productTotal %></td>
            <td>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                    <button type="submit" class="btn btn-danger">Eliminar</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <h2>No se encontraron productos agregados al carrito.</h2>
        <%
            }
        %>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">Total</td>
            <td colspan="2"><%= totalPrice %></td>
        </tr>
        </tfoot>
    </table>

    <img src="img/qr_yape.jpg" alt="qr_yape" height="360" width="330">

    <!-- Formulario para registrar la orden -->
    <form action="${pageContext.request.contextPath}/registerOrder" method="post" enctype="multipart/form-data">
        <input type="hidden" name="totalPrice" value="<%= totalPrice %>">
        <label for="address"> Dirección</label>
        <input type="text" id="address" name="address" placeholder="Dirección para delivery">
        <label for="evidence"> Evidencia (Yape o Plin)</label>
        <input type="file" id="evidence" name="evidence" required>
        <button class="btn btn-success" type="submit">Registrar Orden</button>
    </form>

    <jsp:include page="components/footer.jsp" />
</section>
