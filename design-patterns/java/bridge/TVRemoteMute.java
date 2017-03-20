/**
 * Refined Abstraction
 */
public class TVRemoteMute extends RemoteButton {
    public TVRemoteMute(EntertainmentDevice theDevice) {
        super(theDevice);
    }
    public void btn5Pressed() {
        System.out.println("TV Muted");
    }
}