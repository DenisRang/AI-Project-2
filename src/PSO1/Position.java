package PSO1;

public class Position extends Coordinates {
    Position() {
        super();
        for (int i = 0; i < DIMENSION; i++) {
            coordinates[i] = new MyChord(randomNote(), randomNote(), randomNote());
        }

    }

    int randomNote() {
        return (int) (Math.random() * (maxValues - minValues)) + minValues;
    }

}