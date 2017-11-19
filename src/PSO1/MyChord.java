package PSO1;

public class MyChord {
    int[] notes = new int[3];

    public MyChord(int note1, int note2, int note3) {
        notes[0] = note1;
        notes[1] = note2;
        notes[2] = note3;
    }

    public MyChord sum(MyChord b) {
        MyChord temp = new MyChord(this.notes[0] + b.notes[0], this.notes[1] + b.notes[1], this.notes[2] + b.notes[2]);
        return temp;
    }

    public MyChord dif(MyChord b){
        MyChord temp = new MyChord(this.notes[0] - b.notes[0], this.notes[1] - b.notes[1], this.notes[2] - b.notes[2]);
        return temp;
    }

    public MyChord mul(double d)  {
        MyChord temp = new MyChord((int) (this.notes[0] * d), (int) (this.notes[1] * d), (int) (this.notes[2] * d));
        return temp;
    }

    public void setNote(int i, int note) {
        notes[i] = note;
    }

    public int getNote(int i) {
        return notes[i];
    }

    public int[] getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 3; i++)
            s += notes[i] + ", ";
        return s + ";  ";
    }

}