/**
 * Adaptee
 */
import java.util.Random;

public class EnemyRobot {
    Random generator = new Random();

    public void smashThings() {
        int dmg = generator.nextInt(10) + 1;
        System.out.println("Enemy Robot causes " + dmg + " damage");
    }

    public void walkForward() {
        System.out.println("Robot walks instead of drives");
    }

    public void react2Human(String driverName) {
        System.out.println("Enemy robot steps on driver " + driverName);
    }
}