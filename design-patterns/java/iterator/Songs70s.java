/**
 * 70's DJ sends info in Arrays
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Songs70s implements SongIterator {
    ArrayList<SongInfo> bestSongs;

    public Songs70s() {
        bestSongs = new ArrayList<SongInfo>();

        addSong("Imagine", "John Lennon", 1971);
        addSong("American Pie", "Don McLean", 1971);
        addSong("I Will Survive", "Gloria Gaynor", 1979);
    }

    public void addSong(String name, String band, int year) {
        bestSongs.add(new SongInfo(name, band, year));
    }

    /* Bad!
    public ArrayList<SongInfo> getBestSongs() {
        return bestSongs;
    }*/

    public Iterator createIterator() {
        return bestSongs.iterator();
    }
}