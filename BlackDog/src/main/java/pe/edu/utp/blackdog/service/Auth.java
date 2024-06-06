package pe.edu.utp.blackdog.service;

import pe.edu.utp.blackdog.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {

    public static boolean isValidUser(String dni, String pwd)
            throws SQLException, NamingException, IOException {
        String cnx = AppConfig.getConnectionStringCFN();
        String strSQL = String.format("CALL pr_checkUser('%s','%s')", dni, md5(pwd));
        Connection cnn = DataAccessMariaDB.getConnection(cnx);
        ResultSet rst = cnn.createStatement().executeQuery(strSQL);
        String res = (rst.next()) ? rst.getString("login") : "no_data";
        cnn.close();
        return !res.equals("no_data");
    }

    public static int getUsuarioId(String dni, String pwd)
            throws SQLException, NamingException, IOException {
        String cnx = AppConfig.getConnectionStringCFN();
        String strSQL = String.format("CALL pr_getUsuarioId('%s','%s')", dni, md5(pwd));
        Connection cnn = DataAccessMariaDB.getConnection(cnx);
        ResultSet rst = cnn.createStatement().executeQuery(strSQL);
        int usuarioId = (rst.next()) ? rst.getInt("usuario_id") : -1;
        cnn.close();
        return usuarioId;
    }

    public static String md5(String data) throws IOException {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            MessageDigest msg = (MessageDigest) md.clone();
            msg.update(data.getBytes());
            return byteArrayToHex(msg.digest());
        } catch (CloneNotSupportedException | NoSuchAlgorithmException e) {
            return data;
        }
    }

    /*
    * Link: https://stackoverflow.com/questions/9655181/java-convert-a-byte-array-to-a-hex-string
    * Nota: Metodo altetnativo para JDK17, pero se debe tener cuidado con tener este entorno activado
    * HexFormat hex = HexFormat.of();
    * hex.formatHex(someByteArray)
    * */
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

}
