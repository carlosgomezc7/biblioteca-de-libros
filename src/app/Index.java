package src.app;

import java.util.ArrayList;
import java.util.Scanner;
import src.classes.libros.*;
import src.classes.usuarios.*;

public class Index {
    public static void main(String[] args) {
        // clear
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Libro> inventario = new ArrayList<>();
        inventario.add(new LibroDigital("002", "El Programador Pragmático", "Andy Hunt", "PDF"));

        ArrayList<Usuario> usuariosRegistrados = new ArrayList<>();

        usuariosRegistrados.add(new Administrador("Carlos", "admin", "admin"));
        usuariosRegistrados.add(new Librero("Luis", "lib", "lib")); // <-- NUEVO USUARIO LIBRERO AVANCE4
        usuariosRegistrados.add(new Lector("Carla", "dev", "dev"));
        usuariosRegistrados.add(new Lector("Aaron", "lector", "lector"));

        boolean sistemaEncendido = true;

        System.out.println("=========================================");
        System.out.println("   SISTEMA BIBLIOTECARIO - LOGIN         ");
        System.out.println("=========================================");

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

            Usuario usuarioAutenticado = null;

            for (Usuario u : usuariosRegistrados) {
                if (u.getUsername().equals(inputUser)) {
                    if (u.login(inputPass)) {
                        usuarioAutenticado = u;
                    } else {
                        System.out.println(" Error: Contraseña incorrecta.");
                    }
                    break;
                }
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if (usuarioAutenticado != null) {
                System.out.println("\n ¡Bienvenido, " + usuarioAutenticado.getNombre() + "!");
                usuarioAutenticado.mostrarPanel();

                // Redirección dependiendo del Rol (Polimorfismo / InstanceOf)
                if (usuarioAutenticado instanceof Administrador) {
                    Administrador.menuAdministrador(scanner, (Administrador) usuarioAutenticado, inventario);
                } else if (usuarioAutenticado instanceof Librero) { // <-- REDIRECCIÓN AL NUEVO ROL
                    Librero.menuLibrero(scanner, (Librero) usuarioAutenticado, inventario);
                } else if (usuarioAutenticado instanceof Lector) {
                    Lector.menuLector(scanner, (Lector) usuarioAutenticado, inventario);
                }
            } else if (!inputUser.equalsIgnoreCase("salir")) {
                System.out.println(" No se pudo iniciar sesión. Verifica tus credenciales.");
            }
        }
        scanner.close();
    }
}