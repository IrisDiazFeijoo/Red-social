import java.util.Date;

public class TextoPost extends Post {


    public TextoPost(String contenido, String autor, String id, Date fecha) {
        super(contenido, autor, id, fecha);

    }
    @Override
    public String toString() {
        return "TextoPost: " + getContenido();
    }
}
