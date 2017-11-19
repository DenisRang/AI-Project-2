package PSO1;

import PSO1.Coordinates;
import PSO1.MyChord;
import PSO1.Particle;
import PSO1.Position;

public class Swarm {
    public final int size;   //amount of particles in the swarm
    public static double c1 = 1;      //coefficient of an influence of the local best position to velocity changing
    public static double c2 = 2;      //coefficient of an influence of the global best position to velocity changing
    public static boolean isMinor = true;      //if you want major notes then you should set false
    public static Position globalBestPos = null;
    public Particle[] particles;
    public static int rightNote;

    public Swarm(int size) {
        this.size = size;
        particles = new Particle[size];
        for (int i = 0; i < size; i++) {
            particles[i] = new Particle();
        }
    }

    public void nextIteration(int i) throws CloneNotSupportedException {
        for (Particle p : particles) {
            p.nextIteration(i);
        }
    }

    public static int f(Position pos) {
        if (pos == null) return 10000;
        //Notes: C, D, E, F, G, A, B
        int[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    //in the end of end we will get fitness values of chord's starting note
        int[] amountsN = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    //amount of such chords which starts with that note
        int[] amountsC = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    //amount of such chords which starts with that note
        int[] chords = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    //fitness values of note if it would be starting note of tonic chord
        int i, min = 5000, f;
        for (MyChord myChord : pos.getCoordinates()) {
            f = fitnessFunctForChord(myChord);
            i = index(rightNote);
            values[i] += f;
            amountsN[i]++;
        }
        for (i = 0; i < 12; i++) {
            chords[i] = values[i] + values[(i + 3) % 12] + values[(i + 4) % 12];
            amountsC[i] = amountsN[i] + amountsN[(i + 3) % 12] + amountsN[(i + 4) % 12];
            chords[i] += 70 * (Coordinates.DIMENSION - amountsC[i]);
            if (min > chords[i]) min = chords[i];
        }
        return min;
    }

    public static int fitnessFunctForChord(MyChord myChord) {
        int minorOrMajor = (isMinor) ? 3 : 4;
        int i = index(myChord.getNote(0));
        rightNote = myChord.getNote(0);
        while (i < 0) {
            if (i == -1) rightNote -= 1;
            else rightNote += 1;
            i = index(rightNote);
        }
        return Math.abs(myChord.getNote(0) - rightNote) + Math.abs(myChord.getNote(1) - myChord.getNote(0) - minorOrMajor) + Math.abs(myChord.getNote(2) - myChord.getNote(0) - 7);
    }

    public static int index(int note) {
        if (note < 48) return -2;
        if (note > 72) return -1;
        return note % 12;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++)
            s += particles[i].toString() + "  \n  ";
        return s + "\n";
    }


}
