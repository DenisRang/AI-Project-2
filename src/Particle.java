import javafx.geometry.Pos;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class Particle {
    Position currentPos;
    Velocity velocity;
    Position localBestPos;

    Particle() {
        currentPos = new Position();
        velocity = new Velocity();
        localBestPos = currentPos;
    }

    void nextIteration(int i) {
        Coordinates velocityPart1, velocityPart2, velocityPart3;
        int f = Swarm.f(currentPos);
        if (f < Swarm.f(localBestPos)) {
            localBestPos = currentPos;
            if (f < Swarm.f(Swarm.globalBestPos))
                Swarm.globalBestPos = currentPos;
        }
        double inertia = (i * 0.07 < 0.5) ? 0.9 - i * 0.07 : 0.4;

        velocityPart1 = velocity.mul(inertia);
        velocityPart2 = localBestPos.dif(currentPos).mul(Math.random()).mul(Swarm.c1);
        velocityPart3 = Swarm.globalBestPos.dif(currentPos).mul(Math.random()).mul(Swarm.c2);
        velocity = (Velocity) velocityPart1.sum(velocityPart2).sum(velocityPart3);

        currentPos.nextPosition(velocity);
    }
}


