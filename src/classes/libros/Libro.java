package src.classes.libros;

public class Libro {
    protected String idLibro;
    protected String titulo;
    protected String autor;
    protected boolean disponible;

    // Constructor
    public Libro(String idLibro, String titulo, String autor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public Libro(String id, String titulo) {
        this(id, titulo, "Autor Desconocido");
    }

    public String getTitulo() {
        return titulo;
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