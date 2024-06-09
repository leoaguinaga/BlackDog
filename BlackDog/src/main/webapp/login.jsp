<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<h1>Iniciar Sesión</h1>
<form action="login" method="post">
    <label>Correo: <input type="text" name="email" id="email" required></label><br>
    <label>Contraseña: <input type="password" name="password" id="password" required></label><br>
    <input type="submit" value="Ingresar">
    <a href="<%= request.getContextPath() %>/index.jsp" class="button">Regresar</a>
</form>
<label class="form-check-label">No tienes una cuenta? <a href="singin.jsp">Regístrate!</a></label>
</body>
</html>