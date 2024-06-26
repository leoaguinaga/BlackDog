<%@ page import="pe.edu.utp.blackdog.model.Product_ingredient" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="pe.edu.utp.blackdog.model.Product" %>
<%@ page import="pe.edu.utp.blackdog.model.Ingredient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Ingredient> ingredients = (List<Ingredient>) request.getAttribute("ingredients"); %>
<% Product product = (Product) request.getAttribute("product"); %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/sidebar.jsp" />
<jsp:include page="components/topbar.jsp" />
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Add Ingredients</h1>
    </div>
    <div class="container mt-5">
        <form action="setIngredients" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Name: <%=product.getName()%></label>
                <input type="hidden" id="name" name="name" value="<%=product.getName()%>">
            </div>

            <div class="form-group">
                <label for="price">Price: <%=product.getPrice()%></label>
                <input type="hidden" id="price" name="price" value="<%=product.getPrice()%>">
            </div>

            <div>
                <input type="hidden" id="imageBase64" name="imageBase64" value="<%=request.getAttribute("imageBase64")%>">
            </div>

            <div>
                <input type="hidden" id="type" name="type" value="<%=product.getProduct_type().toString()%>">
            </div>

            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <% if (ingredients != null && !ingredients.isEmpty()) { %>
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for (Ingredient ingredient : ingredients) { %>
                            <tr>
                                <td><%= ingredient.getName()%></td>
                                <td><%= ingredient.getPrice() %></td>
                                <td>
                                    <input type="hidden" name="ingredientId" value="<%= ingredient.getIngredient_id() %>">
                                    <input type="number" class="form-control" value="0" name="quantity_<%= ingredient.getIngredient_id() %>" placeholder="Quantity">
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

            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp" />
<jsp:include page="components/scripts.jsp" />
