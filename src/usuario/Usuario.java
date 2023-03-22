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

    public static void crearUsuario(String nombreUsuario, String hashedContrasenya, String email, int telefono) throws Exception {
        PreparedStatement preparedStatement = conexion.prepareStatement("CREATE USER " + nombreUsuario + " IDENTIFIED BY '" + hashedContrasenya + "';");
        try {
            preparedStatement.execute();
            preparedStatement = conexion.prepareStatement("GRANT ALL PRIVILEGES ON lorazepam.* TO '"+nombreUsuario+"'@'%' WITH GRANT OPTION;");
            preparedStatement.execute();
            preparedStatement = conexion.prepareStatement("INSERT INTO `lorazepam`.`usuarios` (`nombre`, `email`, `telefono`) VALUES ('"+nombreUsuario+"', '"+email+"', '"+telefono+"');");
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("ERROR: No se pudo crear el usuario (compruebe que no exista).");
            e.printStackTrace();
        }

        System.out.println("Usuario "+nombreUsuario+" creado correctamente.");
    }

    public static void eliminarUsuario (String nombreUsuario) throws Exception{
        PreparedStatement preparedStatement = conexion.prepareStatement("REVOKE ALL PRIVILEGES ON lorazepam.* FROM '"+nombreUsuario+"'@'%';");
        try{
            preparedStatement.execute();
            conexion.prepareStatement("DROP USER '"+nombreUsuario+"'@'%'");
            preparedStatement.execute();
            conexion.prepareStatement("DELETE FROM usuarios WHERE nombre LIKE '"+nombreUsuario+"'@'%'");
            preparedStatement.execute();
        }catch (Exception e){
            System.out.println("No se ha podido eliminar el usuario "+ nombreUsuario+ ".");
            e.printStackTrace();
        }

        System.out.println("Se ha eliminado el usuario "+nombreUsuario+".");
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
