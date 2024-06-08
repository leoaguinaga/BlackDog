<%@ page import="pe.edu.utp.blackdog.model.Customer_order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Customer_order> customerOrders = (List<Customer_order>) request.getAttribute("customerOrders"); %>
<html>
<head>
    <title>Administrator Dashboard</title>
</head>
<body>
<a href="showAllOrders">Pedidos</a>
<a href="showProducts">Productos</a>
<a href="../logout">Cerrar Sesión</a>
<h1>Bienvenido al portal de administración</h1>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
    <% if (customerOrders != null && !customerOrders.isEmpty()) { %>
    <thead>
    <tr>
        <th>Id</th>
        <th>Client</th>
        <th>Date</th>
        <th>Address</th>
        <th>Amount</th>
        <th>State</th>
        <th>Image</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% for (Customer_order customerOrder : customerOrders) { %>
    <tr>
        <td><%= customerOrder.getCustomer_order_id() %></td>
        <td><%= customerOrder.getClient().getFirst_name() + " " + customerOrder.getClient().getLast_name() %></td>
        <td><%= customerOrder.getOrder_date() %></td>
        <td><%= customerOrder.getAddress() %></td>
        <td><%= customerOrder.getState() %></td>
        <td><img src="img/products/<%= customerOrder.getEvidence_image() %>" alt="" height="70px"></td>
        <td>
            <a href="${pageContext.request.contextPath}/DeleteProduct?id=<%= customerOrder.getCustomer_order_id() %>"><img src="img/borrar.png" alt="delete image" height="30px"></a>
            <a href="${pageContext.request.contextPath}/admin/UpdateProductRedirect?id=<%= customerOrder.getCustomer_order_id() %>"><img src="img/editar.png" alt="update image" height="30px"></a>
        </td>
    </tr>
    <% } %>
    <% } else  { %>
    <h2>No se encontraron ordenes en la base de datos</h2>
    <% } %>
    </tbody>
</table>
</body>
</html>