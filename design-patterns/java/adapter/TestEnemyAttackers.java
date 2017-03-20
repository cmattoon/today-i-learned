public class TestEnemyAttackers {
    public static void main(String[] args) {
        EnemyTank rxTank = new EnemyTank();
        EnemyRobot fred = new EnemyRobot();
        EnemyAttacker robotAdapter = new EnemyRobotAdapter(fred);

        System.out.println("The Robot.........");
        fred.react2Human("Bob");
        fred.walkForward();
        fred.smashThings();

        System.out.println("Tank..............");
        rxTank.assignDriver("Frank");
        rxTank.driveForward();
        rxTank.fireWeapon();

        System.out.println("Adapter...........");
        robotAdapter.assignDriver("Mark");
        robotAdapter.driveForward();
        robotAdapter.fireWeapon();
    }
}