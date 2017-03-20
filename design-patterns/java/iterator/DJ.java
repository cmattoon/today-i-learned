import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

class DJ {
    Songs70s songs70;
    Songs80s songs80;
    Songs90s songs90;

    SongIterator iter70;
    SongIterator iter80;
    SongIterator iter90;

    public DJ(SongIterator s70, SongIterator s80, SongIterator s90) {
        iter70 = s70;
        iter80 = s80;
        iter90 = s90;
    }

    /**
     * Good!
     */
    public void playSongs() {
        Iterator s70 = iter70.createIterator();
        Iterator s80 = iter80.createIterator();
        Iterator s90 = iter90.createIterator();

        System.out.println("Songs of the 70s:");
        showSongs(s70);

        System.out.println("Songs of the 80s:");
        showSongs(s80);

        System.out.println("Songs of the 90s:");
        showSongs(s90);
    }

    public void showSongs(Iterator iterator) {
        while (iterator.hasNext()) {
            SongInfo bestSongs = (SongInfo) iterator.next();

            System.out.println("    " + bestSongs.getSongName() + " " +
                               bestSongs.getBandName() + " (" +
                               bestSongs.getYearReleased() + ")");

        }
        System.out.println("");
    }

    /** Bad!

    public void playSongs() {
        ArrayList al70 = songs70.getBestSongs();
        System.out.println("Songs of the 70's\n");

        for (int i=0; i < al70.size(); i++) {
            SongInfo bestSongs = (SongInfo) al70.get(i);
            System.out.println(bestSongs.getSongName() + " " +
                               bestSongs.getBandName() + " (" +
                               bestSongs.getYearReleased() + ")");
        }

        // 80's songs....
        SongInfo[] al80 = songs80.getBestSongs();
        System.out.println("Songs of the 80's\n");

        for (int i=0; i < al80.length; i++) {
            SongInfo bestSongs = (SongInfo) al80[i];

            System.out.println(bestSongs.getSongName() + " " +
                               bestSongs.getBandName() + " (" +
                               bestSongs.getYearReleased() + ")");
        }

        // 90's songs....
        Hashtable<Integer, SongInfo> ht90 = songs90.getBestSongs();
        System.out.println("Songs of the 90's\n");

        for (Enumeration<Integer> e = ht90.keys(); e.hasMoreElements();) {
            SongInfo bestSongs = (SongInfo) ht90.get(e.nextElement());

            System.out.println(bestSongs.getSongName() + " " +
                               bestSongs.getBandName() + " (" +
                               bestSongs.getYearReleased() + ")");
        }
    }
    */

}