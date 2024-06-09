package pe.edu.utp.blackdog.dao;

import pe.edu.utp.blackdog.model.Customer_order;
import pe.edu.utp.blackdog.model.State;
import pe.edu.utp.blackdog.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer_orderDAO implements AutoCloseable {
    private final Connection cnn;

    public Customer_orderDAO() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, "java:/MariaDB");
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void registerOrder(Customer_order customerOrder) throws SQLException {
        String query = "INSERT INTO customer_order (client_id, order_date, address, amount, state, evidence_image) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, customerOrder.getClient().getClient_id());
            ps.setDate(2, Date.valueOf(String.valueOf(customerOrder.getOrder_date())));
            ps.setString(3, customerOrder.getAddress());
            ps.setDouble(4, customerOrder.getAmount());
            ps.setString(5, String.valueOf(customerOrder.getState()));
            ps.setString(6, customerOrder.getEvidence_image());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo registrar la orden en la base de datos.");
            }
        }
    }

    public List<Customer_order> getAllOrders() throws SQLException, NamingException {
        List<Customer_order> customer_oders = new ArrayList<>();
        String query = "SELECT * FROM customer_order";
        ClientDAO clientDAO = new ClientDAO();
        try (PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                customer_oders.add(Customer_order.createOrder(
                        rs.getLong("customer_order_id"),
                        clientDAO.getClientById(rs.getLong("client_id")),
                        LocalDateTime.parse(String.valueOf(rs.getDate("order_date"))),
                        rs.getString("address"),
                        rs.getDouble("amount"),
                        State.valueOf(rs.getString("state")),
                        rs.getString("evidence_image")
                ));
                clientDAO.close();
            } if (customer_oders.size() == 0) {
                throw new SQLException("No se encontraron ordenes en la base de datos.");
            }
        }
        return customer_oders;
    }

    public List<Customer_order> getAllOnHoldOrders() throws SQLException, NamingException {
        List<Customer_order> customer_oders = new ArrayList<>();
        String query = "SELECT * FROM customer_order WHERE state = ?";
        ClientDAO clientDAO = new ClientDAO();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, "ON_HOLD");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customer_oders.add(Customer_order.createOrder(
                            rs.getLong("customer_order_id"),
                            clientDAO.getClientById(rs.getLong("client_id")),
                            LocalDateTime.parse(String.valueOf(rs.getDate("order_date"))),
                            rs.getString("address"),
                            rs.getDouble("amount"),
                            State.valueOf(rs.getString("state")),
                            rs.getString("evidence_image")
                    ));
                    clientDAO.close();
                } if (customer_oders.size() == 0) {
                    throw new SQLException("No se encontraron ordenes pendientes en la base de datos.");
                }
            }
        }
        return customer_oders;
    }

    public Customer_order getOrderById(long customer_oder_id) throws SQLException, NamingException {
        String query = "SELECT * FROM customer_oder WHERE customer_oder_id = ?";
        Customer_order customer_oder = null;
        ClientDAO clientDAO = new ClientDAO();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, customer_oder_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer_oder = Customer_order.createOrder(
                            rs.getLong("customer_order_id"),
                            clientDAO.getClientById(rs.getLong("client_id")),
                            LocalDateTime.parse(String.valueOf(rs.getDate("order_date"))),
                            rs.getString("address"),
                            rs.getDouble("amount"),
                            State.valueOf(rs.getString("state")),
                            rs.getString("evidence_image")
                    );
                    clientDAO.close();
                } else {
                    throw new SQLException(String.format("No se encontró una orden con el ID %d en la base de datos.", customer_oder_id));
                }

            }
        }
        return customer_oder;
    }

    public void updateOrder(Customer_order customerOrder, long customer_order_id) throws SQLException {
        String query = "UPDATE customer_order SET client_id = ?, order_date = ?, address = ?, amount = ?, state = ?, evidence_image = ? WHERE customer_order_id = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, customerOrder.getClient().getClient_id());
            ps.setDate(2, Date.valueOf(String.valueOf(customerOrder.getOrder_date())));
            ps.setString(3, customerOrder.getAddress());
            ps.setDouble(4, customerOrder.getAmount());
            ps.setString(5, String.valueOf(customerOrder.getState()));
            ps.setString(6, customerOrder.getEvidence_image());
            ps.executeUpdate();
        }
    }

    public void deleteOrder(long customer_order_id) throws SQLException {
        String query = "DELETE FROM customer_order WHERE customer_order_id = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setLong(1, customer_order_id);
            ps.executeUpdate();
        }
    }
}
