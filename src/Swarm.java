import org.jfugue.theory.Note;

public class Swarm {
    final int size;   //amount of particles in the swarm
    static double c1 = 1.3;      //coefficient of an influence of the local best position to velocity changing
    static double c2 = 2.5;      //coefficient of an influence of the global best position to velocity changing
    static boolean isMinor = true;      //if you want major notes then you should set false
    static Position globalBestPos = null;
    Particle[] particles;
    static int rightNote;

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
        int[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    //in the end of end we will get fitness values of chord's starting note
        int[] amountsN = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    //amount of such chords which starts with that note
        int[] amountsC = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    //amount of such chords which starts with that note
        int[] chords = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    //fitness values of note if it would be starting note of tonic chord
        int i, min = 500, f;
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

    static int fitnessFunctForChord(MyChord myChord) {
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

    static int index(int note) {
        if (note < Coordinates.minValues) return -2;
        if (note > Coordinates.maxValues - 7) return -1;
        return note % 12;
//        switch (new Note(note).getToneString()) {
//            case "C":
//                return 0;
//            case "D":
//                return 1;
//            case "E":
//                return 2;
//            case "F":
//                return 3;
//            case "G":
//                return 4;
//            case "A":
//                return 5;
//            case "B":
//                return 6;
//            default:
//                return -1;
//        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++)
            s += particles[i].toString() + "  \n  ";
        return s + "\n";
    }


}
