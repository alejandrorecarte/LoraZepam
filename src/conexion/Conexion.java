package conexion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Conexion {

    private static Connection connection;

    public Conexion (String usuario, String contrasenya) throws Exception{
        System.out.println("Conectando con la base de datos");
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lorazepam",usuario,contrasenya);
        System.out.println("Conexión con la base de datos establecida");
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void close() throws SQLException {
        connection.close();
    }

    public static String hashPassword(String password) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            hashedPassword = bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción aquí
        }
        return hashedPassword;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
