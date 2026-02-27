package libros;

public class Libro {
    protected String id; // El diagrama lo llama 'id' en lugar de 'idLibro'
    protected String titulo;
    protected String autor; // Nuevo atributo
    protected boolean disponible;

    // Constructor actualizado
    public Libro(String id, String titulo, String autor) {
        this.id = id;
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

    // Nuevo método principal para aplicar el Polimorfismo
    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Título: " + titulo + " | Autor: " + autor + " | Estado: "
                + (disponible ? "Disponible" : "Prestado"));
    }
}