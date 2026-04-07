package src.classes.operaciones;

import java.time.LocalDate;

import src.classes.usuarios.*;
import src.classes.libros.*;

public class Prestamo {
    // Encapsulamiento: Protegemos los datos de la transacción
    private Libro libro;
    private Lector lector;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean estado;

    // Constructor: Al crear un préstamo, se configura todo automáticamente
    public Prestamo(Libro libro, Lector lector, int diasPrestamo) {
        // Validamos que el libro esté disponible antes de prestarlo
        if (libro.isDisponible()) {
            this.libro = libro;
            this.lector = lector;
            this.fechaInicio = LocalDate.now(); // Fecha actual
            this.fechaFin = this.fechaInicio.plusDays(diasPrestamo); // Sumamos los días
            this.estado = true;

            // El préstamo cambia el estado del libro
            this.libro.cambiarEstado(false);
            // Se agrega el getter de nombre y el get de titulo ya que estos atributos se
            // encuentran en protect
            System.out.println("Préstamo aprobado. " + lector.getNombre() + " se lleva: " + libro.getTitulo());
            System.out.println("Fecha límite de devolución: " + this.fechaFin);
        } else {
            System.out.println("Error: El libro '" + libro.getTitulo() + "' no está disponible.");
        }
    }

    // Get para regresar la fecha límite y el libro asociado a este préstamo, útil
    // para mostrar información al usuario
    public LocalDate getfechaFin() {
        return fechaFin;
    }

    // get de libro asociado a este préstamo
    public Libro getLibro() {
        return libro;
    }

    // Método para procesar la devolución
    public void devolverLibro() {
        if (this.estado) {
            this.estado = false;
            // El libro vuelve a estar disponible
            this.libro.cambiarEstado(true);
            System.out.println(
                    "El libro '" + this.libro.getTitulo() + "' ha sido devuelto por " + this.lector.getNombre() + ".");

            // Aquí podríamos agregar lógica para verificar si hay retraso
            verificarRetraso();
        } else {
            System.out.println("Este préstamo ya fue cerrado.");
        }
    }

    // Método privado (encapsulado) que solo la clase Prestamo usa internamente
    private void verificarRetraso() {
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(this.fechaFin)) {
            System.out.println("ATENCIÓN: Entrega con retraso. Se debe aplicar una multa.");
        } else {
            System.out.println("Entrega a tiempo. ¡Gracias!");
        }
    }
}