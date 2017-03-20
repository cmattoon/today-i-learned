/**
 * Refined Abstraction
 */
public class TVRemotePause extends RemoteButton {

    public TVRemotePause(EntertainmentDevice theDevice) {
        super(theDevice);
    }

    public void btn5Pressed() {
        System.out.println("TV Paused");
    }
}