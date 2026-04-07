package src.classes.operaciones;

import java.util.ArrayList;

import src.classes.libros.Libro;

public class MostrarInventario {

    // METODO PARA MOSTRAR INVENTARIO
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

}
