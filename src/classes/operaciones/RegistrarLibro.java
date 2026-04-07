package src.classes.operaciones;

import java.util.List;
import java.util.Scanner;

import src.tools.*;
import src.classes.libros.*;

public class RegistrarLibro {
    // METODO PARA REGISTRAR LIBRO
    public static void registrarLibro(Scanner scanner, List<Libro> inventario) {
        System.out.println("\n--- Registrar Nuevo Libro ---");
        System.out.print("Ingresa el Título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingresa el Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Confirme el ingreso del libro\n 1.- Si\n 2.- NO\nElige: ");

        try {
            int tipo = Integer.parseInt(scanner.nextLine());
            if (tipo == 1) {
                inventario.add(new LibroDigital(titulo, autor, "PDF"));
                limpiar.limpiarConsola();
                System.out.println(
                        "¡Libro '" + titulo + "' registrado con éxito por el Administrador!");
            } else {
                limpiar.limpiarConsola();
                System.out.println("Registro cancelado.");
            }
        } catch (NumberFormatException e) {
            limpiar.limpiarConsola();
            System.out.println("Error: Debes ingresar 1 o 2 numéricamente.");
        }

    }

}
