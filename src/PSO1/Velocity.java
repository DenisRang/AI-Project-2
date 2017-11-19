package PSO1;

public class Velocity extends Coordinates {
    Velocity() {
        super();
        for (int i = 0; i < DIMENSION; i++) {
            coordinates[i] = new MyChord(randomNote(), randomNote(), randomNote());
        }
    }

    int randomNote() {
        return (int) (Math.random() * 2 * (maxValues - minValues)) - minValues;
    }


}