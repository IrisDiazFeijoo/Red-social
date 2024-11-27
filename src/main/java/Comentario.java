public class Comentario {
    private String contenido;
    private String autor;
    private String idComentario;

    public Comentario(String contenido, String autor, String idComentario) {
        this.contenido = contenido;
        this.autor = autor;
        this.idComentario = idComentario;
    }

    public String getContenido() {
        return contenido;
    }

    public String getAutor() {
        return autor;
    }

    public String getIdComentario() {
        return idComentario;
    }

    @Override
    public String toString() {
        return autor + ": " + contenido;
    }
}

