import java.util.Date;

public class ImagenPost extends Post{
    private String titulo;
    private int ancho;
    private int alto;

    public ImagenPost(String titulo, int ancho, int alto,String autor, String id, Date fecha) {
        super(autor, id, fecha);
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }
    @Override
    public String toString() {
        return  "Título: " + titulo + " Imagen " + " Dimensiones: " + ancho + "x" + alto;
    }
}
