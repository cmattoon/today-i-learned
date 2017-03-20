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

        Television theTelevision = new Television();
        Radio theRadio = new Radio();

        List<ElectronicDevice> allDevices = new ArrayList<ElectronicDevice>();

        allDevices.add(theTelevision);
        allDevices.add(theRadio);

        TurnItAllOff killEverything = new TurnItAllOff(allDevices);

        DeviceButton kill9 = new DeviceButton(killEverything);
        kill9.press();
    }
}