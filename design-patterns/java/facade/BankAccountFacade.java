public class BankAccountFacade {
    private int accountNumber;
    private int securityCode;

    AccountNumberCheck acctCheck;
    SecurityCodeCheck codeCheck;
    FundsCheck fundCheck;

    WelcomeToBank welcome;

    public BankAccountFacade(int acct, int pin) {
        accountNumber = acct;
        securityCode = pin;
        welcome = new WelcomeToBank();
        acctCheck = new AccountNumberCheck();
        codeCheck = new SecurityCodeCheck();
        fundCheck = new FundsCheck();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean withdraw(double amount) {
        if (!acctCheck.accountActive(accountNumber)) {
            System.out.println("Bad account");
            return false;
        }

        if (!codeCheck.checkCode(securityCode)) {
            System.out.println("Bad PIN");
            return false;
        }

        if (fundCheck.tryWithdrawl(amount)) {
            fundCheck.withdraw(amount);
            System.out.println("Got " + amount);
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        fundCheck.deposit(amount);
        System.out.println("Deposited " + amount);
    }
}