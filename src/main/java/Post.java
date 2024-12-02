
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private String autor;
    private String id;
    private Date fecha;
    public  List<Comentario> comentarios;

    public Post(String autor, String id, Date fecha) {
        this.autor = autor;
        this.id = id;
        this.fecha = new Date();// Para que me de la fecha actual
        this.comentarios = new ArrayList<>();

    }

    public String getAutor() {
        return autor;
    }

    public String getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(Comentario comentario){
        comentarios.add(comentario);
    }

    public void eliminarComentario(String comentarioId){
        Comentario comentarioAEliminar = null;
        for (Comentario comentario: comentarios){
            if (comentario.getIdComentario().equals(comentarioId)) {
                comentarioAEliminar = comentario;
                break;
            }
        }
        if (comentarioAEliminar != null) {
            comentarios.remove(comentarioAEliminar);
            System.out.println("Comentario eliminado con éxito.");
        } else {
            System.out.println("Error: no se encontró ningún comentario con ese ID.");
        }

    }
    public void mostrarComentarioConId() {
        if (comentarios.isEmpty()) {
            System.out.println("No hay comentarios en este post.");
            return;
        }
        for (Comentario comentario : comentarios) {
            System.out.println("ID: " + comentario.getIdComentario() + ", Autor: " + comentario.getAutor() + ", Contenido: " + comentario.getContenido());
        }
    }
    public int getNumeroComentarios(){
        return comentarios.size();
    }

    @Override
    public String toString() {
        return "Post ID: " + id + ", Autor: " + autor + ", Fecha: " + fecha;
    }
}



