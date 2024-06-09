<%@ page import="pe.edu.utp.blackdog.model.Product_ingredient" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="pe.edu.utp.blackdog.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: Leo
  Date: 8/06/2024
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Product_ingredient> productIngredientList = new LinkedList<>(); %>
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
