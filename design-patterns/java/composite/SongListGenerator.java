public class SongListGenerator {
    public static void main(String[] args) {
        SongComponent metal = new SongGroup("Metal", "");
        SongComponent punk = new SongGroup("Punk", "");
        SongComponent whatever = new SongGroup("Whatever", "");
        SongComponent heavyMetal = new SongGroup("Heavy Metal", "");

        SongComponent allSongs = new SongGroup("All Songs", "");

        metal.add(new Song("War Pigs", "Black Sabbath", 1970));

        punk.add(new Song("Anarchy in the U.K.", "Sex Pistols", 1977));
        punk.add(new Song("The Kids Aren't Alright", "The Offspring", 1998));

        whatever.add(new Song("Whatever", "Some Band", 2000));

        allSongs.add(metal);
        allSongs.add(punk);
        allSongs.add(whatever);

        DJ crazyMike = new DJ(allSongs);
        crazyMike.printSongList();
    }
}