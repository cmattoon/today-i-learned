/**
 * 80's DJ sends info as an Array
 */
import java.util.Arrays;
import java.util.Iterator;

public class Songs80s implements SongIterator {
    SongInfo[] bestSongs;
    int arrayValue = 0;

    public Songs80s() {
        bestSongs = new SongInfo[3];

        addSong("Roam", "B52s", 1989);
        addSong("Cruel Summer", "Bananarama", 1984);
        addSong("Head Over Heels", "Tears for Fears", 1985);
    }

    public void addSong(String name, String band, int year) {
        SongInfo info = new SongInfo(name, band, year);
        bestSongs[arrayValue] = info;
        arrayValue++;
    }
    /**
    public SongInfo[] getBestSongs() {
        return bestSongs;
        }*/

    public Iterator createIterator() {
        return Arrays.asList(bestSongs).iterator();
    }
}