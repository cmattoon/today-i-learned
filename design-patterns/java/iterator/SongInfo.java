public class SongInfo {
    String songName;
    String bandName;
    int yearReleased;

    public SongInfo(String song, String band, int year) {
        songName = song;
        bandName = band;
        yearReleased = year;
    }

    public String getSongName() { return songName; }
    public String getBandName() { return bandName; }
    public int getYearReleased() { return yearReleased; }
}