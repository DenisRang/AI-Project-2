import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        ChordProgression cp = new ChordProgression("I IV V");
        Note notess[] = new Note[3];
        notess[0]= new Note(74);
        notess[1] = new Note(77);
        notess[2] = new Note(81);
        Chord chordd = Chord.fromNotes(notess);
        player.play(chordd);player.play(chordd);player.play(chordd);player.play(chordd);player.play(chordd);player.play(chordd);
        System.out.println(chordd.isMinor());

        Chord[] chords = cp.setKey("c5").getChords();
        for (Chord chord : chords) {
            System.out.print("Chord "+chordd+" has these notes: ");
            Note[] notes = chord.getNotes();
            for (Note note : notes) {
                System.out.print(note.getValue()+" ");
            }
            System.out.println();
        }
        player.play(cp);

        chords = cp.setKey("G5").getChords();
        for (Chord chord : chords) {
            System.out.print("Chord "+chord+" has these notes: ");
            Note[] notes = chord.getNotes();
            for (Note note : notes) {
                System.out.print(note.getValue()+" ");
            }
            System.out.println();
        }
        player.play(cp);
    }
}
