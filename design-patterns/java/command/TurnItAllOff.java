import java.util.List;

public class TurnItAllOff implements Command {
    List<ElectronicDevice> theDevices;

    public TurnItAllOff(List<ElectronicDevice> newDevices) {
        theDevices = newDevices;
    }

    public void execute() {
        for (ElectronicDevice dev : theDevices) {
            dev.off();
        }
    }
}