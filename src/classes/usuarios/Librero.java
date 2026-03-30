package src.classes.usuarios;

import java.util.ArrayList;
import java.util.Scanner;

import src.classes.libros.*;

public class Librero extends Usuario {

    public Librero(String nombre, String username, String password) {
        super(nombre, username, password);
    }

    public static void menuLibrero(Scanner scanner, Librero librero, ArrayList<Libro> inventario) {
        int opcion = 0;
        do {
            System.out.println("\n PANEL DE LIBRERO ");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Ver inventario total");
            System.out.println("3. Cerrar sesión");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
                continue;
            }
             /*agregar un contador fijo autoincremental para no teclear ids manualmente y disminuir el error humano  */
            if (opcion == 1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.print("Ingresa el ID (Eje. 001): ");
                String id = scanner.nextLine();
                try{
                // Validación de ID duplicado
                boolean idExiste = false;
                for (Libro l : inventario) {
                    if (l.getIdLibro().equals(id)) {
                        idExiste = true;
                        break;
                    }
                }

                if (idExiste) {
                    System.out.println("Error: Ya existe un libro registrado con el ID '" + id);
             
                }
      
                }
                catch(NumberFormatException e){
                    System.out.println("Error: "+ e);
                    
                }
                finally {
                        //clear
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Error: Ya existe un libro registrado con el ID '" + id);
                    }
                    /*manda a nombrar el libro pero al ingresar el 003 cierra sesion o cuano se ingresa una id existente 
                     marca error pero aun asi pide ingresar el nombre del libro lo que deberia de ser que muestra que 
                    id ya existe cuando deberia regresar a poner el id (pendiente por revisar) */
        
            
                System.out.print("Ingresa el Título del libro: ");
                
                String titulo = scanner.nextLine();
                System.out.print("Ingresa el Autor: ");
                
                String autor = scanner.nextLine();

                System.out.print("¿El libro es Digital (1) o Físico (2)? ");
                try {
                    int tipo = Integer.parseInt(scanner.nextLine());

                    if (tipo == 1) {
                        System.out.print("Ingresa el Formato (ej. PDF, EPUB): ");
                        String formato = scanner.nextLine();
                        inventario.add(new LibroDigital(id, titulo, autor, formato));
                        System.out.println(
                                "¡Libro Digital registrado con éxito por el librero " + librero.getNombre() + "!");
                    } else if (tipo == 2) {
                        System.out.print("Ingresa la Ubicación (ej. Estante A): ");
                        String ubicacion = scanner.nextLine();
                        inventario.add(new LibroFisico(id, titulo, autor, ubicacion));
                        System.out.println(
                                "¡Libro Físico registrado con éxito por el librero " + librero.getNombre() + "!");
                    } else {
                        System.out.println("Opción inválida. No se registró el libro.");
                    }
                } 
                catch (NumberFormatException e) {
                    System.out.println("Error: Debes ingresar 1 o 2 numéricamente.");
                }
                finally {
                        //clear
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }

            } else if (opcion == 2) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\n--- Inventario ---");
                for (Libro l : inventario) {
                    l.mostrarInfo();
                }
            }
        } while (opcion != 3);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Cerrando sesión de librero...");
    }

    @Override
    public void mostrarPanel() {
        System.out.println("Cargando panel de Librero...");
    }
}