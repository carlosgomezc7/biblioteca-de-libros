package src.classes.libros;

public class LibroFisico extends Libro {
    private String ubicacion;

    public LibroFisico(String id, String titulo, String autor, String ubicacion) {
        super(id, titulo, autor);
        this.ubicacion = ubicacion;
    }

    // Polimorfismo: Sobrescribimos mostrarInfo()
    @Override
    public void mostrarInfo() {
        System.out.println("[LIBRO FÍSICO] ID: " + idLibro + " | Título: " + titulo + " | Autor: " + autor
                + " | Ubicación: " + ubicacion);
    }
}