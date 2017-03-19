import java.util.ArrayList;
import java.util.List;

public class PlayWithRemote {

    public static void main(String[] args) {
        ElectronicDevice device = TVRemote.getDevice(); // Return Television

        TVOn onCommand = new TVOn(device);
        TVOff offCommand = new TVOff(device);

        DeviceButton btnOn = new DeviceButton(onCommand);
        DeviceButton btnOff = new DeviceButton(offCommand);

        btnOn.press();
        btnOff.press();
    }
}