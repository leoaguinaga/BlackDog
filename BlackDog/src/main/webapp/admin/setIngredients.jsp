<%@ page import="pe.edu.utp.blackdog.model.Product_ingredient" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="pe.edu.utp.blackdog.model.Product" %>
<%@ page import="pe.edu.utp.blackdog.model.Ingredient" %><%--
  Created by IntelliJ IDEA.
  User: Leo
  Date: 8/06/2024
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Product_ingredient> productIngredientList = new LinkedList<>(); %>
<% List<Ingredient> ingredients = (List<Ingredient>) request.getRequestDispatcher("ingredients"); %>
<% Product product = (Product) request.getRequestDispatcher("product"); %>
<% double price = 0.0; %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/sidebar.jsp" />
<jsp:include page="components/topbar.jsp" />
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Add Products</h1>
    </div>
    <div class="container mt-5">
        <form action="addIngredient" method="post">

            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <% if (ingredients != null && !ingredients.isEmpty()) { %>
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for (Ingredient ingredient : ingredients) { %>
                            <tr>
                                <td><%= ingredient.getIngredient_id() %></td>
                                <td><%= ingredient.getName()%></td>
                                <td><%= ingredient.getPrice() %></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/deleteIngredient?id=<%= ingredient.getIngredient_id() %>"><img src="img/borrar.png" alt="delete image" height="30px"></a>
                                    <a href="${pageContext.request.contextPath}/admin/updateIngredientRedirect?id=<%= ingredient.getIngredient_id() %>"><img src="img/editar.png" alt="update image" height="30px"></a>
                                </td>
                            </tr>
                            <% } %>
                            <% } else  { %>
                            <h2>No se encontraron ingredientes en la base de datos</h2>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Introduce the ingredients name">
            </div>

            <div class="form-group">
                <label for="price">Número</label>
                <input type="number" class="form-control" id="price" name="price" placeholder="Introduce the ingredients price">
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp" />
<jsp:include page="components/scripts.jsp" />
