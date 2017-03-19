public class DeviceButton {
    Command theCommand;

    public DeviceButton(Command newCmd) {
        theCommand = newCmd;
    }

    public void press() {
        theCommand.execute();
    }
}