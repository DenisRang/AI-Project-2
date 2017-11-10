public class MyChord {
    int[] notes = new int[3];

    MyChord(int note1, int note2, int note3) {
        notes[0] = note1;
        notes[1] = note2;
        notes[2] = note3;
    }

    MyChord sum(MyChord b) {
        for (int i = 0; i < 3; i++)
            notes[i] += b.notes[i];
        return this;
    }

    MyChord dif(MyChord b) {
        for (int i = 0; i < 3; i++)
            notes[i] -= b.notes[i];
        return this;
    }

    MyChord mul(double d) {
        for (int i = 0; i < 3; i++)
            notes[i] = (int) (this.notes[i] * d);
        return this;
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
}