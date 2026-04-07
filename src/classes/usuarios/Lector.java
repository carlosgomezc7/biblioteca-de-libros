package src.classes.usuarios;

import src.classes.libros.*;
import src.classes.operaciones.*;
import src.tools.limpiar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lector extends Usuario {

    private List<Prestamo> misPrestamos;

    public Lector(String nombre, String username, String password) {
        super(nombre, username, password);
        this.misPrestamos = new ArrayList<>();
    }

    public void agregarPrestamo(Prestamo prestamo) {
        this.misPrestamos.add(prestamo);
    }

    public void descargar(LibroDigital libroDigital) {
        System.out.println("Descargando libro digital: " + libroDigital.getTitulo() + " en formato "
                + libroDigital.getFormato());
    }

    // [3er Avance - Proyecto POO] Mostrar los libros actuales del lector
    public void mostrarMisLibros() {
        System.out.println("\n--- Mis Libros Prestados ---");
        if (this.misPrestamos.isEmpty()) {
            System.out.println("No tienes libros prestados actualmente.");
        } else {
            for (int i = 0; i < this.misPrestamos.size(); i++) {
                Prestamo p = this.misPrestamos.get(i);
                System.out.println(
                        (i + 1) + ". " + p.getLibro().getTitulo() + " | Fecha límite de entrega: " + p.getfechaFin());
            }
        }
    }

    // [3er Avance - Proyecto POO] Lógica de devolución
    public void devolverLibro(Prestamo prestamo) {
        prestamo.devolverLibro(); // Llama a la lógica de la transacción en la clase Prestamo
        this.misPrestamos.remove(prestamo); // Libera el registro del lector
    }

    public static void menuLector(Scanner scanner, Lector lector, ArrayList<Libro> inventario) {
        int opcion = 0;
        do {
            System.out.println("\n--- PANEL DE LECTOR ---");
            System.out.println("1. Ver catálogo y pedir libro");
            System.out.println("2. Ver mis libros prestados");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Cerrar sesión");
            System.out.print("Elige una opción: ");

            // [3er Avance] Implementación de Excepciones para evitar el colapso
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Error: Por favor, ingresa un número válido.");
                continue;
            } finally {
                // clear
                limpiar.limpiarConsola();
            }

            if (opcion == 1) {
                src.classes.operaciones.PedirLibro.pedirLibro(scanner, inventario, lector);
            } else if (opcion == 2) {
                lector.mostrarMisLibros();
            } else if (opcion == 3) {
                // [3er Avance] Interacción para seleccionar y devolver un libro
                lector.mostrarMisLibros();
                if (!lector.misPrestamos.isEmpty()) {
                    System.out.print("Ingresa el número del libro que deseas devolver (o 0 para cancelar): ");
                    try {
                        int seleccionDev = Integer.parseInt(scanner.nextLine());
                        if (seleccionDev > 0 && seleccionDev <= lector.misPrestamos.size()) {
                            Prestamo prestamoADevolver = lector.misPrestamos.get(seleccionDev - 1);
                            lector.devolverLibro(prestamoADevolver);
                        } else if (seleccionDev != 0) {

                            // clear
                            limpiar.limpiarConsola();
                            System.out.println(" Selección inválida.");

                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Error: Entrada inválida.");
                    } finally {
                        // clear
                        limpiar.limpiarConsola();
                    }
                }
            }
        } while (opcion != 4);
        System.out.println("Cerrando sesión de lector...");
    }

    @Override
    public void mostrarPanel() {
        System.out.println("Cargando panel de Lector: " + this.nombre + "...");
    }
}
