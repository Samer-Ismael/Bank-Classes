import java.util.ArrayList;
import java.util.Random;

public class Account {
    private ArrayList<Object> Accounts;
    private String AccountNumber = null;
    private double AccountBalance;
    private String accountName;
    int pinCod;
    public Account(String accountName, int pinCod) {
        this.accountName = accountName;
        this.pinCod = pinCod;
        this.Accounts = new ArrayList<>();
    }
    public String getAccountNumber() {
        return AccountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }
    public double getAccountBalance() {
        return AccountBalance;
    }
    public void setAccountBalance(double accountBalance) {
        AccountBalance = accountBalance;
    }
    public void deposit (double input){
        AccountBalance += input;
    }
    public void withdraw (double input){
        AccountBalance -= input;
    }
    public String newAccountNumber (){
        Random rand = new Random();
        String AccountNumber = rand.nextInt(1000, 9999) + "-" + rand.nextInt(1000,9999) + "-" + rand.nextInt(1000,9999) + "-" + rand.nextInt(1000,9999);
        return AccountNumber;
    }
    public Object addNewAccount (Object account){
        return Accounts.add(account);
    }
    public void manageAccounts (){

        for (int i = 0; i < Accounts.size(); i++) {
            System.out.println(Accounts.get(i));

        }
    }
}
