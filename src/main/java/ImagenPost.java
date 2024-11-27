import java.util.Date;

public class ImagenPost extends Post{
    private String titulo;
    private int ancho;
    private int alto;

    public ImagenPost(String contenido, String autor, String id, Date fecha, String titulo, int ancho, int alto) {
        super(contenido, autor, id, fecha);
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }
    @Override
    public String toString() {
        return  "Título: " + titulo + "Imagen: " + getContenido() +  " Dimensiones: " + ancho + "x" + alto;
    }
}
