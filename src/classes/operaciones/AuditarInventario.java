package src.classes.operaciones;

import java.util.List;

import src.classes.libros.Libro;

public class AuditarInventario {
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

}
