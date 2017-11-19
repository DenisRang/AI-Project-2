package PSO2;

public class Position extends Coordinates {
    Position() {
        super();
        for (int i = 0; i < DIMENSION; i++) {
            coordinates[i] = (int) (Math.random() * (maxValues - minValues)) + minValues;
        }

    }
}