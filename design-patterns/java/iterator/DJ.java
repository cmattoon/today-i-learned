import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

class DJ {
    Songs70s songs70;
    Songs80s songs80;
    Songs90s songs90;

    public DJ(Songs70s s7, Songs80s s8, Songs90s s9) {
        songs70 = s7;
        songs80 = s8;
        songs90 = s9;
    }

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
}