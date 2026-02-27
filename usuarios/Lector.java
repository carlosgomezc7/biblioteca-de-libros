package usuarios; //EMPAQUETA USUARIOS Y SUS RELACIONADOS

import libros.*; //SE IMPORTA TODA LA CARPETA DE LIBROS
import operaciones.*; //SE IMPORTA TODA LA CARPETA OPERACIONES

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
        System.out.println("⬇️ Descargando libro digital: " + libroDigital.getTitulo() + " en formato "
                + libroDigital.getFormato());
    }

    public void mostrarMisLibros() {
        // ... (Se mantiene igual tu código original)
    }

    public void devolverLibro(Prestamo prestamo) {
        // ... (Se mantiene igual tu código original)
    }

    public static void menuLector(Scanner scanner, Lector lector, ArrayList<Libro> inventario) {
        int opcion;
        do {
            System.out.println("\n--- PANEL DE LECTOR ---");
            System.out.println("1. Ver catálogo y pedir libro");
            System.out.println("2. Ver mis libros prestados");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Cerrar sesión");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.println("\n--- Catálogo Disponible ---");
                for (int i = 0; i < inventario.size(); i++) {
                    if (inventario.get(i).isDisponible()) {
                        System.out.print(i + ". ");
                        inventario.get(i).mostrarInfo(); // Polimorfismo: muestra si es físico o digital
                    }
                }

                System.out.print("Ingresa el número del libro a pedir (o -1 para cancelar): ");
                int seleccion = scanner.nextInt();

                if (seleccion >= 0 && seleccion < inventario.size() && inventario.get(seleccion).isDisponible()) {
                    Libro libroSeleccionado = inventario.get(seleccion);
                    Prestamo nuevoPrestamo = new Prestamo(libroSeleccionado, lector, 7);
                    lector.agregarPrestamo(nuevoPrestamo);
                    System.out.println("✅ ¡Libro asignado a tu cuenta!");

                    // Si el libro es digital, se llama al método de descarga automáticamente
                    if (libroSeleccionado instanceof LibroDigital) {
                        lector.descargar((LibroDigital) libroSeleccionado);
                    }
                }
            }

            else if (opcion == 2) {
                lector.mostrarMisLibros();
            }

            else if (opcion == 3) {
                // ... (Se mantiene igual tu código original)
            }
        } while (opcion != 4);
        System.out.println("Cerrando sesión de lector...");
    }

    @Override
    public void mostrarPanel() {
        System.out.println("Cargando panel de Lector: " + this.nombre + "...");
    }
}