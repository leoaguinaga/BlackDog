<%@ page import="java.util.List" %>
<%@ page import="pe.edu.utp.blackdog.model.Product" %>
<%@ page import="pe.edu.utp.blackdog.dao.ProductDAO" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Product> products = (List<Product>) request.getAttribute("products"); %>

<jsp:include page="components/header.jsp" />
<jsp:include page="components/sidebar.jsp" />
<jsp:include page="components/topbar.jsp" />
<!-- Content Products -->
<div class="container-fluid">
    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Administrate Products</h1>
    </div>
    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <% if (products != null && !products.isEmpty()) {%>
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Type</th>
                        <th>Image</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Product product : products) {
                        String base64Image = Base64.getEncoder().encodeToString(product.getImage());%>
                    <tr>
                        <td><%= product.getProduct_id() %></td>
                        <td><%= product.getName()%></td>
                        <td><%= product.getPrice() %></td>
                        <td><%= product.getProduct_type().getDisplayName() %></td>
                        <td><img src="data:image/jpg;base64,<%= base64Image %>" alt="productImage" height="100px"/></td>
                        <td> <a href="${pageContext.request.contextPath}/admin/seeProductIngredient?id=<%= product.getProduct_id() %>"> Ver ingredientes del producto </a> </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/deleteProduct?id=<%= product.getProduct_id() %>"><img src="img/borrar.png" alt="delete image" height="30px"></a>
                            <a href="${pageContext.request.contextPath}/admin/updateProduct?id=<%= product.getProduct_id() %>"><img src="img/editar.png" alt="update image" height="30px"></a>
                        </td>
                    </tr>
                    <% } %>
                    <% } else  { %>
                    <h2>No se encontraron productos en la base de datos</h2>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <a href="addProduct.jsp" class="btn btn-success btn-icon-split">
            <span class="text">Añadir nuevo producto</span>
        </a>
    </div>
</div>
<jsp:include page="components/footer.jsp" />
<jsp:include page="components/scripts.jsp" />