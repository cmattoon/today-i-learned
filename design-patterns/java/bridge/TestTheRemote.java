public class TestTheRemote {
    public static void main(String[] args) {

        RemoteButton theTV = new TVRemoteMute(new TVDevice(1, 1, 100));

        RemoteButton theTV2 = new TVRemotePause(new TVDevice(1, 1, 100));

        //RemoteButton theDVD = new DVDRemote(new DVDDevice(1, 1, 100));

        System.out.println("Test TV with Mute");
        theTV.btn5Pressed();

        System.out.println("Test TV with Pause");
        theTV2.btn5Pressed();
    }
}