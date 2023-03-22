import conexion.Conexion;
import usuario.Usuario;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static Connection connection;
    public static Usuario usuario;
    private static Scanner sc;
    public static void main(String[] args) {
        try {
            start();
            menu();
            end();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void start() {
        sc = new Scanner(System.in);
        try{
            System.out.println("Indique su nombre de usuario");
            String nombre = sc.nextLine();
            System.out.println("Indique la contraseña");
            String hashContrasenya = Conexion.hashPassword(sc.nextLine());
            Conexion conexion = new Conexion(nombre, hashContrasenya);
            connection = conexion.getConnection();
            usuario = new Usuario(connection, nombre);
        } catch (Exception e){
            System.out.println("No se ha podido iniciar sesión en la BBDD.");
            start();
            return;
        }
        System.out.println("Se ha iniciado sesión correctamente en la BBDD.");
    }

    private static void menu() {
        System.out.println("MENU PRINCIPAL");
        switch (sc.nextInt()){
            case 0:
                System.out.println("0. Ayuda" +
                        "\n1. Productos" +
                        "\n2. Usuarios" +
                        "\n3. Salir del programa");
                menu();
                break;
            case 1:
                menus.menuProductos.run();
                menu();
                break;
            case 2:
                menus.menuUsuarios.run();
                menu();
                break;
            case 3:
                break;
            default:
                System.out.println("Opción inválida, vuelve a marcar una opción.");
                menu();
                break;
        }
    }
    private static void end() throws Exception {
        System.out.println("Cerrando los recursos...");
        connection.close();
        sc.close();
        System.out.println("Gracias por usar el programa.");
    }
}