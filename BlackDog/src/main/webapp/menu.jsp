<%@ page import="pe.edu.utp.blackdog.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Base64" %>
<%@ page import="pe.edu.utp.blackdog.model.Product_Type" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    Map<Long, String> productIngredientsMap = (Map<Long, String>) request.getAttribute("productIngredientsMap");
    Product_Type product_type = (Product_Type) request.getAttribute("productType");
%>

<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />

<section class="products" id="products">
    <div class="middle-text">
        <h4>Nuestros Productos</h4>

        <form><input type="text" value="HAMBURGER" hidden name="type"><button formaction="menu" formmethod="post" type="submit" class="btn-primary">Hamburguesas</button></form>
        <form><input type="text" value="CHAUFA" hidden name="type"><button formaction="menu" formmethod="post" type="submit" class="btn-primary">Chaufas</button></form>
        <form><input type="text" value="SALCHIPAPA" hidden name="type"><button formaction="menu" formmethod="post" type="submit" class="btn-primary">Salchipapas</button></form>
        <form><input type="text" value="DRINK" hidden name="type"><button formaction="menu" formmethod="post" type="submit" class="btn-primary">Bebidas</button></form>
    </div>

    <jsp:include page="modal.jsp" />
    <jsp:include page="singin.jsp" />

    <style>
        .quantity-control {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .quantity-btn {
            width: 30px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
            cursor: pointer;
        }

        .quantity-btn:hover {
            background-color: #e9e9e9;
        }

        .quantity-control input {
            width: 50px;
            text-align: center;
            border: 1px solid #ccc;
            margin: 0 5px;
        }
    </style>

    <section class="list-products" id="list-products">
        <% if (products != null && !products.isEmpty()) {%>
        <% for (Product product : products) {
            String base64Image = Base64.getEncoder().encodeToString(product.getImage());
            String ingredients = productIngredientsMap != null ? productIngredientsMap.get(product.getProduct_id()) : null;
            String width;
            if(product_type == Product_Type.DRINK) {
                width = "50px";
            }else width = "auto";
        %>
        <div class="card" style="width: 18rem;">

            <img src="data:image/jpg;base64,<%= base64Image %>" class="card-img-top" alt="burger" width="<%=width%>" height="240px">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/car" method="post">
                    <input type="hidden" name="action" value="add">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <h5 class="card-subtitle"><b>Precio: </b><%= product.getPrice() %></h5>
                    <% if (ingredients != null) { %>
                    <p class="card-text"><%= ingredients %></p>
                    <% } %>

                    <div class="quantity-control">
                        <button type="button" class="quantity-btn" onclick="decrement(<%= product.getProduct_id() %>)">-</button>
                        <input type="text" id="quantity-<%= product.getProduct_id() %>" name="quantity" value="1" readonly>
                        <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                        <button type="button" class="quantity-btn" onclick="increment(<%= product.getProduct_id() %>)">+</button>
                    </div>
                    <button class="btn btn-primary" type="submit">Agregar al carrito</button>
                </form>
            </div>
        </div>
        <%} } else { %>
        <h2>No se encontraron productos en esta categoría</h2>
        <% } %>
    </section>

</section>

<jsp:include page="components/footer.jsp" />

<script>
    function increment(productId) {
        var quantityInput = document.getElementById('quantity-' + productId);
        var currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
    }

    function decrement(productId) {
        var quantityInput = document.getElementById('quantity-' + productId);
        var currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }
    }
</script>
