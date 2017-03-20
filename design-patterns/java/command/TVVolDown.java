class TVVolDown implements Command {
    ElectronicDevice theDevice;

    public TVVolDown(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    public void execute() {
        theDevice.volumeUp();
    }

    public void undo() {
        theDevice.volumeDown();
    }
}