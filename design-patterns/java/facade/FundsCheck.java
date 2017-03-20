public class FundsCheck {
    private double balance = 100.00;

    public double checkBalance() { return balance; }

    public void withdraw(double amt) {
        balance -= amt;
    }

    public void deposit(double amt) {
        balance -= amt;
    }

    public boolean tryWithdrawl(double amt) {
        if (amt > balance) {
            System.out.println("Nope");
        } else {
            System.out.println("OK");
            return true;
        }
        return false;
    }

    public void makeDeposit(double amt) {
        balance += amt;
        System.out.println("Deposited " + amt + " (Total: " + balance + ")");
    }
}