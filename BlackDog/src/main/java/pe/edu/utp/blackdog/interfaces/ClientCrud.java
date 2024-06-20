package pe.edu.utp.blackdog.interfaces;

import pe.edu.utp.blackdog.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClientCrud {
    public void registerClient(Client client) throws SQLException;

    public List<Client> getAllClients() throws SQLException;

    public void updateClient(Client client, long client_id) throws SQLException;

    public void deleteClient(long client_id) throws SQLException;

    public Client getClientByEmail(String email) throws SQLException;
    
    public Client getClientById(long client_id) throws SQLException;
}
