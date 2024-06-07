package pe.edu.utp.blackdog.dao;

import pe.edu.utp.blackdog.model.Ingredient;
import pe.edu.utp.blackdog.model.Order_detail;
import pe.edu.utp.blackdog.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order_detailDAO implements AutoCloseable{
    private final Connection cnn;

    public Order_detailDAO() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, "java:/MariaDB");
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void registerOrder_detail(Order_detail orderDetail) throws SQLException, NamingException {
        String query = "INSERT INTO order_detail (customer_order, product_id) VALUES (?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, orderDetail.getCustomerOder().getCustomer_order_id());
            ps.setLong(2, orderDetail.getProduct().getProduct_id());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo insertar el detalle de la orden en la base de datos.");
            }
        }
    }

    public List<Order_detail> getAllOrder_details() throws SQLException, NamingException {
        List<Order_detail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM order_detail";
        Customer_orderDAO customerOrderDAO = new Customer_orderDAO();
        ProductDAO productDAO = new ProductDAO();
        try (PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                orderDetails.add(Order_detail.createOrderDetail(
                        customerOrderDAO.getOrderById(rs.getLong("customer_order")),
                        productDAO.getProductById(rs.getLong("product_id"))
                ));
                customerOrderDAO.close();
                productDAO.close();
            } if (orderDetails.size() == 0) {
                throw new SQLException("No se encontraron ingredientes en la base de datos.");
            }
        }
        return orderDetails;
    }

    public Order_detail getOrderDetailsByOrderId(long customer_order_id) throws SQLException, NamingException {
        String query = "SELECT * FROM order_detail WHERE customer_order_id = ?";
        Order_detail order_detail = null;
        Customer_orderDAO customerOrderDAO = new Customer_orderDAO();
        ProductDAO productDAO = new ProductDAO();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, customer_order_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order_detail = Order_detail.createOrderDetail(
                            customerOrderDAO.getOrderById(rs.getLong("customer_order")),
                            productDAO.getProductById(rs.getLong("product_id"))
                    );
                    customerOrderDAO.close();
                    productDAO.close();
                } else {
                    throw new SQLException(String.format("No se encontró un ingrediente con el ID %d en la base de datos.", customer_order_id));
                }

            }
        }
        return order_detail;
    }

    public Order_detail getOrderDetailsByProductId(long product_id) throws SQLException, NamingException {
        String query = "SELECT * FROM product WHERE product_id = ?";
        Order_detail order_detail = null;
        Customer_orderDAO customerOrderDAO = new Customer_orderDAO();
        ProductDAO productDAO = new ProductDAO();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, product_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order_detail = Order_detail.createOrderDetail(
                            customerOrderDAO.getOrderById(rs.getLong("customer_order")),
                            productDAO.getProductById(rs.getLong("product_id"))
                    );
                    customerOrderDAO.close();
                    productDAO.close();
                } else {
                    throw new SQLException(String.format("No se encontró un ingrediente con el ID %d en la base de datos.", product_id));
                }

            }
        }
        return order_detail;
    }
}
