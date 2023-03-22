package menus;

import java.util.Scanner;

import conexion.Conexion;
import usuario.Usuario;

public class menuUsuarios {

    private static Scanner sc;

    public static void run() {
        sc = new Scanner(System.in);
        System.out.println("MENU USUARIOS");
        switch (sc.nextInt()){
            case 0:
                System.out.println("0. Ayuda" +
                        "\n1. Crear un usuario" +
                        "\n2. Modificar un usuario" +
                        "\n3. Eliminar un usuario" +
                        "\n4. Volver al menu principal");
                run();
                break;
            case 1:
                System.out.println("Escribe el nombre del usuario");
                String nombre = sc.next();
                System.out.println("Escribe la contraseña");
                String hashContrasenya = Conexion.hashPassword(sc.next());
                System.out.println("Vuelve a escribir la contraseña");
                if(!Conexion.hashPassword((sc.next())).equals(hashContrasenya)){
                    System.out.println("Las contraseñas no coinciden, vuelve a intentarlo");
                    run();
                    break;
                }
                System.out.println("Escribe el email");
                String email = sc.next();
                System.out.println("Escribe el teléfono");
                int telefono = sc.nextInt();

                try {
                    Usuario.crearUsuario(nombre, hashContrasenya, email, telefono);
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
}
