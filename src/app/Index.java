package src.app;

import java.util.ArrayList;
import java.util.Scanner;
import src.classes.libros.*;
import src.classes.usuarios.*;
import src.tools.limpiar; // <-- Se importa la clase limpiar

public class Index {
    public static void main(String[] args) {
        // clear
        limpiar.limpiarConsola(); // <-- Reemplazo 1

        Scanner scanner = new Scanner(System.in);
        ArrayList<Libro> inventario = new ArrayList<>();

        // Mantenemos el inventario 100% digital y sin ID manual
        inventario.add(new LibroDigital("Manual de sistema", "CCA", "EPUB"));
        inventario.add(new LibroDigital("Cien Años de Soledad", "Gabriel García Márquez", "EPUB"));
        inventario.add(new LibroDigital("El Programador Pragmático", "Andy Hunt", "PDF"));

        ArrayList<Usuario> usuariosRegistrados = new ArrayList<>();

        usuariosRegistrados.add(new Administrador("Carlos", "admin", "admin"));
        usuariosRegistrados.add(new Librero("Luis", "lib", "lib"));
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

            limpiar.limpiarConsola(); // <-- Reemplazo 2

            if (usuarioAutenticado != null) {
                System.out.println("\n ¡Bienvenido, " + usuarioAutenticado.getNombre() + "!");
                usuarioAutenticado.mostrarPanel();

                // Redirección dependiendo del Rol (Polimorfismo / InstanceOf)
                if (usuarioAutenticado instanceof Administrador) {
                    Administrador.menuAdministrador(scanner, (Administrador) usuarioAutenticado, inventario,
                            usuariosRegistrados);
                } else if (usuarioAutenticado instanceof Librero) {
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