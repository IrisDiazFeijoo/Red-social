import com.campusdual.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Menu {
    private Usuario usuarioActual;
    private static Map<String, Usuario> usuarios = new HashMap<>(); // Creamos un mapa en el que tiene una calve tipo String y un valor tipo usuario.

    public Menu(Usuario usuarioActual) {
        this.usuarioActual = null ; // No hay ningún usuario logueado

    }

    public void mostrarMenuPrincipal() {
        int opcion;
        while (true){
            if (usuarioActual == null) {
                System.out.println("\nMENÚ PRINCIPAL");
                System.out.println("1. Crear ususario");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Salir");
                opcion = Utils.integer("Selecciona una opción: ");

                switch (opcion) {
                    case 1:
                        createUsuario();
                        break;
                    case 2:
                        iniciarSesion();
                        break;
                    case 3:
                        System.out.println("¡Hasta luego!");
                        return;
                    default:
                        System.out.println("Opción no válida, por favor intenta nuevamente.");
                }
            } else {
                System.out.println("\nMENÚ PRINCIPAL");
                System.out.println("1. Crear post");
                System.out.println("2. Eliminar post");
                System.out.println("3. Agregar un comentario a post");
                System.out.println("4. Eliminar comentario");
                System.out.println("5. Ver posts y comentarios");
                System.out.println("6. Ver muro");
                System.out.println("7. Seguir a un usuario");
                System.out.println("8. Dejar de seguir a un usuario");
                System.out.println("9. Eliminar usuario");
                System.out.println("10. Salir");
                opcion = Utils.integer("Selecciona una opción: ");

                switch (opcion) {
                    case 1:
                        int tipoPost;
                        System.out.println("Elige el tipo de post que deseas publicar");
                        System.out.println("1. Texto");
                        System.out.println("2. Imagen");
                        System.out.println("3. Video");
                        tipoPost = Utils.integer("Selecciona una opción: ");

                        String idPost = "post" + (usuarioActual.getPosts().size() + 1);

                        switch (tipoPost){
                            case 1:
                                String contenidoTexto = Utils.string("Escribe el contenido de tu post:");
                                TextoPost textoPost = new TextoPost(contenidoTexto, usuarioActual.getNombreUsuario(), idPost, new Date());
                                usuarioActual.agregarPost(textoPost);
                                System.out.println("Post creado con éxito");
                                break;
                            case 2:
                                String tituloImagen = Utils.string("Escribe el título de la imagen: ");
                                int ancho = Utils.integer("Ingresa el ancho de la imagen: ");
                                int alto = Utils.integer("Ingresa el alto de la imagen: ");
                                ImagenPost imagenPost = new ImagenPost(tituloImagen, ancho, alto, usuarioActual.getNombreUsuario(), idPost, new Date());
                                usuarioActual.agregarPost(imagenPost);
                                System.out.println("Post de imagen creado con éxito.");
                                break;
                            case 3:
                                String tituloVideo = Utils.string("Escribe el título del video: ");
                                int duracion = Utils.integer("Ingresa la duración del video (en segundos): ");
                                String calidad = Utils.string("Especifica la calidad del video (por ejemplo, 720p, 1080p) ");
                                VideoPost videoPost = new VideoPost(usuarioActual.getNombreUsuario(), idPost, new Date(), tituloVideo, duracion, calidad);
                                usuarioActual.agregarPost(videoPost);
                                System.out.println("Post de video creado con éxito.");
                                break;
                            default:
                                System.out.println("Opción no válida");
                        }
                        break;
                    case 2:
                        if (usuarioActual != null) {
                            usuarioActual.eliminarPost();
                        } else {
                            System.out.println("No estás logueado. Debes iniciar sesión primero.");
                        }
                        break;
                    case 3:
                        if (usuarioActual != null) {
                            usuarioActual.agregarComentarioAPost();
                        } else {
                            System.out.println("No estás logueado. Debes iniciar sesión primero.");
                        }
                        break;
                    case 4:
                        if (usuarioActual != null) {
                            usuarioActual.eliminarComentarioDePost();
                        } else {
                            System.out.println("No estás logueado. Debes iniciar sesión primero.");
                        }
                        break;
                    case 5:
                        verPostsYComentarios();
                        break;
                    case 6:
                        usuarioActual.MostrarMuro();
                        break;
                    case 7:
                        Usuario.seguirUsuario(usuarioActual);
                        break;
                    case 8:
                        if (usuarioActual != null) {
                            usuarioActual.dejarDeSeguirUsuario(usuarioActual);
                        } else {
                            System.out.println("No estás logueado. Debes iniciar sesión primero.");
                        }
                        break;
                    case 9:
                        if (usuarioActual != null) {
                            eliminarUsuario();
                        } else {
                            System.out.println("No estás logueado. Debes iniciar sesión primero.");
                        }
                        break;
                    case 10:
                        System.out.println("¡Hasta luego!");
                        return ;
                    default:
                        System.out.println("Opción no válida, por favor intenta nuevamente.");
                }

            }
        }
    }

    public void createUsuario() {
        String nombre;
        while(true){
             nombre = Utils.string("Escribe tu nombre: ").trim();
            if (nombre.isEmpty()) {
                System.out.println("El campo nombre no puede estar vacío ni contener solo espacios..");
                } else{
                break;
            }
        }
        String apellidos;
        while(true){
            apellidos = Utils.string("Escribe tus apellidos: ").trim();
            if (apellidos.isEmpty()) {
                System.out.println("El campo apellidos no puede estar vacío ni contener solo espacios..");
            } else{
                break;
            }
        }
        String email;
        while(true){
            email = Utils.string("Escribe tu email: ").trim();
            if (email.isEmpty()) {
                System.out.println("El campo email no puede estar vacío ni contener solo espacios..");
            } else{
                break;
            }
        }
        String nombreUsuario;
        while(true){
            nombreUsuario = Utils.string("Escribe tu nombre de usuario: ").trim();
            if (nombreUsuario.isEmpty()) {
                System.out.println("El campo nombre de usuario no puede estar vacío ni contener solo espacios..");
            }else{
                break;
            }
        }
        String contraseña;
        while(true){
            contraseña = Utils.string("Escribe tu contraseña: ").trim();
            if (contraseña.isEmpty()) {
                System.out.println("El campo contraseña no puede estar vacío ni contener solo espacios..");
            } else{
                break;
            }
        }
        String confirmarContraseña = Utils.string("Confirma tu contraseña: ").trim();

        if (!contraseña.equals(confirmarContraseña)) {
            System.out.println("Las contraseñas no coinciden. Inténtalo de nuevo.");
            return;
        }
        if (usuarios.containsKey(nombreUsuario)) {
            System.out.println("El nombre del usuario ya existe: " + nombreUsuario);
        } else {
            Usuario nuevoUsuario = new Usuario(nombre, apellidos, email, nombreUsuario, contraseña);
            usuarios.put(nombreUsuario, nuevoUsuario);// indicacamos que la clave es el atributo nombreUsuario y el valor es el usuario en si (el objeto que tiene todos los del usuario).
            System.out.println("Usuario añadido: " + nuevoUsuario);
        }
    }
    public void eliminarUsuario() {
        String nombreUsuarioConfirmacion = Utils.string("Escriba tu nombre de usuario para confirmar que quieres eliminar tu cuenta: ");
        if (nombreUsuarioConfirmacion.equals(this.usuarioActual.getNombreUsuario())) {
            usuarios.remove(usuarioActual.getNombreUsuario());
            System.out.println("Tu cuenta ha sido elimada.");
            usuarioActual= null;

        } else {
            System.out.println("El nombre de usuario no coincide. No se puede eliminar la cuenta. ");
        }
    }


    private void iniciarSesion() {
        if (usuarioActual != null) {
            System.out.println("Actualmente estás conectado como " + usuarioActual.getNombreUsuario() + ".");
            String respuesta = Utils.string("¿Deseas cerrar sesión? (s/n)");

            if (!respuesta.equalsIgnoreCase("s")) {
                System.out.println("Operación cancelada. Sigues conectado como " + usuarioActual.getNombreUsuario() + ".");
                return;
            }
            cerrarSesion();
        }
        String nombreUsuario = Utils.string("Ingresa tu nombre de usuario: ");
        if (nombreUsuario.isEmpty()) {
            System.out.println("El nombre de usuario no puede estar vacío.");
            return;
        }

        System.out.print("Ingresa tu contraseña: ");
        String contraseña = Utils.string("Ingresa tu contraseña: ");
        if (contraseña.isEmpty()) {
            System.out.println("La contraseña no puede estar vacía.");
            return;
        }

        Usuario usuario = this.usuarios.get(nombreUsuario);

        if (usuario == null) {
            System.out.println("El nombre de usuario no existe.");
            return;
        }

        if (usuario.getContraseña().equals(contraseña)) {
            usuarioActual = usuario;
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuarioActual.getNombreUsuario());
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    }
    private void cerrarSesion() {
        System.out.println("Cerrando sesión de " + usuarioActual.getNombreUsuario() + "...");
        usuarioActual = null;
        System.out.println("Sesión cerrada.");
    }

    public void verPostsYComentarios() {
        if (usuarioActual==null || usuarioActual.getPosts().isEmpty()) {
            System.out.println("No tienes posts publicados.");
            return;
        }
        for(Post post: usuarioActual.getPosts()){
            System.out.println("\n----------------------");
            System.out.println("Autor: " + post.getAutor());
            System.out.println("Fecha: " + post.getFecha());
            System.out.println(post.toString());
            if (post.getComentarios().isEmpty()) {
                System.out.println("No hay comentarios en este post.");
            } else {
                System.out.println("Comentarios:");
                for (Comentario comentario : post.getComentarios()) {
                    System.out.println("- " + comentario.getContenido() + " (Autor: " + comentario.getAutor() + ")");
                }
            }

        }
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void crearUsuariosPredeterminados() { //creo algunos usuarios predeterninados para no tener que ir metiendolos uno a uno
        Usuario usuario1 = new Usuario("Juan", "Pérez", "juan.perez@gmail.com", "juanperez", "12345");
        Usuario usuario2 = new Usuario("Ana", "Borrajo", "anaborrajo@gmail.com", "2", "ABCD");
        Usuario usuario3 = new Usuario("Adrian", "Martínez", "adri.m@gmail.com", "Adri.m", "euquesei");
        Usuario usuario4 = new Usuario("Gaia", "Moran", "gaiai.moran@gmail.com", "AirGaia", "123");

        this.usuarios.put("juanperez", usuario1);
        this.usuarios.put("anita", usuario2);
        this.usuarios.put("Adri.m", usuario3);
        this.usuarios.put("AirGaia", usuario4);

        //Posts de Juanperez

        Post post1 = new TextoPost("Hola mundo! Este es mi primer post", usuario1.getNombreUsuario(), "post1", new Date());
        Post post2 = new TextoPost("Hola, estaré por aquí a partir de ahora!", usuario1.getNombreUsuario(), "post2", new Date());
        Post post3 = new ImagenPost("El solpor gallego",60,120,"juanperez","post3",new Date());

        //Posts de anita

        Post post4 =new TextoPost("Hola! Mi nomre es anita y disfurto con el contenido audiovisual",usuario2.getNombreUsuario(),"post4",new Date());
        Post post5 = new ImagenPost("Un atarceder en Fisterra",600,500,"anita","post5",new Date());

        //posts de airGaia

        Post post6 =new TextoPost("Hola gente, soy AirGaia y soy una fanática del deporte.",usuario4.getNombreUsuario(),"post6",new Date());

        String contenidoComentarioPost1 = "¡Hola juan, que bueno que estés por aquí!";
        String idComentarioPost1 = "comentario1";
        String contenidoComentarioPost2 = "¡Qué bien ver que estás activo en la comunidad!";
        String idComentarioPost2 = "comentario2";
        String contenidoComentarioPost3 = "¡Qué foto más bonita!";
        String idComentarioPost3 = "comentario3";

        String contenidoComentarioPost4 = "Hola Ana, me encantará ver tu contenido!";
        String idComentarioPost4 = "Comentario1";
        String contenidoComentarioPost5 = "Hola Ana, me encantará ver tu contenido!";
        String idComentarioPost5 = "Que imagen más bonita anita, es un sitio genial para ver el atardecer";


        usuario1.getPosts().add(post1);
        usuario1.getPosts().add(post2);
        usuario1.getPosts().add(post3);

        usuario2.getPosts().add(post4);
        usuario2.getPosts().add(post5);

        usuario4.getPosts().add(post6);

        Comentario comentarioAnita = new Comentario(contenidoComentarioPost1, "anita", idComentarioPost1);
        Post postDeJuan1 = usuario1.getPosts().get(0);
        postDeJuan1.agregarComentario(comentarioAnita);
        Comentario comentarioAirGaia = new Comentario(contenidoComentarioPost2, "AirGaia", idComentarioPost2);
        Post postDeJuan2 = usuario1.getPosts().get(1);
        postDeJuan2.agregarComentario(comentarioAirGaia);
        Comentario comentarioAdriM = new Comentario(contenidoComentarioPost3, "Adri.m", idComentarioPost3);
        Post postDeJuan3 = usuario1.getPosts().get(2);
        postDeJuan3.agregarComentario(comentarioAdriM);

        Comentario comentarioJuanPerez = new Comentario(contenidoComentarioPost5, "juanperez", idComentarioPost5);
        Post postDeAnita1 = usuario2.getPosts().get(0);
        postDeAnita1.agregarComentario(comentarioJuanPerez);


        usuario1.seguir("anita");
        usuario2.agregarSeguidor("juanperez");
        usuario1.seguir("AirGaia");
        usuario4.agregarSeguidor("juanperez");
        usuario1.seguir("Adri.m");
        usuario3.agregarSeguidor("juanperez");


        usuario2.seguir("juanperez");
        usuario1.agregarSeguidor("anita");
        usuario2.seguir("Adri.m");
        usuario3.agregarSeguidor("anita");
        usuario2.seguir("AirGaia");
        usuario4.agregarSeguidor("anita");


    }
    public static void mostrarListaUsuarios() {
        for (String key : usuarios.keySet()) {
            System.out.println(key);
        }
    }

    public static Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
}
