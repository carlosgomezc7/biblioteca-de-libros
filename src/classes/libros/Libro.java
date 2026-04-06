package src.classes.libros;

// CLASE PADRE
public class Libro {
    // Variable estática para llevar el control del autoincremento
    private static int contadorId = 1;

    // DECLARACION DE ATRIBUTOS
    private String idLibro;
    protected String titulo;
    protected String autor;
    protected boolean disponible;

    // Constructor
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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