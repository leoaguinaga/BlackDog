<%--
  Created by IntelliJ IDEA.
  User: Leo
  Date: 7/06/2024
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BlackDog</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<h1>Regístrate</h1>
<form action="singin" method="post">
        <label class="form-label" for="firstName">Ingrese sus nombres</label>
        <input type="text" id="firstName" name="firstName" class="form-control" required/>
        <label class="form-label" for="lastName">Ingrese sus apellidos</label>
        <input type="text" id="lastName" name="lastName" class="form-control" required/>
        <label class="form-label" for="phone">Ingrese su número de teléfono</label>
        <input type="text" id="phone" name="phone" class="form-control" />
        <label class="form-label" for="email">Ingrese su correo</label>
        <input type="text" id="email" name="email" class="form-control" required/>
        <label class="form-label" for="pwd">Ingrese su contraseña</label>
        <input type="password" id="pwd" name="pwd" class="form-control" required/>
        <button type="submit" class="btn btn-primary">Registrar</button>
</form>
<a href="index.jsp" class="button">Regresar</a>
</body>
</html>