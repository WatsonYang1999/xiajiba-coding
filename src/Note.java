import javax.sound.midi.Track;

public class Note {
    private int scale;
    private int duration;
    private int tick;
    public Note(int scale,int duration,int tick){
        this.scale = scale;
        this.duration = duration;
        this.tick = tick;
    }
}
