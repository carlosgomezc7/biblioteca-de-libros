package src.classes.operaciones;

import java.util.List;
import java.util.Scanner;

import src.classes.libros.*;
import src.tools.limpiar;

public class EditarLibro {
    public static void editarLibro(Scanner scanner, List<Libro> inventario, String titulo, String autor,
            String formato) {
        // METODO PARA EDITAR UN LIBRO
        System.out.println("\n--- Editar Libro ---");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío. No hay libros para editar.");
        } else {
            for (int i = 1; i < inventario.size(); i++) {
                Libro l = inventario.get(i);
                System.out.println(i + ". " + l.getTitulo() + " (Autor: " + l.getAutor() + ")");
            }

            System.out.print("\nIngrese el número del libro a editar: ");
            try {
                int index = Integer.parseInt(scanner.nextLine());

                if (index >= 0 && index < inventario.size()) {
                    Libro libroAEditar = inventario.get(index);
                    System.out.println("\nEditando: " + libroAEditar.getTitulo());
                    System.out.println(
                            "(Presiona ENTER sin escribir nada para conservar el dato actual)");

                    System.out.print("Nuevo título: ");
                    String nuevoTitulo = scanner.nextLine();
                    if (!nuevoTitulo.trim().isEmpty()) {
                        libroAEditar.setTitulo(nuevoTitulo);
                    }

                    System.out.print("Nuevo autor: ");
                    String nuevoAutor = scanner.nextLine();
                    if (!nuevoAutor.trim().isEmpty()) {
                        libroAEditar.setAutor(nuevoAutor);
                    }

                    limpiar.limpiarConsola();
                    System.out.println("Libro actualizado correctamente.");
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
