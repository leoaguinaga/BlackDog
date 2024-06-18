<%@ page import="pe.edu.utp.blackdog.model.Customer_order" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.utp.blackdog.model.Order_detail" %>
<%@ page import="java.util.Base64" %>
<%@ page import="pe.edu.utp.blackdog.model.State" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Order_detail> order_detailList = (List<Order_detail>) request.getAttribute("order_detailList"); %>
<% Customer_order customer_order = (Customer_order) request.getAttribute("customer_order"); %>
<% String base64Evidence = Base64.getEncoder().encodeToString(customer_order.getEvidence_image());%>

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
                    <%String base64Product = Base64.getEncoder().encodeToString(orderDetail.getProduct().getImage());%>
                    <tr>
                        <td><img src="data:image/jpg;base64,<%= base64Product %>" alt="productImage" height="100px"/></td>
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

                <a href="${pageContext.request.contextPath}/admin/alterStateOrder?state=ON_PROCESS&id=<%= customer_order.getCustomer_order_id() %>">Aceptar</a>
                <br>
                <a href="${pageContext.request.contextPath}/admin/alterStateOrder?state=CANCELED&id=<%= customer_order.getCustomer_order_id() %>">Cancelar</a>
                <br>
                <% if (customer_order.getState()==State.ON_PROCESS){%>
                <a href="${pageContext.request.contextPath}/admin/alterStateOrder?state=FINISHED&id=<%= customer_order.getCustomer_order_id() %>">Finalizar</a>
                <%}%>
                <br>
                <strong>Evidencia de pago: </strong>
                <img src="data:image/jpg;base64,<%= base64Evidence %>" alt="productImage" height="100px"/>
            </div>
        </div>
    </div>

</div>
<jsp:include page="components/footer.jsp" />
<jsp:include page="components/scripts.jsp" />