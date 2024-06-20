package pe.edu.utp.blackdog.interfaces;

import java.sql.SQLException;

public interface GetAdmin {
    public String getAdministratorNameByEmail(String email) throws SQLException;
}
