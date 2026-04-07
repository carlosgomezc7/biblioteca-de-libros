package src.classes.usuarios;

import src.tools.limpiar;
import java.util.Scanner;
import java.util.ArrayList;

/* EN ESTA CLASE SE OPTO POR USAR TODOS LOS METODOS EN UNA SOLA CLASE (USUARIOS) A COMPARACION DE LAS 
OPERACIONES DE LIBROS, CON EL MOTIVO DE MOSTRAR EN ESTE PROYECTO QUE SE PUEDE TRABAJAR CON LOS PACKETES
DE DIFERENTES FORMAS, ¿QUE ES LO MAS OPTIMO?...
LA RESPUESTA PARA NOSOTROS ES QUE DEPENDE DEL PROYECTO TENIENDO EN CUENTA LA ESCALABILIDAD*/

public abstract class Usuario {
    protected String idUsuario;
    protected String nombre;
    protected String username;
    private String password;

    // Constructor actualizado con los nuevos campos
    public Usuario(String idUsuario, String nombre, String email, String username, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
    }

    public Usuario(String nombre, String username, String password) {
        this("U-000", nombre, "correo@default.com", username, password);
    }

    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // SET's PARA EL ACCESO Y LA MODIFICACIÓN DE LOS ATRIBUTOS EN ESTE CASO NOMBRE,
    // USERNAME Y PASSWORD
    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void mostrarPanel();

    // METODO PARA MOSTRAR USUARIOS
    public static void mostrarUsuarios(ArrayList<Usuario> usuariosRegistrados) {
        System.out.println("\n--- Lista de Usuarios ---");
        for (int i = 0; i < usuariosRegistrados.size(); i++) {
            Usuario u = usuariosRegistrados.get(i);
            System.out.println(i + ". " + u.getNombre() + " (" + u.getUsername() + ") - "
                    + u.getClass().getSimpleName());
        }
    }

    // METODO PARA EDITAR USUARIOS
    public static void editarUsuario(ArrayList<Usuario> usuariosRegistrados, Scanner scanner) {

        System.out.println("\n--- Editar Usuario ---");
        for (int i = 1; i < usuariosRegistrados.size(); i++) {
            Usuario u = usuariosRegistrados.get(i);
            System.out.println(i + ". " + u.getNombre() + " (" + u.getUsername() + ")");
        }

        System.out.print("\nIngrese el número del usuario a editar: ");
        int index = Integer.parseInt(scanner.nextLine());

        if (index >= 1 && index < usuariosRegistrados.size()) {
            Usuario usuarioAEditar = usuariosRegistrados.get(index);
            System.out.println("\nEditando a: " + usuarioAEditar.getNombre());
            System.out.println("(Presiona ENTER sin escribir nada para conservar el dato actual)");

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.trim().isEmpty()) {
                usuarioAEditar.setNombre(nuevoNombre);
            }

            System.out.print("Nuevo username: ");
            String nuevoUsername = scanner.nextLine();
            if (!nuevoUsername.trim().isEmpty()) {
                usuarioAEditar.setUsername(nuevoUsername);
            }

            System.out.print("Nueva contraseña: ");
            String nuevaPassword = scanner.nextLine();
            if (!nuevaPassword.trim().isEmpty()) {
                usuarioAEditar.setPassword(nuevaPassword);
            }

            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.out.println("Índice inválido.");
        }
    }
    // METODO PARA ELIMINAR USUARIOS

    public static void eliminarUsuario(ArrayList<Usuario> usuariosRegistrados, Scanner scanner, Administrador admin) {

        System.out.println("\n--- Lista de Usuarios ---");
        for (int i = 1; i < usuariosRegistrados.size(); i++) {
            Usuario u = usuariosRegistrados.get(i);
            System.out.println(i + ". " + u.getNombre() + " (" + u.getUsername() + ") - "
                    + u.getClass().getSimpleName());
        }
        System.out.print("Ingrese el índice del usuario a eliminar: ");
        int index = Integer.parseInt(scanner.nextLine());

        if (index >= 0 && index < usuariosRegistrados.size()) {
            if (usuariosRegistrados.get(index) instanceof Administrador
                    && usuariosRegistrados.get(index).getUsername().equals(admin.getUsername())) {
                System.out.println("No puedes eliminarte a ti mismo.");
            } else {
                usuariosRegistrados.remove(index);
                limpiar.limpiarConsola();
                System.out.println("Usuario eliminado.");
                System.out.println("\n--- Confirmacion de usuarios existentes ---");
                for (int i = 1; i < usuariosRegistrados.size(); i++) {
                    Usuario u = usuariosRegistrados.get(i);
                    System.out.println(i + ". " + u.getNombre() + " (" + u.getUsername() + ") - "
                            + u.getClass().getSimpleName());
                }
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }
}
