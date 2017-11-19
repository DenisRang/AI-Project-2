package PSO1;

import PSO1.Coordinates;
import PSO1.Position;
import PSO1.Velocity;

public class Particle {
    public Position currentPos;
    public Coordinates velocity;
    public Position localBestPos;
    public int f;

    public Particle() {
        currentPos = new Position();
        velocity = new Velocity();
    }

    public void nextIteration(int i) throws CloneNotSupportedException {
        Coordinates velocityPart1, velocityPart2, velocityPart3;
        f = Swarm.f(currentPos);
        int fl = Swarm.f(localBestPos);
        if (f < fl) {
            localBestPos = deepClone(currentPos);
            int fg = Swarm.f(Swarm.globalBestPos);
            if (f < fg) {
                Swarm.globalBestPos = deepClone(currentPos);
            }
        }
        double inertia = (i * 0.05 < 0.4) ? 0.9 - i * 0.05 : 0.4;

        velocityPart1 = velocity.mul(inertia);
        velocityPart2 = localBestPos.dif(currentPos).mul(Math.random()).mul(Swarm.c1);
        velocityPart3 = Swarm.globalBestPos.dif(currentPos).mul(Math.random()).mul(Swarm.c2);
        velocity = velocityPart1.sum(velocityPart2).sum(velocityPart3);

        currentPos.nextPosition(velocity);
    }

    @Override
    public String toString() {
        return currentPos.toString();
    }

    public Position deepClone(Position object) {
        Position clone = new Position();
        for (int i = 0; i < Coordinates.DIMENSION; i++) {
            for (int j = 0; j < 3; j++) {
                clone.getCoordinates()[i].setNote(j, object.getCoordinates()[i].getNote(j));
            }
        }
        return clone;
    }
}


