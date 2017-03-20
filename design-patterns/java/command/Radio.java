public class Radio implements ElectronicDevice {

    private int power = 0;

    private int volume = 0;

    public void on() {
        power = 1;
        System.out.println("Radio is ON");
    }

    public void off() {
        power = 0;
        System.out.println("Radio is OFF");
    }

    public void volumeUp() {
        volume++;
        System.out.println("Radio volume = " + volume);
    }

    public void volumeDown() {
        volume--;
        System.out.println("Radio volume = " + volume);
    }
}