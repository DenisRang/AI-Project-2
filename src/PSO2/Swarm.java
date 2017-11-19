package PSO2;

import org.jfugue.theory.Chord;

public class Swarm {
    public final int size;   //amount of particles in the swarm
    public static double c1 = 1;      //coefficient of an influence of the local best position to velocity changing
    public static double c2 = 2;      //coefficient of an influence of the global best position to velocity changing
    public static Position globalBestPos = null;
    public Particle[] particles;
    public static boolean[][] possibleValues;   //possible values of even notes   [0..15][300]
    public static int[] startNotes; //starting values of even notes   [0..15]

    public Swarm(int size, int[][] chords) {
        this.size = size;
        particles = new Particle[size];
        for (int i = 0; i < size; i++) {
            particles[i] = new Particle();
        }
        possibleValues = new boolean[Coordinates.DIMENSION / 2][301];  //possible values note for some chord
        startNotes = new int[Coordinates.DIMENSION / 2];
        for (int i = 0; i < Coordinates.DIMENSION / 2; i++) {
            startNotes[i] = chords[i][0] + 12;
            for (int j = chords[i][0] + 12; j < 96; j += 12) possibleValues[i][j] = true;
            for (int j = chords[i][1] + 12; j < 96; j += 12) possibleValues[i][j] = true;
            for (int j = chords[i][2] + 12; j < 96; j += 12) possibleValues[i][j] = true;
        }
    }

    public void nextIteration(int i) {
        for (Particle p : particles) {
            p.nextIteration(i);
        }
    }

    public static int f(Position pos) {
        if (pos == null) return 10000;
        int result = 0, max, min, a;
        int[] coordinates = pos.getCoordinates().clone();   //coordinates of 'pos' position     [0..31]
        int[] perfectNotes = pos.getCoordinates().clone();     //coordinates of suitable 'pos' position     [0..31]

        //process even notes
        if (perfectNotes[0] < startNotes[0]) perfectNotes[0] = startNotes[0];
        if (perfectNotes[0] > 96) perfectNotes[0] = 96;
        a = -1;
        while (!possibleValues[0][perfectNotes[0]]) {
            if (perfectNotes[0] < startNotes[0] || perfectNotes[0] > 96) {
                perfectNotes[0] = coordinates[0];
                a *= -1;
            }
            perfectNotes[0] += a;
        }
        for (int i = 1; i < Coordinates.DIMENSION / 2; i++) {
            if (perfectNotes[i * 2] < startNotes[i]) perfectNotes[i * 2] = startNotes[i];
            if (perfectNotes[i * 2] > 96) perfectNotes[i * 2] = 96;
            a = (perfectNotes[i * 2] > perfectNotes[i * 2 - 2]) ? -1 : 1;
            while ((!possibleValues[i][perfectNotes[i * 2]]) || (Math.abs(perfectNotes[i * 2] - perfectNotes[i * 2 - 2]) > 24)) {
                if (perfectNotes[i * 2] < startNotes[i] || perfectNotes[i * 2] > 96) {
                    perfectNotes[i * 2] = coordinates[i * 2];
                    a *= -1;
                }
                perfectNotes[i * 2] += a;
            }

        }

        //process odd notes
        for (int i = 0; i < (Coordinates.DIMENSION / 2) - 1; i++) {
            max = (perfectNotes[i * 2] > perfectNotes[i * 2 + 2]) ? perfectNotes[i * 2] : perfectNotes[i * 2 + 2];
            min = (perfectNotes[i * 2] < perfectNotes[i * 2 + 2]) ? perfectNotes[i * 2] : perfectNotes[i * 2 + 2];
            if (perfectNotes[i * 2 + 1] < min) perfectNotes[i * 2 + 1] = min;
            if (perfectNotes[i * 2 + 1] > max) perfectNotes[i * 2 + 1] = max;
            a = ((perfectNotes[i * 2 + 1] - min) > 12) ? -1 : 1;
            while (!(Math.abs(max - perfectNotes[i * 2 + 1]) < 13 && Math.abs(min - perfectNotes[i * 2 + 1]) < 13))
                perfectNotes[i * 2 + 1] += a;
        }
        if (perfectNotes[31] < perfectNotes[30] - 12) perfectNotes[31] = perfectNotes[30] - 12;
        if (perfectNotes[31] > perfectNotes[30] + 12) perfectNotes[31] = perfectNotes[30] + 12;

        for (int i = 0; i < Coordinates.DIMENSION; i++) {
            result += Math.abs(perfectNotes[i] - coordinates[i]);
        }
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++)
            s += particles[i].toString() + "  \n  ";
        return s + "\n";
    }


}
