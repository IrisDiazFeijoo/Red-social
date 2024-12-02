import java.util.Date;

public class TextoPost extends Post {


    private String contenido;

    public TextoPost(String contenido, String autor, String id, Date fecha) {
        super( autor, id, fecha);
        this.contenido = contenido;

    }
    @Override
    public String toString() {
        return "Contenido: " + contenido;
    }
    public String getContenido() {
        return contenido;
    }

}
