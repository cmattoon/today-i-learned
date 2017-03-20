public class TVOff implements Command {
    ElectronicDevice theDevice;

    public TVOff(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    public void execute() {
        theDevice.off();
    }

    public void undo() {
        theDevice.on();
    }
}