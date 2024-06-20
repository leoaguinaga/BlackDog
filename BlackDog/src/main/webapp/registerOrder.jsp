<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% double amount = (double) request.getAttribute("amount"); %>
<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />

<img src="img/qr_yape.jpg" alt="qr_yape" height="360" width="330">

Formulario para registrar la orden
<form action="${pageContext.request.contextPath}/registerOrder" method="post" enctype="multipart/form-data">
    <input type="hidden" name="totalPrice" value="<%= amount %>">
    <label for="address"> Dirección</label>
    <input type="text" id="address" name="address" placeholder="Dirección para delivery">
    <label for="evidence"> Evidencia (Yape o Plin)</label>
    <input type="file" id="evidence" name="evidence" required>
    <button class="btn btn-success" type="submit">Registrar Orden</button>
</form>
<jsp:include page="components/footer.jsp" />
