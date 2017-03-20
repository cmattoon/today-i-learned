/**
 * Concrete Implementor
 */
public class TVDevice extends EntertainmentDevice {

    public TVDevice(int theState, int newMinSetting, int newMaxSetting) {
        deviceState = theState;
        minSetting = newMinSetting;
        maxSetting = newMaxSetting;
    }

    public void btn1Pressed() {
        System.out.println("Channel+");
        deviceState++;
    }

    public void btn2Pressed() {
        System.out.println("Channel-");
        deviceState--;
    }
}