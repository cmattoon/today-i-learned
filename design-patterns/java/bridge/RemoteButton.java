public abstract class RemoteButton {
    private EntertainmentDevice theDevice;

    public RemoteButton(EntertainmentDevice newDevice) {
        theDevice = newDevice;
    }

    public void btn1Pressed() {
        theDevice.btn1Pressed();
    }

    public void btn2Pressed() {
        theDevice.btn2Pressed();
    }

    public void btn3Pressed() {
        theDevice.btn3Pressed();
    }

    public void btn4Pressed() {
        theDevice.btn4Pressed();
    }

    public abstract void btn5Pressed();
}