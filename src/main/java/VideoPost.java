import java.util.Date;

public class VideoPost extends Post {
    private String título;
    private String calidad;
    private int duración;

    public VideoPost( String autor, String id, Date fecha, String título, int duración, String calidad) {
        super(autor, id, fecha);
        this.título = título;
        this.duración = duración;
        this.calidad = calidad;
    }
    @Override
    public String toString() {
        return "VideoPost: " + ", Título: " + título + ", Duración: " + duración + "s, Calidad: " + calidad;
    }
}
