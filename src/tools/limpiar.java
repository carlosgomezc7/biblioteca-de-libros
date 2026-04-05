package src.tools;

public class limpiar {
    public static void limpiarConsola() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Ejecuta comando 'cls' en Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Ejecuta comando 'clear' en Unix/Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Manejar excepciones si el comando no se puede ejecutar
            System.out.println("No se pudo limpiar la consola: " + e.getMessage());
        }
    }
}