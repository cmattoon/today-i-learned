public class SecurityCodeCheck {
    private int code = 1234;

    public int getCode() { return code; }

    public boolean checkCode(int input) {
        return (input == code);
    }
}