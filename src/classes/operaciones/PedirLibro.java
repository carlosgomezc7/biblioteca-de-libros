package src.classes.operaciones;

import java.util.ArrayList;
import java.util.Scanner;

import src.classes.libros.*;
import src.classes.usuarios.*;
import src.tools.limpiar;

public class PedirLibro {
    // METODO PARA PEDIR LIBRO
    public static void pedirLibro(Scanner scanner, ArrayList<Libro> inventario, Lector lector) {
        System.out.println("\n--- Catálogo Disponible ---");
        // Corrección: Iniciar en 1 y en el index se puso el primer campo del array list
        // en null

        for (int i = 1; i < inventario.size(); i++) {
            if (inventario.get(i).isDisponible()) {
                System.out.print(i + ". ");
                inventario.get(i).mostrarInfo();
            }
        }
        System.out.print("Ingresa el número del libro a pedir (o 0 para cancelar): ");
        try {
            int seleccion = Integer.parseInt(scanner.nextLine());
            // AJUSTAR DEJAR EN CERO PARA CANCELAR
            if (seleccion >= 0 && seleccion < inventario.size() && inventario.get(seleccion).isDisponible()) {
                Libro libroSeleccionado = inventario.get(seleccion);
                Prestamo nuevoPrestamo = new Prestamo(libroSeleccionado, lector, 7);
                lector.agregarPrestamo(nuevoPrestamo);

                System.out.println(" ¡Libro asignado a tu cuenta!");

                if (libroSeleccionado instanceof LibroDigital) {
                    lector.descargar((LibroDigital) libroSeleccionado);
                }
            } else if (seleccion != -1) {
                System.out.println(" Selección inválida o libro no disponible.");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Error: Entrada inválida. Debes ingresar un número.");
        } finally {
            // clear
            limpiar.limpiarConsola();
        }

    }
}