import java.util.Date;

public class VideoPost extends Post {
    private String título;
    private String calidad;
    private int duración;

    public VideoPost(String contenido, String autor, String id, Date fecha, String título, int duración, String calidad) {
        super(contenido, autor, id, fecha);
        this.título = título;
        this.duración = duración;
        this.calidad = calidad;
    }
    @Override
    public String toString() {
        return "VideoPost: " + getContenido() + ", Título: " + título + ", Duración: " + duración + "s, Calidad: " + calidad;
    }
}
