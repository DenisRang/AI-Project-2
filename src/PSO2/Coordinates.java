package PSO2;

public class Coordinates {
    public final static int DIMENSION = 32;
    public final static int minValues = 0;   //limits of
    public final static int maxValues = 127;   //coordinates
    protected int[] coordinates;

    public Coordinates() {
        coordinates = new int[DIMENSION];
    }

    Coordinates sum(Coordinates b) {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++) {
            temp.coordinates[i] = this.coordinates[i]+b.coordinates[i];
        }
        return temp;
    }

    public Coordinates dif(Coordinates b) {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++)
            temp.coordinates[i] = this.coordinates[i] - b.coordinates[i];
        return temp;
    }

    public Coordinates mul(double d) {
        Coordinates temp = new Coordinates();
        for (int i = 0; i < DIMENSION; i++)
            temp.coordinates[i] = (int)(this.coordinates[i]*d);
        return temp;
    }

    public void nextPosition(Coordinates velocity){
        for (int i = 0; i < DIMENSION; i++) {
            this.coordinates[i] = this.coordinates[i]+velocity.coordinates[i];
        }
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < DIMENSION; i++)
            s += coordinates[i] + "  |  ";
        return s;
    }

}





