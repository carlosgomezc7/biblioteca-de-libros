package src.classes.usuarios;

import src.tools.limpiar;
import src.classes.libros.*;
import java.util.ArrayList;

import java.util.Scanner;

public class Administrador extends Usuario {

    public Administrador(String nombre, String username, String password) {
        super(nombre, username, password);

    }

    public static void menuAdministrador(Scanner scanner, Administrador admin, ArrayList<Libro> inventario,
            ArrayList<Usuario> usuariosRegistrados) {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n--- PANEL DE ADMINISTRADOR ---");
            System.out.println("1. Libros");
            System.out.println("2. Usuarios");
            System.out.println("3. Cerrar sesión");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Error: Ingresa un número válido.");
                continue;
            } finally {
                // clear
                limpiar.limpiarConsola();
            }

            if (opcion == 1) {
                int subOpcion = 0;
                while (subOpcion != 6) {
                    System.out.println("\n--- PANEL DE LIBROS ---");
                    System.out.println("1. Inventario total");
                    System.out.println("2. Reporte de Estado");
                    System.out.println("3. Registrar nuevo libro");
                    System.out.println("4. Editar un libro");
                    System.out.println("5. Eliminar un libro");
                    System.out.println("6. Atras");
                    System.out.print("Elige una opción: ");

                    try {
                        subOpcion = Integer.parseInt(scanner.nextLine());
                        limpiar.limpiarConsola();

                        if (subOpcion == 1) {
                            // MOSTRAR INVENTARIO
                            src.classes.operaciones.MostrarInventario.mostrarInventario(inventario);

                        } else if (subOpcion == 2) {
                            // AUDITAR INVENTARIO
                            System.out.println("\n--- Reporte de Estado ---");
                            src.classes.operaciones.AuditarInventario.auditarInventario(inventario);

                        } else if (subOpcion == 3) {
                            // REGISTRAR UN LIBRO
                            src.classes.operaciones.RegistrarLibro.registrarLibro(scanner, null);

                        } else if (subOpcion == 4) {
                            // EDITAR UN LIBRO
                            src.classes.operaciones.EditarLibro.editarLibro(scanner, inventario, null, null, null);

                        } else if (subOpcion == 5) {
                            // ELIMINAR UN LIBRO
                            src.classes.operaciones.EliminarLibro.eliminarLibro(scanner, inventario);

                        }

                    } catch (NumberFormatException e) {
                        limpiar.limpiarConsola();
                        System.out.println("Error: Ingresa un número válido para el submenú.");
                    }
                }

            } else if (opcion == 2) {
                int subOpcion = 0;
                while (subOpcion != 5) {
                    System.out.println("\n--- CONTROL DE USUARIOS ---");
                    System.out.println("1. Mostrar usuarios");
                    System.out.println("2. Registrar un usuario"); // NUEVA OPCIÓN
                    System.out.println("3. Editar un usuario");
                    System.out.println("4. Eliminar un usuario");
                    System.out.println("5. Atrás");
                    System.out.print("Elige una opción: ");

                    try {
                        subOpcion = Integer.parseInt(scanner.nextLine());
                        if (subOpcion == 1) {
                            // MOSTRAR USUARIOS
                            src.classes.usuarios.Usuario.mostrarUsuarios(usuariosRegistrados);
                        } else if (subOpcion == 2) {
                            // REGISTRAR USUARIO
                            src.classes.usuarios.Usuario.registrarUsuario(usuariosRegistrados, scanner);
                        } else if (subOpcion == 3) {
                            // EDITAR USUARIO
                            src.classes.usuarios.Usuario.editarUsuario(usuariosRegistrados, scanner);
                        } else if (subOpcion == 4) {
                            // ELIMINAR USUARIO
                            src.classes.usuarios.Usuario.eliminarUsuario(usuariosRegistrados, scanner, admin);
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Error en la operación de usuarios. Verifica que el dato ingresado sea correcto.");
                    }
                }

            }
        }
        System.out.println("Cerrando sesión de administrador...");
    }

    @Override
    public void mostrarPanel() {
        System.out.println("Cargando panel de Administración...");
    }
}