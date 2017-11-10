import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

import java.util.ArrayList;
import java.util.Random;

public class Swarm {
    final int size;   //amount of particles in the swarm
    static double c1 = 1.5;      //coefficient of an influence of the local best position to velocity changing
    static double c2 = 2;      //coefficient of an influence of the global best position to velocity changing
    static Position globalBestPos = null;
    Particle[] particles;

    public Swarm(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            particles[i] = new Particle();
        }
    }

    void nextIteration(int i) {
        for (Particle p : particles) {
            p.nextIteration(i);
        }
    }

    static int f(Position pos) {
        //Notes: C, D, E, F, G, A, B
        int[] values = {0,0,0,0,0,0,0};
        int[] amounts = {0,0,0,0,0,0,0};
        int[] chords = {0,0,0,0,0,0,0};
        int i;
        for (MyChord myChord : pos.getCoordinates()) {
            i=index(myChord);
            if(i!=-1){
                values[i]+=fitnessFunctForChord(myChord);
                amounts[i]++;
            }
            else values[i]=97;
        }
    }

    static int fitnessFunctForChord(MyChord myChord) {
        return Math.abs(myChord.getNote(1) - myChord.getNote(0) - 3) + Math.abs(myChord.getNote(2) - myChord.getNote(1) - 3);
    }

    static int index(MyChord myChord) {
        switch (new Note(myChord.getNote(0)).getToneString()){
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

}
