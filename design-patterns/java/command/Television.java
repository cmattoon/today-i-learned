public class Television implements ElectronicDevice {

    private int power = 0;

    private int volume = 0;

    public void on() {
        power = 1;
        System.out.println("TV is ON");
    }

    public void off() {
        power = 0;
        System.out.println("TV is OFF");
    }

    public void volumeUp() {
        volume++;
        System.out.println("TV volume = " + volume);
    }

    public void volumeDown() {
        volume--;
        System.out.println("TV volume = " + volume);
    }
}