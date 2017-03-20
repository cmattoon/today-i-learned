/**
 * 90's DJ sends info in Hashtable
 */
import java.util.Hashtable;
import java.util.Iterator;

public class Songs90s implements SongIterator {
    Hashtable<Integer, SongInfo> bestSongs = new Hashtable<Integer, SongInfo>();
    int hashKey = 0;

    public Songs90s() {
        addSong("Losing My Religion", "REM", 1993);
        addSong("Creep", "Radiohead", 1993);
        addSong("Walk on the Ocean", "Toad the Wet Sprocket", 1991);
    }

    public void addSong(String name, String band, int year) {
        bestSongs.put(hashKey, new SongInfo(name, band, year));
        hashKey++;
    }
    /**
    public Hashtable<Integer, SongInfo> getBestSongs() {
        return bestSongs;
        }*/
    public Iterator createIterator() {
        return bestSongs.values().iterator();
    }
}