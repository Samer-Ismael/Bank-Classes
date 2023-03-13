import java.util.Scanner;
public class Menu {

    public void Welcome() {
        System.out.println("Welcome to the bank app");
        System.out.println("------------------");
        System.out.println("Create new account");
        CreateNewAccounts();
    }

    public void CreateNewAccounts() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter account name: ");
        String input1 = scan.next();
        System.out.println("Enter your Pin code: \n4 digits");
        int input2 = scan.nextInt();

        Account account = new Account(input1, input2);

        System.out.print("Your new account number: ");
        account.setAccountNumber(account.newAccountNumber());
        System.out.println(account.getAccountNumber());
        account.addNewAccount(account);

        MasterCard card = new MasterCard(input1, input2);
        System.out.print("Your MasterCard number: ");
        card.setCardNumber(card.newCardNumber());
        System.out.print(card.getCardNumber());
        System.out.println();
        Credit credit = new Credit(1500);
        System.out.print("Your credit limit is: ");
        System.out.print(credit.getCreditLimit());
        System.out.println();
        SavingAccount saving = new SavingAccount();
        System.out.println("--------------------");

        Statement state = new Statement();

        while (true) {
            manageList(card, account, credit, saving, state);
        }
    }

    public void manageList(MasterCard card, Account account, Credit credit, SavingAccount saving, Statement state) {
        System.out.println("1- change PinCode");
        System.out.println("2- card payment");
        System.out.println("3- deposit");
        System.out.println("4- withdraw");
        System.out.println("5- Save money");
        System.out.println("6- Take out money from saving");
        System.out.println("7- Print statement");
        System.out.println("8- Exit");

        takeInputs(card, account, credit, saving, state);
        System.out.println("Account balance: " + account.getAccountBalance());
        System.out.println("Saving account balance: " + saving.getBalance());
        System.out.println("Credit limit: " + credit.getCreditLimit());
        System.out.println("----------");
    }

    private static void takeInputs(MasterCard card, Account account, Credit credit, SavingAccount saving, Statement state) {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        if (input == 1) {
            ChangePinCode(card);
            System.out.println("----------");
        }
        if (input == 2) {
            cardPayment(account, credit, card, state);
            System.out.println("----------");
        }
        if (input == 3) {
            deposit(account, state, credit);
            System.out.println("----------");
        }
        if (input == 4) {
            withdraw(account, credit,card, state);
            System.out.println("----------");
        }
        if (input == 5) {
            save(account,saving, state);
            System.out.println("----------");
        }
        if (input == 6) {
            outFromSaving(saving,account, state);
            System.out.println("----------");
        }
        if (input == 7) {
            System.out.println("----------");
            state.printStatement();
        }
        if (input == 8) {
            System.exit(0);
        }
    }

    private static void deposit(Account account, Statement state, Credit credit) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount: ");
        int amount = scan.nextInt();
        if (amount > 0) {

            credit.CreditLimit += amount;

            if (credit.CreditLimit > 1500) {
                double left = credit.CreditLimit - 1500;
                account.AccountBalance += left;
                credit.CreditLimit = 1500;
                state.addToList("Deposit ", amount);
            }
        }else {
            System.out.println("Not allowed");
        }
    }

    private static void save(Account account, SavingAccount saving, Statement state) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter amount:");
        double amount = scan.nextDouble();
        if (amount <= account.AccountBalance && amount > 0) {
            account.AccountBalance -= amount;
            saving.balance += amount;
            state.addToList("Saving ", amount);
        } else {
            System.out.println("you don't have enough money");
        }
    }

    private static void outFromSaving(SavingAccount saving, Account account, Statement state) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount");
        double amount = scan.nextDouble();

        if (amount <= saving.balance && amount > 0) {
            saving.balance -= amount;
            account.AccountBalance += amount;
            state.addToList("Saving ", -amount);
        } else {
            System.out.println("You don't have enough money");
        }
    }

    private static void withdraw(Account account, Credit credit, MasterCard card, Statement state) {
        Scanner scan = new Scanner(System.in);
        pinCheck(card);

        System.out.println("Enter Amount: ");
        double amount = scan.nextDouble();
        if (amount <= account.getAccountBalance() && credit.CreditLimit >= 1000) {
            //Will not allow withdrew mor than 500 from credit.
            account.setAccountBalance(account.AccountBalance - amount);
            state.addToList("Withdraw ", amount);
        } else {
            System.out.println("You don't have enough money!");
        }
    }

    private static void pinCheck(MasterCard card) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Pin Cod: ");
            int pinCheck = scan.nextInt();
            if (pinCheck == card.pinCode) {
                break;
            }
            System.out.println("Wrong pin!");
        }
    }

    private static void cardPayment(Account account, Credit credit, MasterCard card, Statement state) {
        Scanner scan = new Scanner(System.in);

        pinCheck(card);

        System.out.println("Enter Amount: ");
        double amount = scan.nextDouble();
        if (amount <= account.AccountBalance && amount < credit.CreditLimit && amount > 0) {
            account.setAccountBalance(account.AccountBalance - amount);
            state.addToList("Card Payment ", amount);
        }
        if (amount > account.AccountBalance && amount < credit.CreditLimit && amount > 0) {
            double left = amount - account.AccountBalance;
            account.AccountBalance = 0;
            credit.CreditLimit -= left;
            state.addToList("Card Payment ", amount);
        }
        if (amount < 0) {
            System.out.println("Not allowed!");
        } else {
            System.out.println("You don't have enough money!");
        }
    }

    private static void ChangePinCode(MasterCard card) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter new Pin code: \n4 digits ");
        int newPin = scan.nextInt();
        card.setPinCode(newPin);
        System.out.println("Your new PinCode is " + card.getPinCode());
    }
}
