public class TVOn implements Command {
    ElectronicDevice theDevice;

    public TVOn(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    public void execute() {
        theDevice.on();
    }
}