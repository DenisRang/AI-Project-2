import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

public class Coordinates {
    final static int DIMENSION = 16;
    final static int minValues = 48;   //limits of
    final static int maxValues = 96;   //coordinates
    protected MyChord[] coordinates;

    public Coordinates() {
        coordinates = new MyChord[DIMENSION];
    }

    Coordinates sum(Coordinates b) {
        for (int i = 0; i < DIMENSION; i++) {
            this.coordinates[i].sum(b.coordinates[i]);
        }
        return this;
    }

    Coordinates dif(Coordinates b) {
        for (int i = 0; i < DIMENSION; i++)
            this.coordinates[i].dif(b.coordinates[i]);
        return this;
    }

    Coordinates mul(double d) {
        for (int i = 0; i < DIMENSION; i++)
            this.coordinates[i].mul(d);
        return this;
    }

    void nextPosition(Coordinates velocity) {
        sum(velocity);
    }

    public MyChord[] getCoordinates() {
        return coordinates;
    }
}


class Position extends Coordinates {
    Position() {
        for (int i = 0; i < DIMENSION; i++) {
            this.coordinates[i].setNote(0, randomNote());
            this.coordinates[i].setNote(1, randomNote());
            this.coordinates[i].setNote(2, randomNote());
        }

    }

    int randomNote() {
        return (int) (Math.random() * (maxValues - minValues)) + minValues;
    }
}


class Velocity extends Coordinates {
    Velocity() {
        for (int i = 0; i < DIMENSION; i++) {
            this.coordinates[i].setNote(0, randomNote());
            this.coordinates[i].setNote(1, randomNote());
            this.coordinates[i].setNote(2, randomNote());
        }
    }

    int randomNote() {
        return (int) (Math.random() * 2 * (maxValues - minValues)) - minValues;
    }
}