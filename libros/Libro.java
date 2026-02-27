package libros;

public class Libro {
    protected String idLibro; // El diagrama lo llama 'id' en lugar de 'idLibro'
    protected String titulo;
    protected String autor; // Nuevo atributo
    protected boolean disponible;

    // Constructor actualizado
    public Libro(String idLibro, String titulo, String autor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    // Sobrecarga para no romper tu Index.java
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