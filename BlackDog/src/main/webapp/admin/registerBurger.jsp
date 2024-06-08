<%@ page import="pe.edu.utp.blackdog.dao.IngredientDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.utp.blackdog.model.Ingredient" %><%--
  Created by IntelliJ IDEA.
  User: yordi
  Date: 07/06/2024
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RegisterBurger</title>
</head>
<body>
<h1>Registrar Hamburguesa</h1>
<form action="singin" method="post">
    <label class="form-label" for="name">Nombre</label>
    <input type="text" id="name" name="name" class="form-control" required/>
    <%
        IngredientDAO ingredientDAO= new IngredientDAO();
        List<Ingredient> ingredientList = ingredientDAO.getAllIngredients();
    %>
    <br>
    <%for (Ingredient ingredient : ingredientList) {%>
    <label class="form-label" for="<%=ingredient.getName().toLowerCase()%>"><%=ingredient.getName()%></label>
    <input type="number" id="<%=ingredient.getName().toLowerCase()%>" name="<%=ingredient.getName().toLowerCase()%>" class="form-control" required>
    <br>
    <%}%>
    <label class="form-label" for="price">Precio</label>
    <input type="text" id="price" name="price" class="form-control" required/>
    <br>
    <label class="form-label" for="image">Imagen</label>
    <input type="file" id="image" name="image" class="form-control" required/>
    <br>
    <button type="submit" class="btn btn-primary">Registrar</button>
</form>
</body>
</html>
