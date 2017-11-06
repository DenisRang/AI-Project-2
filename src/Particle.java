import javafx.geometry.Pos;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class Particle {
    Position currentPos;
    Position velocity;
    Position localBestPos;

    Particle(Position currentPos, Position velocity) {
        this.currentPos = currentPos;
        this.velocity = velocity;
        localBestPos = currentPos;
    }

    void nextIteration(int i) {
        int f = Swarm.f(currentPos);
        if (f < Swarm.f(localBestPos)) {
            localBestPos = currentPos;
            if (f < Swarm.f(Swarm.globalBestPos))
                Swarm.globalBestPos = currentPos;
        }
        double inertia=(i*0.07<0.5)?0.9-i*0.07:0.4;
        //velocity=inertia*velocity + Swarm.c1*Math.random()*(localBestPos-currentPos)

    }

}

class Position {
    //coordinates
    private int t;  //tonic
    private int s;  //subdominant
    private int d;  //dominant

    public Position(int t, int s, int d) {
        this.t = t;
        this.s = s;
        this.d = d;
    }
    Position sum(Position a, Position b){
        return new Position(a.t+b.t, a.s+b.s, a.d+b.d);
    }
    Position dif(Position a, Position b){
        return new Position(a.t-b.t, a.s-b.s, a.d-b.d);
    }
    Position mul(double d, Position a){
        return new Position((int)(a.t*d), (int)(a.s*d), (int)(a.d*d));
    }

    void nextPosition(Position currentPos, Position velocity) {
        currentPos.d += velocity.d;
        currentPos.s += velocity.s;
        currentPos.t += velocity.t;
    }

    public int getD() {
        return d;
    }

    public int getS() {
        return s;
    }

    public int getT() {
        return t;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setS(int s) {
        this.s = s;
    }

    public void setT(int t) {
        this.t = t;
    }
}

