public class Song extends SongComponent {
    String songName;
    String bandName;
    int releaseYear;

    public Song(String name, String band, int year) {
        songName = name;
        bandName = band;
        releaseYear = year;
    }

    public String getSongName() { return songName; }
    public String getBandName() { return bandName; }
    public int getReleaseYear() { return releaseYear; }

    public void displaySongInfo() {
        System.out.println("    " + getSongName() +
                           " (" + getBandName() + ", " +
                           getReleaseYear() + ")");

    }
}