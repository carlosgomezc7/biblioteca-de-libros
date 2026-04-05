package src.classes.libros;

public class LibroDigital extends Libro {
    private String formato;

    public LibroDigital(String titulo, String autor, String formato) {
        super(titulo, autor); // Llama al constructor del padre actualizado
        this.formato = formato; // Se agrega la asignación del formato
    }

    public String getFormato() {
        formato = "pdf";
        return formato;
    }

    // Polimorfismo: Sobrescribimos mostrarInfo()
    @Override
    public void mostrarInfo() {
        // Se cambia idLibro por getIdLibro() ya que el atributo ahora es privado
        System.out.println("[LIBRO DIGITAL] ID: " + getIdLibro() + " | Título: " + titulo + " | Autor: " + autor
                + " | Formato: " + formato);
    }
}