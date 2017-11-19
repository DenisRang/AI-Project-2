package PSO2;

public class Particle {
    Position currentPos;
    Coordinates velocity;
    Position localBestPos;
    int f;

    Particle() {
        currentPos = new Position();
        velocity = new Velocity();
    }

    void nextIteration(int i) {
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
        double inertia = (i * 0.05 < 0.5) ? 0.9 - i * 0.05 : 0.4;

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
                clone.getCoordinates()[i]= object.getCoordinates()[i];
        }
        return clone;
    }
}


