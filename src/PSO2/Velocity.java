package PSO2;

public class Velocity extends Coordinates {
    Velocity() {
        super();
        for (int i = 0; i < DIMENSION; i++) {
            coordinates[i] = (int) (Math.random() * 2 * (maxValues - minValues)) - minValues;
        }
    }
}