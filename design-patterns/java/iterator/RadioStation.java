public class RadioStation {
    public static void main(String[] args) {
        Songs70s s70 = new Songs70s();
        Songs80s s80 = new Songs80s();
        Songs90s s90 = new Songs90s();
        DJ djMike = new DJ(s70, s80, s90);
        djMike.playSongs();
    }
}