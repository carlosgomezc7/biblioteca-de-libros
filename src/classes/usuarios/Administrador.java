package src.classes.usuarios;

import src.tools.limpiar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.classes.libros.*;

public class Administrador extends Usuario {

    public Administrador(String nombre, String username, String password) {
        super(nombre, username, password);
    }

    // Reportes de Estado para control de la biblioteca
    public void auditarInventario(List<Libro> inventario) {
        System.out.println("\n=== REPORTE DE ESTADO DEL INVENTARIO ===");
        int total = inventario.size();
        int disponibles = 0;
        int prestados = 0;

        for (Libro l : inventario) {
            if (l.isDisponible()) {
                disponibles++;
            } else {
                prestados++;
            }
        }

        System.out.println(" Total de libros registrados en el sistema: " + total);
        System.out.println(" Libros disponibles para los lectores: " + disponibles);
        System.out.println(" Libros prestados actualmente: " + prestados);
        System.out.println("========================================\n");
    }

    public static void menuAdministrador(Scanner scanner, Administrador admin, ArrayList<Libro> inventario,
            ArrayList<Usuario> usuariosRegistrados) {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\n--- PANEL DE ADMINISTRADOR ---");
            System.out.println("1. Ver inventario total");
            System.out.println("2. Reporte de Estado");
            System.out.println("3.-Usuarios");
            System.out.println("4. Cerrar sesión");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Error: Ingresa un número válido.");
                continue;
            } finally {
                // clear
                limpiar.limpiarConsola();
            }

            if (opcion == 1) {
                System.out.println("\n--- Inventario ---");
                for (Libro l : inventario) {
                    if (l != null) {
                        l.mostrarInfo();
                    }
                }
            } else if (opcion == 2) {
                admin.auditarInventario(inventario);
            } else if (opcion == 3) {
                int subOpcion = 0;
                while (subOpcion != 4) {
                    System.out.println("\n--- CONTROL DE USUARIOS ---");
                    System.out.println("1. Mostrar usuarios");
                    System.out.println("2. Editar un usuario");
                    System.out.println("3. Eliminar un usuario");
                    System.out.println("4. Atras");
                    System.out.print("Elige una opción: ");

                    try {
                        subOpcion = Integer.parseInt(scanner.nextLine());
                        limpiar.limpiarConsola();
                        if (subOpcion == 1) {
                            System.out.println("\n--- Lista de Usuarios ---");
                            for (int i = 1; i < usuariosRegistrados.size(); i++) {
                                Usuario u = usuariosRegistrados.get(i);
                                System.out.println(i + ". " + u.getNombre() + " (" + u.getUsername() + ") - "
                                        + u.getClass().getSimpleName());
                            }
                        } else if (subOpcion == 3) {
                            System.out.print("Ingrese el índice del usuario a eliminar: ");
                            int index = Integer.parseInt(scanner.nextLine());
                            System.out.println("\n--- Lista de Usuarios ---");
                            for (int i = 1; i < usuariosRegistrados.size(); i++) {
                                Usuario u = usuariosRegistrados.get(i);
                                System.out.println(i + ". " + u.getNombre() + " (" + u.getUsername() + ") - "
                                        + u.getClass().getSimpleName());
                            }

                            if (index >= 0 && index < usuariosRegistrados.size()) {
                                if (usuariosRegistrados.get(index) instanceof Administrador
                                        && usuariosRegistrados.get(index).getUsername().equals(admin.getUsername())) {
                                    System.out.println("No puedes eliminarte a ti mismo.");
                                } else {
                                    usuariosRegistrados.remove(index);
                                    System.out.println("Usuario eliminado.");
                                }
                            } else {
                                System.out.println("Índice inválido.");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error en la operación de usuarios.");
                    }
                }
            }
        }
        System.out.println("Cerrando sesión de administrador...");
    }

    @Override
    public void mostrarPanel() {
        System.out.println("Cargando panel de Administración...");
    }
}