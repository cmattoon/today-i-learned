public class AccountNumberCheck {
    private int validAccountNumber = 987654321;

    public int getAccountNumber() { return validAccountNumber; }

    public boolean accountActive(int acctNum) {
        return (acctNum == validAccountNumber);
    }
}