package pe.edu.utp.blackdog.interfaces;

import pe.edu.utp.blackdog.dao.ClientDAO;
import pe.edu.utp.blackdog.model.Customer_order;
import pe.edu.utp.blackdog.model.State;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface Customer_orderCrud {
    void registerOrder(Customer_order customerOrder) throws SQLException;

    List<Customer_order> getAllOrders() throws SQLException, NamingException;

    List<Customer_order> getAllOnHoldOrders() throws SQLException, NamingException;

    Customer_order getOrderById(long customer_order_id) throws SQLException, NamingException;

    void updateOrder(Customer_order customerOrder, long customer_order_id) throws SQLException;

    void deleteOrder(long customer_order_id) throws SQLException;

    Customer_order getLastCustomer_order() throws SQLException, NamingException;
}
