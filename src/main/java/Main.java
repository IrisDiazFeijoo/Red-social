import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Usuario usuarioActual = null; // Inicialmente, no hay usuario logueado


        // Crear instancia del menú pasando el usuario actual
        Menu menu = new Menu(usuarioActual);
        menu.crearUsuariosPredeterminados();

        // Mostrar el menú principal al usuario
        menu.mostrarMenuPrincipal();

    }

}