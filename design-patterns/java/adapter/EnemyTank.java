import java.util.Random;

public class EnemyTank implements EnemyAttacker {

    Random generator = new Random();

    public void fireWeapon() {
        int damage = generator.nextInt(10) + 1;
        System.out.println("Pew");
    }

    public void driveForward() {
        System.out.println("Vroom!");
    }

    public void assignDriver(String driver) {
        System.out.println("Driver " + driver + " is driving the tank");
    }
}