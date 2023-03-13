public class Account {
    double AccountNumber;
    double AccountBalance;
    public Account(double accountNumber, double accountBalance) {
        AccountNumber = accountNumber;
        AccountBalance = accountBalance;
    }
    public double getAccountNumber() {
        return AccountNumber;
    }
    public void setAccountNumber(double accountNumber) {
        AccountNumber = accountNumber;
    }
    public double getAccountBalance() {
        return AccountBalance;
    }
    public void setAccountBalance(double accountBalance) {
        AccountBalance = accountBalance;
    }
}
