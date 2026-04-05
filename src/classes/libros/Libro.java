package src.classes.libros;

// DEJAR 100% DIGITAL Y ELIMINAR LA LIBRERIA FISICA
public class Libro {
    // Variable estática para llevar el control del autoincremento
    private static int contadorId = 1;

    // Se cambia a private
    private String idLibro;
    protected String titulo;
    protected String autor;
    protected boolean disponible;

    // Constructor actualizado (ya no recibe idLibro como parámetro)
    public Libro(String titulo, String autor) {
        // Se asigna el valor actual del contador y luego se incrementa
        this.idLibro = String.valueOf(contadorId++);
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    // Constructor sobrecargado actualizado
    public Libro(String titulo) {
        this(titulo, "Autor Desconocido");
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void cambiarEstado(boolean estado) {
        this.disponible = estado;
    }

    public void mostrarInfo() {
        System.out.println("ID: " + idLibro + " | Título: " + titulo + " | Autor: " + autor + " | Estado: "
                + (disponible ? "Disponible" : "Prestado"));
    }
}