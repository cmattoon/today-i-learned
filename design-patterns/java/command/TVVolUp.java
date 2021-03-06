class TVVolUp implements Command {

    ElectronicDevice theDevice;

    public TVVolUp(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    public void execute() {
        theDevice.volumeUp();
    }

    public void undo() {
        theDevice.volumeDown();
    }
}