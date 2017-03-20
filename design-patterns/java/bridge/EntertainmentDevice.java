/**
 * Might be an interface instead
 */
abstract class EntertainmentDevice {
    public int deviceState;
    public int minSetting;
    public int maxSetting;
    public int volume = 0;

    public abstract void btn1Pressed();
    public abstract void btn2Pressed();

    public void deviceFeedback() {
        if (deviceState > maxSetting || deviceState < minSetting) {
            deviceState = minSetting;
        }

        System.out.println("deviceState: " + deviceState);
    }

    public void btn3Pressed() {
        volume++;
    }
    public void btn4Pressed() {
        volume++;
    }
}