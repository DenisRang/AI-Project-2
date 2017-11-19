package PSO1;

public class Coordinates {
    public final static int DIMENSION = 5;
    public final static int minValues = 0;   //limits of
    public final static int maxValues = 127;   //coordinates
    public  MyChord[] coordinates;

    public Coordinates() {
        coordinates = new MyChord[DIMENSION];
    }

    public Coordinates sum(Coordinates b) throws CloneNotSupportedException {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++) {
            temp.coordinates[i] = this.coordinates[i].sum(b.coordinates[i]);
        }
        return temp;
    }

    public Coordinates dif(Coordinates b) throws CloneNotSupportedException {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++)
            temp.coordinates[i] = this.coordinates[i].dif(b.coordinates[i]);
        return temp;
    }

    public Coordinates mul(double d) throws CloneNotSupportedException {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++)
            temp.coordinates[i] = this.coordinates[i].mul(d);
        return temp;
    }

    public void nextPosition(Coordinates velocity) throws CloneNotSupportedException {
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





