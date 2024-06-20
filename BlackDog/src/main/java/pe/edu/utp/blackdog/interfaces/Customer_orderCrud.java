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
    public void registerOrder(Customer_order customerOrder) throws SQLException;

    public List<Customer_order> getAllOrders() throws SQLException, NamingException;

    public List<Customer_order> getAllOnHoldOrders() throws SQLException, NamingException;

    public Customer_order getOrderById(long customer_order_id) throws SQLException, NamingException;

    public void updateOrder(Customer_order customerOrder, long customer_order_id) throws SQLException;

    public void deleteOrder(long customer_order_id) throws SQLException;

    public Customer_order getLastCustomer_order() throws SQLException, NamingException;
}
