import org.jfugue.midi.MidiFileManager;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException, IOException {
        Swarm swarm = new Swarm(100000);
        int i = 0;
        while (Swarm.f(Swarm.globalBestPos) != 0) {
            swarm.nextIteration(++i);
            System.out.println("Iteration number: " + i);
            System.out.println("Global best sequence of chords: " + Swarm.globalBestPos);
            System.out.println("Fitness function of the global best: " + Swarm.f(Swarm.globalBestPos));
            System.out.println("____________________________________________________");
        }

        Chord[] chords = new Chord[16];
        Note[] notes;
        for (i = 0; i < 16; i++) {
            notes = new Note[3];
            for (int j = 0; j < 3; j++) {
                notes[j] = new Note(Swarm.globalBestPos.getCoordinates()[i % Coordinates.DIMENSION].getNote(j));
            }
            chords[i] = Chord.fromNotes(notes);
        }
        org.jfugue.pattern.Pattern pattern = new org.jfugue.pattern.Pattern(chords);
        MidiFileManager.savePatternToMidi(pattern, new File("MySong.midi"));
    }
}
