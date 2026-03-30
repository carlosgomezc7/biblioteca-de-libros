package src.classes.usuarios;
//CREAR EN CONSOLA EDITAR NOMBRES DE ROLES Y PERFILES
//INICIAR SESION O CREAR CUENTA CON UN ARRAYLIST DINAMICO
//VALIDACION CORREO EN TIEMPO REAL A TRAVES DE LA WEB AL FINAL
//PDF LO ABRA Y DESCARGA O SI DA TIEMPO EN UNA NUBE EN TIEMPO REAL
//INTERFAZ GRAFICA NIVEL EXPERT
public abstract class Usuario {
    protected String id;
    protected String nombre;
    protected String email;
    protected String username;
    private String password;

    // Constructor actualizado con los nuevos campos
    public Usuario(String id, String nombre, String email, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Sobrecarga del constructor para no romper tu Index.java actual
    public Usuario(String nombre, String username, String password) {
        this("U-000", nombre, "correo@default.com", username, password);
    }

    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    // Nuevo método según el diagrama
    public void solicitarPrestamo() {
        System.out.println(this.nombre + " está solicitando un préstamo...");
    }

    public abstract void mostrarPanel();

    
    
}