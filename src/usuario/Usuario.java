package usuario;

import java.sql.*;


public class Usuario {

    private static Connection conexion;
    private String nombre;
    private String email;
    private int telefono;

    public Usuario(Connection conexion, String nombre) throws Exception{
        PreparedStatement preparedStatement = conexion.prepareStatement("SELECT email, telefono FROM usuarios WHERE nombre LIKE '"+nombre+"'");
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        this.conexion = conexion;
        this.nombre = nombre;
        this.email = rs.getString(1);
        this.telefono = rs.getInt(2);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public static void crearUsuario(String nombreUsuario, String hashedContrasenya, String email, int telefono) throws SQLException {
        PreparedStatement preparedStatement = conexion.prepareStatement("" +
                "CREATE USER "+nombreUsuario+" IDENTIFIED BY '"+hashedContrasenya+"';" +
                "GRANT ALL PRIVILEGES ON *.* TO "+nombreUsuario+";" +
                "INSERT INTO `lorazepam`.`usuarios` (`nombre`, `email`, `telefono`) VALUES ('"+nombreUsuario+"', '"+email+"', '"+telefono+"');");
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
