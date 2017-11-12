
public class Coordinates {
    final static int DIMENSION = 6;
    final static int minValues = 48;   //limits of
    final static int maxValues = 72;   //coordinates
    protected MyChord[] coordinates;

    public Coordinates() {
        coordinates = new MyChord[DIMENSION];
    }

    Coordinates sum(Coordinates b) throws CloneNotSupportedException {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++) {
            temp.coordinates[i] = this.coordinates[i].sum(b.coordinates[i]);
        }
        return temp;
    }

    Coordinates dif(Coordinates b) throws CloneNotSupportedException {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++)
            temp.coordinates[i] = this.coordinates[i].dif(b.coordinates[i]);
        return temp;
    }

    Coordinates mul(double d) throws CloneNotSupportedException {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++)
            temp.coordinates[i] = this.coordinates[i].mul(d);
        return temp;
    }

    void nextPosition(Coordinates velocity) throws CloneNotSupportedException {
        for (int i = 0; i < DIMENSION; i++) {
            this.coordinates[i] = this.coordinates[i].sum(velocity.coordinates[i]);
        }
    }

    public MyChord[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < DIMENSION; i++)
            s += coordinates[i].toString() + "  |  ";
        return s;
    }

}


class Position extends Coordinates {
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


class Velocity extends Coordinates {
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