import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

import java.util.Random;

public class Swarm {
    final int size;   //amount of particles in the swarm
    static int minValues = 48;   //limits of coordinates(tonic,subdominant and dominant) of particle
    static int maxValues = 96;   //of particle
    static double c1=1.5;      //coefficient of an influence of the local best position to velocity changing
    static double c2=2;      //coefficient of an influence of the global best position to velocity changing
    static Position globalBestPos = null;
    Particle[] particles;

    public Swarm(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            particles[i] = new Particle(randomCoordinate(), randomVelocity());
        }
    }

    void nextIteration(int i) {
        for (Particle p : particles) {
            p.nextIteration(i);
        }
    }

    static int f(Position pos){
        return 1;
    }

    Position randomCoordinate() {
        int t = (int) (Math.random() * (maxValues - minValues)) + minValues;
        int s = (int) (Math.random() * (maxValues - minValues)) + minValues;
        int d = (int) (Math.random() * (maxValues - minValues)) + minValues;
        return new Position(t, s, d);
    }

    Position randomVelocity() {
        int t = (int) (Math.random() * 2 * (maxValues - minValues)) - minValues;
        int s = (int) (Math.random() * 2 * (maxValues - minValues)) - minValues;
        int d = (int) (Math.random() * 2 * (maxValues - minValues)) - minValues;
        return new Position(t, s, d);
    }
}
