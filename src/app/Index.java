package src.app;

import java.util.ArrayList; //importe de liberia para utilizar arrays
import java.util.Scanner;
import src.classes.libros.*;
import src.classes.usuarios.*;

public class Index {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 1. Vectores principales, simulacion de bd
        ArrayList<Libro> inventario = new ArrayList<>();
        // Ahora instanciamos libros usando las clases hijas, AQUI APLICAMOS HERENCIA
        inventario
                .add(new LibroFisico("001", "Cien Años de Soledad", "Gabriel García Márquez", "Pasillo 3 - Estante B"));
        inventario.add(new LibroDigital("002", "El Programador Pragmático", "Andy Hunt", "PDF"));

        // VECTOR DE USUARIOS EN EL CUAL ESTOS USUARIOS QUEDAN FIJOS POR SEGURIDAD
        ArrayList<Usuario> usuariosRegistrados = new ArrayList<>();

        // AQUI PODEMOS AGREGAR TODOS LOS USUARIOS QUE NECESITEMOS
        usuariosRegistrados.add(new Administrador("Carlos", "admin", "admin"));
        usuariosRegistrados.add(new Lector("Carla", "dev", "dev"));
        usuariosRegistrados.add(new Lector("Aaron", "dev", "dev"));

        // INICIAR SISTEMA
        boolean sistemaEncendido = true;
        System.out.println("=========================================");
        System.out.println("   SISTEMA BIBLIOTECARIO - LOGIN         ");
        System.out.println("=========================================");

        // 2. BUCLE PRINCIPAL DEL SISTEMA
        //
        while (sistemaEncendido) {
            System.out.println("\n--- INICIAR SESIÓN ---");
            System.out.print("Usuario: ");
            String inputUser = scanner.nextLine();

            if (inputUser.equalsIgnoreCase("salir")) {
                sistemaEncendido = false;
                System.out.println("Apagando sistema... ¡Adiós!");
                continue;
            }

            System.out.print("Contraseña: ");
            String inputPass = scanner.nextLine();

            // 3. PROCESO DE VALIDACIÓN
            Usuario usuarioAutenticado = null;
            // AQUI UTILIZAMOS UN FOR PARA REALIZAR LA COMPARACION DE LO QUE INGRESA EL
            // USUARIO CON LOS REGISTRADOS
            for (Usuario u : usuariosRegistrados) {
                if (u.getUsername().equals(inputUser)) {
                    if (u.login(inputPass)) {
                        usuarioAutenticado = u;
                    } else {
                        System.out.println("Error: Contraseña incorrecta.");
                    }
                    break;
                }
            }

            // 4. DIRECCIONAMIENTO SEGÚN EL ROL
            if (usuarioAutenticado != null) {
                System.out.println("\n ¡Bienvenido, " + usuarioAutenticado.getNombre() + "!");
                usuarioAutenticado.mostrarPanel(); // Polimorfismo

                if (usuarioAutenticado instanceof Administrador) {
                    // Llamamos al método estático de la clase Administrador
                    Administrador.menuAdministrador(scanner, inventario);
                } else if (usuarioAutenticado instanceof Lector) {
                    // Llamamos al método estático de la clase Lector
                    Lector.menuLector(scanner, (Lector) usuarioAutenticado, inventario);
                }
            } else if (!inputUser.equalsIgnoreCase("salir")) {
                System.out.println(" No se pudo iniciar sesión. Verifica tus credenciales.");
            }
        }
        scanner.close();
    }
}