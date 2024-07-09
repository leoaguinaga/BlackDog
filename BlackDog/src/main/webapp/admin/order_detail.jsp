<%@ page import="pe.edu.utp.blackdog.model.Customer_order" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.utp.blackdog.model.Order_detail" %>
<%@ page import="java.util.Base64" %>
<%@ page import="pe.edu.utp.blackdog.model.State" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Order_detail> order_detailList = (List<Order_detail>) request.getAttribute("order_detailList"); %>
<% Customer_order customer_order = (Customer_order) request.getAttribute("customer_order"); %>

<jsp:include page="components/header.jsp" />
<jsp:include page="components/sidebar.jsp" />
<jsp:include page="components/topbar.jsp" />
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Order detail</h1>
    </div>

    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <% if (order_detailList != null && !order_detailList.isEmpty()) {%>

                    El pedido #<%=customer_order.getCustomer_order_id()%> se realizó el <%=customer_order.getOrderDateOnly()%> a las <%= customer_order.getOrderTimeOnly()%> y se encuentra <%=customer_order.getState().getDisplayName()%>.
                    <br>
                    <strong>Nombre del cliente: </strong><%=customer_order.getClient().getFirst_name()%> <%=customer_order.getClient().getLast_name()%>.
                    <br>
                    <strong>Dirección: </strong><%=customer_order.getAddress()%>.
                    <br>
                    <tbody>
                    <thead>
                    <tr>
                        <th colspan="2"><strong>Producto</strong></th>
                        <th><strong>Cantidad</strong></th>
                    </tr>
                    </thead>
                    <% for (Order_detail orderDetail : order_detailList) { %>
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/image?img=<%= orderDetail.getProduct().getImage() %>" class="card-img-top" alt="Image" height="100px"/></td>
                        <td><%= orderDetail.getProduct().getName()%></td>
                        <td><%= orderDetail.getQuantity()%></td>
                    </tr>
                    <% } %>
                    <% } else  { %>
                    <h2>No se encontraron ordenes en la base de datos</h2>
                    <% } %>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th colspan="2"><strong>Total</strong></th>
                        <td><%=customer_order.getAmount()%></td>
                    </tr>
                    </tfoot>
                </table>

                <%
                    State currentState = customer_order.getState();
                    if (currentState == State.ON_HOLD) {
                %>
                <a href="${pageContext.request.contextPath}/admin/alterStateOrder?state=ON_PROCESS&id=<%= customer_order.getCustomer_order_id() %>">Preparar pedido</a>
                <br>
                <a href="${pageContext.request.contextPath}/admin/alterStateOrder?state=CANCELED&id=<%= customer_order.getCustomer_order_id() %>">Cancelar pedido</a>
                <br>
                <%
                } else if (currentState == State.ON_PROCESS) {
                %>
                <a href="${pageContext.request.contextPath}/admin/alterStateOrder?state=ON_THE_WAY&id=<%= customer_order.getCustomer_order_id() %>">Pedido en camino</a>
                <br>
                <a href="${pageContext.request.contextPath}/admin/alterStateOrder?state=CANCELED&id=<%= customer_order.getCustomer_order_id() %>">Cancelar pedido</a>
                <br>
                <%
                } else if (currentState == State.ON_THE_WAY) {
                %>
                <a href="${pageContext.request.contextPath}/admin/alterStateOrder?state=FINISHED&id=<%= customer_order.getCustomer_order_id() %>">Entregado/Finalizar</a>
                <br>
                <%
                    }
                %>
                <strong>Evidencia de pago: </strong>
                <img src="${pageContext.request.contextPath}/image?img=<%= customer_order.getEvidence_image() %>" class="card-img-top" alt="Image" height="150px" width="80px"/>
            </div>
        </div>
    </div>

</div>
<jsp:include page="components/footer.jsp" />
<jsp:include page="components/scripts.jsp" />