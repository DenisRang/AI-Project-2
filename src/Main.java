import PSO1.Coordinates;
import PSO1.Swarm;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException, IOException {
        PSO1.Swarm swarm1 = new PSO1.Swarm(100000);
        int i = 0;
        while (PSO1.Swarm.f(PSO1.Swarm.globalBestPos) != 0) {
            swarm1.nextIteration(++i);
            System.out.println("Iteration number: " + i);
            System.out.println("Global best sequence of chords: " + PSO1.Swarm.globalBestPos);
            System.out.println("Fitness function of the global best: " + PSO1.Swarm.f(PSO1.Swarm.globalBestPos));
            System.out.println("____________________________________________________");
        }
        int[][] chords = new int[16][3];
        for (i = 0; i < 16; i++) {
            chords[i] = PSO1.Swarm.globalBestPos.getCoordinates()[i % Coordinates.DIMENSION].getNotes();
        }

        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        PSO2.Swarm swarm2 = new PSO2.Swarm(100000, chords);
        i = 0;
        while (PSO2.Swarm.f(PSO2.Swarm.globalBestPos) != 0) {
            swarm2.nextIteration(++i);
            System.out.println("Iteration number: " + i);
            System.out.println("Global best sequence of chords: " + PSO2.Swarm.globalBestPos);
            System.out.println("Fitness function of the global best: " + PSO2.Swarm.f(PSO2.Swarm.globalBestPos));
            System.out.println("____________________________________________________");
        }
        int[] notes = new int[32];
        for (i = 0; i < 32; i++) {
            notes[i] = PSO2.Swarm.globalBestPos.getCoordinates()[i];
        }

        musicTranscription(notes.length, notes, chords, 120, 4 / 4);
    }

    private static void createMidiFile(String musicString, int tempo) // throws IOException, InvalidMidiDataException
    {
        String midiFileNameBeginning = "Result ";
        String midiFileNameEnd = ".mid";
        Pattern pattern = new Pattern(musicString).setVoice(0).setInstrument("PIANO").setTempo(tempo);
        try {
            MidiFileManager.savePatternToMidi(pattern, new File(midiFileNameBeginning + midiFileNameEnd));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void musicTranscription(int notesAmount, int[] notes, int[][] chords, int tempo, float timeSignature) //throws IOException, InvalidMidiDataException
    {
        String barEnd = "| ";
        String currentNoteSymbol;
        String musicString = "";
        String noteDuration = "i";
        String chordDuration = "q";
        float barSize = 0;
        for (int i = 0; i < notesAmount; i += 2) {
            Note chord[] = new Note[3];
            for (int j = 0; j < 3; j++) {
                chord[j] = new Note(chords[i / 2][j]);
            }
            Chord currentChord = Chord.fromNotes(chord);
            String chordSymbol = currentChord.toString();
            musicString += chordSymbol + chordDuration + "+";
            currentNoteSymbol = (new Note(notes[i])).toString();
            musicString += currentNoteSymbol + noteDuration + "_";
            currentNoteSymbol = (new Note(notes[i + 1])).toString();
            musicString += currentNoteSymbol + noteDuration + " ";
            barSize += 1;
            if (barSize == timeSignature) {
                musicString += barEnd;
                barSize = 0;
            }
        }
        System.out.println(musicString);
        createMidiFile(musicString, tempo);
    }
}
