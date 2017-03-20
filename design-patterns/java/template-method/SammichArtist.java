public class SammichArtist {

    public static void main(String[] args) {
        Hoagie myHoagie = new ItalianHoagie();
        myHoagie.sudoMakeSammich();

        Hoagie trash = new VeganHoagie();
        trash.sudoMakeSammich();
    }

}