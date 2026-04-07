package src.classes.operaciones;

import java.util.List;
import java.util.Scanner;

import src.classes.libros.Libro;
import src.tools.limpiar;

public class EliminarLibro {

    // METODO PARA ELIMINAR UN LIBRO
    public static void eliminarLibro(Scanner scanner, List<Libro> inventario) {

        System.out.println("\n--- Eliminar Libro ---");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío. No hay libros para eliminar.");
        } else {
            for (int i = 1; i < inventario.size(); i++) {
                Libro l = inventario.get(i);
                System.out.println(i + ". " + l.getTitulo() + " (Autor: " + l.getAutor() + ")");
            }

            System.out.print("\nIngrese el número del libro a eliminar: ");
            try {
                int index = Integer.parseInt(scanner.nextLine());

                if (index >= 0 && index < inventario.size()) {
                    String tituloEliminado = inventario.get(index).getTitulo();
                    // Verificar si está prestado antes de borrarlo
                    if (!inventario.get(index).isDisponible()) {
                        System.out.println(
                                "Advertencia: Este libro está prestado actualmente. ¿Deseas eliminarlo de todos modos?");
                        System.out.print("1. Sí / 2. No: ");
                        int confirmar = Integer.parseInt(scanner.nextLine());
                        if (confirmar != 1) {
                            limpiar.limpiarConsola();
                            System.out.println("Eliminación cancelada.");
                            return;
                        }
                    }

                    inventario.remove(index);
                    limpiar.limpiarConsola();
                    System.out.println(
                            "Libro '" + tituloEliminado + "' eliminado del inventario.");
                } else {
                    limpiar.limpiarConsola();
                    System.out.println("Índice inválido.");
                }
            } catch (NumberFormatException e) {
                limpiar.limpiarConsola();
                System.out.println("Error: Debes ingresar un número.");
            }
        }

    }
}