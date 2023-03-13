import java.util.Scanner;
public class Menu {


    public void Welcome (){
        System.out.println("Welcome to the bank app");
        System.out.println("------------------");
        System.out.println("Create new account");
        CreateNewAccount();
        manageList();

    }
    public void CreateNewAccount (){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter account name: ");
        String input1 = scan.next();
        System.out.println("Enter your PinCod 4 digits");
        int input2 = scan.nextInt();

        Account account = new Account(input1, input2);

        System.out.print("Your new account number: ");
        account.setAccountNumber(account.newAccountNumber());
        System.out.println(account.getAccountNumber());
        account.addNewAccount(account);

        System.out.print("Your MasterCard number: ");
        MasterCard card = new MasterCard(input1, input2);

        Credit credit = new Credit(1500);
        System.out.print("Your credit limit is: ");


    }
    public void manageList (MasterCard card, Account account, Credit credit){
        System.out.println("1- change PinCode");
        System.out.println("2- card payment");
        System.out.println("3- deposit");
        System.out.println("4- withdraw");
        System.out.println("5- Save money");
        System.out.println("6- Take out money from saving");
        System.out.println("7- Exit");

        Scanner scan = new Scanner(System.in);
        int inout = scan.nextInt();

        if (inout == 1) {
            ChangePinCode(card, scan);
        }
        if (inout == 2) {
            cardPayment(account, credit, scan);
        }
        if (inout == 3) {
            System.out.println("Enter amount: ");
            int amount = scan.nextInt();
            account.setAccountBalance(account.AccountBalance + amount);
        }
        if (inout == 4) {
            withdraw(account, credit, scan);
        }
        if (inout == 5) {
            save(account, scan);
        }
        if (inout == 6) {


            System.out.println("Enter amount:");
            double amount = scan.nextDouble();

            //Do saving account

        }
        if (inout == 7) {
            System.exit(0);
        }








    }

    private static void save(Account account, Scanner scan) {
        System.out.println("Enter amount:");
        double amount = scan.nextDouble();

        if (amount > account.AccountBalance) {
            account.setAccountBalance(account.AccountBalance + amount);
        }else {
            System.out.println("you don't have enough money");
        }
    }

    private static void withdraw(Account account, Credit credit, Scanner scan) {
        System.out.println("Enter Pin Cod: ");
        int pinCheck = scan.nextInt();
        while (pinCheck != account.pinCod) {
            System.out.println("Wrong pin!");
        }
        System.out.println("Enter Amount: ");
        double amount = scan.nextDouble();
        if (amount < account.getAccountBalance() && credit.CreditLimit > credit.CreditLimit - 150) {
            //Will not allow withdrew mor than 150 from credit.
            account.setAccountBalance(account.AccountBalance - amount);
        } else {
            System.out.println("You don't have enough money!");
        }
    }

    private static void cardPayment(Account account, Credit credit, Scanner scan) {
        System.out.println("Enter Pin Cod: ");
        int pinCheck = scan.nextInt();
        while (pinCheck != account.pinCod) {
            System.out.println("Wrong pin!");
        }

        System.out.println("Enter Amount: ");
        double amount = scan.nextDouble();
        if (amount < account.getAccountBalance() && amount < credit.CreditLimit) {
            account.setAccountBalance(account.AccountBalance - amount);
        } else {
            System.out.println("You don't have enough money!");
        }
    }

    private static void ChangePinCode(MasterCard card, Scanner scan) {
        System.out.println("Enter new PinCode 4 digits: ");
        int newPin = scan.nextInt();
        card.setPinCode(newPin);
        System.out.println("Your new PinCode is " + card.getPinCode());
    }


}
