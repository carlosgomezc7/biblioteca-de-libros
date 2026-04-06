package src.classes.libros;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import src.tools.limpiar;

//CLASE HIJA EN LA CUAL SE USA POLIMORFISMO EN RELACION CON LA CLASE PADRE
public class LibroDigital extends Libro {
    private String formato;

    public LibroDigital(String titulo, String autor, String formato) {
        super(titulo, autor); // Llama al constructor del padre actualizado
        this.formato = formato; // Se agrega la asignación del formato
    }

    public String getFormato() {
        formato = "pdf";
        return formato;
    }

    // METODO AUDITAR INVENTARIO
    public static void auditarInventario(List<Libro> inventario) {
        System.out.println("\n=== REPORTE DE ESTADO DEL INVENTARIO ===");
        int total = inventario.size();
        int disponibles = total;
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

    // METODO PARA ELIMINAR UN LIBRO
    public static void eliminarLibro(Scanner scanner, List<Libro> inventario) {

        // ELIMINAR UN LIBRO
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

    public static void mostrarInventario(ArrayList<Libro> inventario) {
        System.out.println("\n--- Inventario ---");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Libro l : inventario) {
                if (l != null) {
                    l.mostrarInfo();
                }
            }
        }

    }

    // Polimorfismo: Sobrescribimos mostrarInfo
    @Override
    public void mostrarInfo() {
        // Se cambia idLibro por getIdLibro() ya que el atributo ahora es privado
        System.out.println("[LIBRO DIGITAL] ID: " + getIdLibro() + " | Título: " + titulo + " | Autor: " + autor
                + " | Formato: " + formato);
    }
}