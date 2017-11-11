import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

import java.util.ArrayList;
import java.util.Random;

public class Swarm {
    final int size;   //amount of particles in the swarm
    static double c1 = 2.5;      //coefficient of an influence of the local best position to velocity changing
    static double c2 = 1.3;      //coefficient of an influence of the global best position to velocity changing
    static Position globalBestPos = null;
    Particle[] particles;

    public Swarm(int size) {
        this.size = size;
        particles = new Particle[size];
        for (int i = 0; i < size; i++) {
            particles[i] = new Particle();
        }
    }

    void nextIteration(int i) throws CloneNotSupportedException {
        for (Particle p : particles) {
            p.nextIteration(i);
        }
    }

    static int f(Position pos) {
        if (pos == null) return 1000;
        //Notes: C, D, E, F, G, A, B
        int[] values = {0, 0, 0, 0, 0, 0, 0};    //in the end of end we will get fitness values of chord's starting note
        int[] amounts = {0, 0, 0, 0, 0, 0, 0};    //amount of such chords which starts with that note
        int[] chords = {0, 0, 0, 0, 0, 0, 0};    //fitness values of note if it would be starting note of tonic chord
        int i, min = 500;
        for (MyChord myChord : pos.getCoordinates()) {
            i = index(myChord);
            if (i > -1) {
                values[i] += fitnessFunctForChord(myChord);
                amounts[i]++;
            }
        }
        for (i = 0; i < 7; i++) {
            if (amounts[i] != 0) {
                values[i] /= (amounts[i] * amounts[i]);
            } else values[i] = 97;
        }
        for (i = 0; i < 7; i++) {
            chords[i] = values[i] + values[(i + 3) % 7] + values[(i + 4) % 7];
            if (min > chords[i]) min = chords[i];
        }
        return min;
    }

    static int fitnessFunctForChord(MyChord myChord) {
        return Math.abs(myChord.getNote(1) - myChord.getNote(0) - 3) + Math.abs(myChord.getNote(2) - myChord.getNote(1) - 3);
    }

    static int index(MyChord myChord) {
        if (myChord.getNote(0) < Coordinates.minValues || myChord.getNote(0) > Coordinates.maxValues) return -2;
        switch (new Note(myChord.getNote(0)).getToneString()) {
            case "C":
                return 0;
            case "D":
                return 1;
            case "E":
                return 2;
            case "F":
                return 3;
            case "G":
                return 4;
            case "A":
                return 5;
            case "B":
                return 6;
            default:
                return -1;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++)
            s += particles[i].toString() + "  \n  ";
        return s + "\n";
    }


}
