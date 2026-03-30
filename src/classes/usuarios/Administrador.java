package src.classes.usuarios;

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

    public static void menuAdministrador(Scanner scanner, Administrador admin, ArrayList<Libro> inventario) {
        int opcion = 0;
        do {
            System.out.println("\n--- PANEL DE ADMINISTRADOR ---");
            System.out.println("1. Ver inventario total");
            System.out.println("2. Reporte de Estado");
            System.out.println("3. Cerrar sesión");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Error: Ingresa un número válido.");
                continue;
            }
            finally {
            //clear
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }


            if (opcion == 1) {
                System.out.println("\n--- Inventario ---");
                for (Libro l : inventario) {
                    l.mostrarInfo();
                    System.out.flush();
                }
            } else if (opcion == 2) {
                admin.auditarInventario(inventario);
            }
        } while (opcion != 3);
        System.out.println("Cerrando sesión de administrador...");
    }

    @Override
    public void mostrarPanel() {
        System.out.println("Cargando panel de Administración...");
    }
}