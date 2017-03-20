/**
 * Adapter
 */
public class EnemyRobotAdapter implements EnemyAttacker {
    EnemyRobot theRobot;

    public EnemyRobotAdapter(EnemyRobot newBot) {
        theRobot = newBot;
    }
    public void fireWeapon() {
        theRobot.smashThings();
    }
    public void driveForward() {
        theRobot.walkForward();
    }
    public void assignDriver(String driver) {
        theRobot.react2Human(driver);
    }
}