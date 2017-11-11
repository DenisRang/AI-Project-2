import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

public class Main {
    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {
        Swarm swarm=new Swarm(10000);
        int i=0;
        while (Swarm.f(Swarm.globalBestPos)!=0){
            swarm.nextIteration(++i);
            System.out.println(Swarm.globalBestPos);
            System.out.println(Swarm.f(Swarm.globalBestPos));
           // Thread.sleep(1000);
        }






//        Player player = new Player();
//
//        ChordProgression cp = new ChordProgression("I IV V");
//        Note notess[] = new Note[3];
//        notess[0]= new Note(15);
//        notess[1] = new Note(77);
//        notess[2] = new Note(81);
//        Chord chordd = Chord.fromNotes(notess);
//        player.play(chordd);player.play(chordd);player.play(chordd);player.play(chordd);player.play(chordd);player.play(chordd);
//        System.out.println(notess[0].getToneString());
//
//        Chord[] chords = cp.setKey("c5").getChords();
//        for (Chord chord : chords) {
//            System.out.print("Chord "+chordd+" has these notes: ");
//            Note[] notes = chord.getNotes();
//            for (Note note : notes) {
//                System.out.print(note.getValue()+" ");
//            }
//            System.out.println();
//        }
//        player.play(cp);
//
//        chords = cp.setKey("G5").getChords();
//        for (Chord chord : chords) {
//            System.out.print("Chord "+chord+" has these notes: ");
//            Note[] notes = chord.getNotes();
//            for (Note note : notes) {
//                System.out.print(note.getValue()+" ");
//            }
//            System.out.println();
//        }
//        player.play(cp);
    }
}
