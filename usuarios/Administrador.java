package usuarios;

import libros.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrador extends Usuario {

    public Administrador(String nombre, String username, String password) {
        super(nombre, username, password);
    }

    public void registrarLibro(List<Libro> inventario, String idLibro, String titulo) {
        Libro nuevoLibro = new Libro(idLibro, titulo);
        inventario.add(nuevoLibro);
        System.out.println("✅ El administrador " + this.nombre + " ha registrado exitosamente: " + titulo);
    }

    public void auditarInventario(List<Libro> inventario) {
        // ... (Se mantiene igual tu código original)
    }

    // SUB-MENÚ DEL ADMINISTRADOR
    public static void menuAdministrador(Scanner scanner, ArrayList<Libro> inventario) {
        int opcion;
        do {
            System.out.println("\n--- PANEL DE ADMINISTRADOR ---");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Ver inventario total");
            System.out.println("3. Cerrar sesión");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 1) {
                System.out.print("Ingresa el ID (ej. 003): ");
                String id = scanner.nextLine();
                System.out.print("Ingresa el Título del libro: ");
                String titulo = scanner.nextLine();
                System.out.print("Ingresa el Autor: ");
                String autor = scanner.nextLine();

                System.out.print("¿El libro es Digital (1) o Físico (2)? ");
                int tipo = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                if (tipo == 1) {
                    System.out.print("Ingresa el Formato (ej. PDF, EPUB): ");
                    String formato = scanner.nextLine();
                    inventario.add(new LibroDigital(id, titulo, autor, formato));
                    System.out.println("✅ ¡Libro Digital registrado con éxito!");
                } else if (tipo == 2) {
                    System.out.print("Ingresa la Ubicación (ej. Estante A): ");
                    String ubicacion = scanner.nextLine();
                    inventario.add(new LibroFisico(id, titulo, autor, ubicacion));
                    System.out.println("✅ ¡Libro Físico registrado con éxito!");
                } else {
                    System.out.println("❌ Opción inválida. No se registró el libro.");
                }

            } else if (opcion == 2) {
                System.out.println("\n--- Inventario ---");
                for (Libro l : inventario) {
                    l.mostrarInfo(); // Polimorfismo: Imprimirá los datos según sea digital o físico
                }
            }
        } while (opcion != 3);
        System.out.println("Cerrando sesión de administrador...");
    }

    @Override
    public void mostrarPanel() {
        System.out.println("Cargando panel de Administración con privilegios elevados para: " + this.nombre + "...");
    }
}