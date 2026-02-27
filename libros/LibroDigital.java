package libros;

public class LibroDigital extends Libro {
    private String formato; // Ejemplo: PDF, EPUB

    public LibroDigital(String id, String titulo, String autor, String formato) {
        super(id, titulo, autor); // Llama al constructor del padre
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }

    // Polimorfismo: Sobrescribimos mostrarInfo()
    @Override
    public void mostrarInfo() {
        System.out.println("[LIBRO DIGITAL] ID: " + id + " | Título: " + titulo + " | Autor: " + autor
                + " | Formato: " + formato);
    }
}