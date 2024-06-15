<%@ page import="pe.edu.utp.blackdog.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Base64" %>
<%@ page import="pe.edu.utp.blackdog.model.Product_Type" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    Map<Long, String> productIngredientsMap = (Map<Long, String>) request.getAttribute("productIngredientsMap");
%>

<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />

<section class="products" id="products">
    <div class="middle-text">
        <h4>Nuestros Productos</h4>
        <h2>
            <a href="${pageContext.request.contextPath}/menu?type=HAMBURGER">Hamburguesas</a> &nbsp; &nbsp;
            <a href="${pageContext.request.contextPath}/menu?type=CHAUFA">Chaufas</a> &nbsp; &nbsp;
            <a href="${pageContext.request.contextPath}/menu?type=SALCHIPAPA">Salchipapas</a> &nbsp; &nbsp;
            <a href="${pageContext.request.contextPath}/menu?type=DRINK">Bebidas</a> &nbsp; &nbsp;
        </h2>
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
            String ingredients = productIngredientsMap.get(product.getProduct_id());
        %>
        <div class="card" style="width: 18rem;">
            <img src="data:image/jpg;base64,<%= base64Image %>" class="card-img-top" alt="burger">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="action" value="add">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <h5 class="card-subtitle"><b>Precio: </b><%= product.getPrice() %></h5>
                    <p class="card-text"><%= ingredients %></p>

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

    <form action="${pageContext.request.contextPath}/checkout" method="post">
        <button class="btn btn-success" type="submit">Ver carrito</button>
    </form>
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
