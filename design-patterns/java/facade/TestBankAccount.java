public class TestBankAccount {
    public static void main(String[] args) {
        BankAccountFacade bank = new BankAccountFacade(987654321, 1234);
        bank.withdraw(50.00);
        bank.withdraw(100000000.00);
        bank.deposit(100.00);
    }
}