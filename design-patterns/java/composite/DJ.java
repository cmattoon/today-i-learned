public class DJ {
    SongComponent songList;

    public DJ(SongComponent newSongList) {
        songList = newSongList;
    }

    public void printSongList() {
        songList.displaySongInfo();
    }
}