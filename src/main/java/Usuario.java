
import com.campusdual.Utils;

import java.util.*;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String email;
    private String nombreUsuario;
    private String contraseña;
    private Set<String> seguidores;// Almacenar los seguidores
    private Set<String> siguiendo;// Almacenar quines sigues
    public List<Post> posts; // Lista para alamcenar los posts del usuario
    // Creamos un mapa en el que tiene una calve tipo String y un valor tipo usuario.


    public Usuario(String nombre, String apellidos, String email, String nombreUsuario, String contraseña) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.seguidores = new HashSet<>();//Un mapa para almacenar a los seguidores.
        this.siguiendo = new HashSet<>();// Un mapa para almacenar a los que sigues.
        this.posts = new ArrayList<>();// Una lista para almacenar los posts.
    }


    public int contarUsuarios() {
        return Menu.getUsuarios().size();
    }



    public void mostrarUsuarios() {
        if (Menu.getUsuarios().isEmpty()) {
            System.out.println("No hay usuario registrados.");
        } else {
            System.out.println("Usuarios registrados: ");
            for (String nombreUsuario : Menu.getUsuarios().keySet()) {
                System.out.println("Usuario: " + nombreUsuario + " --> " + Menu.getUsuarios().get(nombreUsuario));
            }
            System.out.println("Cantidad total de usuarios registrados: " + contarUsuarios());
        }
    }

    public void agregarSeguidor(String seguidor) { //método para agregar seguidor
        seguidores.add(seguidor);
    }

    public void removerSeguidor(String seguidor) {
        seguidores.remove(seguidor);
    }

    public void seguir(String nombreUsuarioASeguir) { //método para agregar a quién sigues
        siguiendo.add(nombreUsuarioASeguir);
    }

    public void dejarDeSeguir(String nombreUsuarioASeguir) {
        siguiendo.remove(nombreUsuarioASeguir);

    }

    public static void seguirUsuario(Usuario usuarioActual) {
        if (usuarioActual == null) {
            System.out.println("Debes iniciar sesión para seguir a alguien.");
            return;
        }
        String nombreUsuarioASeguir = Utils.string("Nombre del usuario que deseas seguir: ");

        Usuario usuarioASeguir = Menu.getUsuarios().get(nombreUsuarioASeguir);

        if (usuarioASeguir == null) {
            System.out.println("El usuario " + nombreUsuarioASeguir + " no existe.");
        } else if (usuarioASeguir.getNombreUsuario().equals(usuarioActual.getNombreUsuario())) {
            System.out.println("No puedes seguirte a ti mismo.");
        } else if (usuarioActual.siguiendo.contains(nombreUsuarioASeguir)) {
            System.out.println("Ya estás siguiendo a " + nombreUsuarioASeguir + ".");
        } else {
            usuarioActual.seguir(nombreUsuarioASeguir);
            usuarioASeguir.agregarSeguidor(usuarioActual.getNombreUsuario());
            System.out.println("Ahora sigues a " + nombreUsuarioASeguir + ".");
        }
    }

    public void dejarDeSeguirUsuario(Usuario usuarioActual) {
        if (usuarioActual == null) {
            System.out.println("Debes iniciar sesión para dejar de seguir a alguien.");
            return;
        }
        String nombreUsuarioADejarDeSeguir = Utils.string("Nombre del usuario que deseas dejar de seguir: ");

        if (!usuarioActual.siguiendo.contains(nombreUsuarioADejarDeSeguir)) {
            System.out.println("No sigues al usuario " + nombreUsuarioADejarDeSeguir + ".");
        } else {
            Usuario usuarioADejarDeSeguir = Usuario.getUsuarios().get(nombreUsuarioADejarDeSeguir);

            if (usuarioADejarDeSeguir != null) {
                usuarioActual.dejarDeSeguir(nombreUsuarioADejarDeSeguir);
                usuarioADejarDeSeguir.removerSeguidor(usuarioActual.getNombreUsuario());

                System.out.println("Has dejado de seguir a " + nombreUsuarioADejarDeSeguir + ".");
            } else {
                System.out.println("El usuario " + nombreUsuarioADejarDeSeguir + " no existe.");
            }
        }
    }

    public void agregarPost(Post nuevoPost) {
        posts.add(nuevoPost);
    }

    public void eliminarPost() {
        Scanner scanner = new Scanner(System.in);
        mostrarPostConId();
        System.out.print("Escribe el ID del post a eliminar: ");
        String id = scanner.nextLine();
        posts.removeIf(post -> post.getId().equals(id));
        System.out.println("Post eliminado con éxito.");
    }
    public void mostrarPostConId(){
        if (posts.isEmpty()){
            System.out.println("No tienes posts");
            return;
        }
        System.out.println("Posts: ");
        for (Post post: posts){
            System.out.println("ID: " + post.getId() + " Contenido: " + post.getContenido());
        }

    }
    public void mostrarComentariosDePost(String postId) {
        Post postSeleccionado = buscarPostPorId(postId);

        if (postSeleccionado == null) {
            System.out.println("No se encontró un post con el ID especificado.");
            return;
        }

        // Mostrar los comentarios del post seleccionado
        if (postSeleccionado.getComentarios().isEmpty()) {
            System.out.println("El post seleccionado no tiene comentarios.");
        } else {
            System.out.println("Comentarios en el post con ID " + postId + ":");
            postSeleccionado.mostrarComentarioConId();
        }
    }

    private Post buscarPostPorId(String postId) {
        for (Post post : posts) {
            if (post.getId().equals(postId)) {
                return post;
            }
        }
        return null;
    }


    public void agregarComentarioAPost() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el nombre de usuario a quien quieres comentar: ");
        String nombreUsuario = scanner.nextLine();

        Usuario usuarioSeleccionado = Usuario.getUsuarios().get(nombreUsuario);
        if (usuarioSeleccionado == null) {
            System.out.println("No se encontró un usuario con ese nombre");
            return;
        }

        if (usuarioSeleccionado.getPosts().isEmpty()) {
            System.out.println("No hay posts publicados");
            return;
        }

        System.out.println("Los posts de " + nombreUsuario + " son: ");
        List<Post> postsUsuario = usuarioSeleccionado.getPosts();
        for (int i = 0; i < postsUsuario.size(); i++) {
            Post post = postsUsuario.get(i);
            System.out.println((i + 1) + ". ID: " + post.getId() + " | Contenido: " + post.getContenido());
        }

        System.out.print("Escribe el ID del post al que deseas comentar: ");
        String postId = scanner.nextLine();
        Post postSeleccionado= null;

        for (Post post : postsUsuario) {
            if (post.getId().equals(postId)) {
                postSeleccionado = post;
                break;
            }
        }

        if (postSeleccionado == null) {
            System.out.println("No se encontró un post con ese ID.");
            return;
        }
        System.out.print("Escribe tu comentario: ");
        String contenidoComentario = scanner.nextLine();
        String idComentario = "comentario " + (postSeleccionado.getComentarios().size() + 1);
        Comentario nuevoComentario = new Comentario(contenidoComentario, this.nombreUsuario, idComentario);
        postSeleccionado.agregarComentario(nuevoComentario);
        System.out.println("Comentario agregado con éxito al post!");
    }


    public void eliminarComentarioDePost() {
        if (posts.isEmpty()) {
            System.out.println("No tienes posts.");
            return;
        }
        mostrarPostConId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el ID del post cual deseas eliminar un comentario:");
        String postId = scanner.nextLine();
        Post postSeleccionado = buscarPostPorId(postId);

        if (postSeleccionado == null) {
            System.out.println("No se encontró un post con el ID especificado.");
            return;
        }
        if (postSeleccionado.getComentarios().isEmpty()) {
            System.out.println("El post seleccionado no tiene comentarios.");
            return;
        }

        postSeleccionado.mostrarComentarioConId();
        System.out.print("Escribe el ID del comentario que deseas eliminar: ");
        String comentarioId = scanner.nextLine();
        Comentario comentarioAEliminar = buscarComentarioPorId(comentarioId, postSeleccionado);
        if (comentarioAEliminar != null) {
            postSeleccionado.eliminarComentario(comentarioId);
            System.out.println("Comentario eliminado con éxito.");
        } else {
            System.out.println("No se encontró un comentario con el ID especificado.");
        }
    }


    public Comentario buscarComentarioPorId(String comentarioId, Post post) {
        for (Comentario comentario : post.getComentarios()) {
            if (comentario.getIdComentario().equals(comentarioId)) {
                return comentario;
            }
        }
        return null; // Si no se encuentra, retornamos null
    }

    public static Map<String, Usuario> getUsuarios() {
        return Menu.getUsuarios();
    }

    public String getContraseña() {
        return contraseña;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Apellidos: " + apellidos + " Email: " + email + " Nombre de usuario: " + nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
