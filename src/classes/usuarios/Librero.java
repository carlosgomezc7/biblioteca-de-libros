package src.classes.usuarios;

import src.tools.limpiar;

import java.util.ArrayList;
import java.util.Scanner;

import src.classes.libros.*;

public class Librero extends Usuario {

    public Librero(String nombre, String username, String password) {
        super(nombre, username, password);
    }

    public static void menuLibrero(Scanner scanner, Librero librero, ArrayList<Libro> inventario) {
        int opcion = 0;
        do {
            System.out.println("\n PANEL DE LIBRERO ");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Ver inventario total");
            System.out.println("3. Cerrar sesión");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                limpiar.limpiarConsola();
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
                continue;
            }

            if (opcion == 1) {
                limpiar.limpiarConsola();

                /*
                 * manda a nombrar el libro pero al ingresar el 003 cierra sesion o cuano se
                 * ingresa una id existente
                 * marca error pero aun asi pide ingresar el nombre del libro lo que deberia de
                 * ser que muestra que
                 * id ya existe cuando deberia regresar a poner el id (pendiente por revisar)
                 */

                System.out.print("Ingresa el Título del libro: ");

                String titulo = scanner.nextLine();

                System.out.print("Ingresa el Autor: ");

                String autor = scanner.nextLine();

                System.out.print("Confirme el ingreso del libro\n 1.- Si\n 2.- NO\n");

                try {
                    int tipo = Integer.parseInt(scanner.nextLine());

                    if (tipo == 1) {

                        inventario.add(new LibroDigital(titulo, autor, "PDF"));
                        System.out.println(
                                "¡Libro Digital registrado con éxito por el librero " + librero.getNombre() + "!");
                    } else {
                        System.out.println("Opción inválida. No se registró el libro.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debes ingresar 1 o 2 numéricamente.");
                } finally {
                    // clear
                    limpiar.limpiarConsola();
                }

            } else if (opcion == 2) {
                limpiar.limpiarConsola();
                System.out.println("\n--- Inventario ---");
                for (Libro l : inventario) {
                    l.mostrarInfo();
                }
            } else if (opcion == 3) {
                System.out.println("\n--- Editar Libro ---");
                if (inventario.isEmpty()) {
                    System.out.println("El inventario está vacío. No hay libros para editar.");
                } else {
                    // 1. Mostrar libros con su índice
                    for (int i = 0; i < inventario.size(); i++) {
                        Libro l = inventario.get(i);
                        System.out.println(i + ". " + l.getTitulo() + " (Autor: " + l.getAutor() + ")");
                    }

                    System.out.print("\nIngrese el número del libro a editar: ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine());

                        // 2. Validar que el índice exista
                        if (index >= 0 && index < inventario.size()) {
                            Libro libroAEditar = inventario.get(index);
                            System.out.println("\nEditando: " + libroAEditar.getTitulo());
                            System.out.println("(Presiona ENTER sin escribir nada para conservar el dato actual)");

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

                            System.out.println(" Libro actualizado correctamente.");
                        } else {
                            System.out.println(" Índice inválido.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Error: Debes ingresar un número.");
                    } finally {
                        limpiar.limpiarConsola();
                    }
                }
            }

        } while (opcion != 4);
        limpiar.limpiarConsola();
        System.out.println("Cerrando sesión de librero...");
    }

    @Override
    public void mostrarPanel() {
        System.out.println("Cargando panel de Librero...");
    }
}