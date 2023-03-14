import java.util.Scanner;
public class Menu {

    public void Welcome() {
        System.out.println("Welcome to the bank app");
        System.out.println("------------------");
        System.out.println("Create new account");
        CreateNewAccounts();
    }

    public void CreateNewAccounts() {
        Scanner name = new Scanner(System.in);
        System.out.println("Enter account name or type Exit to close the program: ");
        String input1 = name.next();
        if (input1.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
        System.out.println("Enter your Pin code: ");
        Scanner scan = new Scanner(System.in);

        if (checkIfInteger(scan)) {
            int input2 = scan.nextInt();

            Account account = new Account(input1, input2);//New account
            System.out.print("Your new account number: ");
            account.setAccountNumber(account.newAccountNumber());
            System.out.println(account.getAccountNumber());
            account.addNewAccount(account);

            MasterCard card = new MasterCard(input1, input2);//New MasterCard
            System.out.print("Your MasterCard number: ");
            card.setCardNumber(card.newCardNumber());
            System.out.print(card.getCardNumber());
            System.out.println();

            Credit credit = new Credit(1500);//New credit
            System.out.print("Your credit limit is: ");
            System.out.print(credit.getCreditLimit());
            System.out.println();

            SavingAccount saving = new SavingAccount();//New saving account
            System.out.println("--------------------");
            Statement state = new Statement();

            while (true) {
                //Will run until the user chose to exit by entering number 8
                manageList(card, account, credit, saving, state);
            }
        } else {
            System.out.println("Invalid input!");
        }
        CreateNewAccounts();
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

        if (checkIfInteger(scan)) { //Will check if input is number

            int input = scan.nextInt();
            if (input >= 1 && input <= 8) { // Will enter this loop when number is 1 - 8.
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
                    withdraw(account, credit, card, state);
                    System.out.println("----------");
                }
                if (input == 5) {
                    save(account, saving, state);
                    System.out.println("----------");
                }
                if (input == 6) {
                    outFromSaving(saving, account, state);
                    System.out.println("----------");
                }
                if (input == 7) {
                    System.out.println("----------");
                    state.printStatement();
                }
                if (input == 8) {
                    System.out.println("See ya!");
                    System.exit(0);
                }
            } else {
                System.out.println("Enter a number from the list!");
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("Invalid input!");
        }
    }

    private static void deposit(Account account, Statement state, Credit credit) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount: ");

        if (scan.hasNextDouble()) { // Will allow only number to be entered
            double amount = scan.nextDouble();
            if (amount > 0) { // Loop will not allow deposit in minus.
                credit.setCreditLimit(credit.getCreditLimit() + amount);
                // Will fill the credit if it's less than 1500 first and then set the rest in the balance.
                if (credit.getCreditLimit() > 1500) {
                    double left = credit.getCreditLimit() - 1500;
                    account.setAccountBalance(account.getAccountBalance() + left);
                    credit.setCreditLimit(1500);
                    state.addToList("Deposit ", amount);
                    //Will add ths action to the statement list
                }
            } else {
                System.out.println("Not allowed");
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("Invalid inout!");
        }
    }

    private static void save(Account account, SavingAccount saving, Statement state) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount:");

        if (scan.hasNextDouble()) { // Will allow only number
            double amount = scan.nextDouble();
            //Will only send money to saving account
            if (amount <= account.getAccountBalance() && amount > 0) {
                account.setAccountBalance(account.getAccountBalance() - amount);
                saving.balance += amount;
                state.addToList("Saving ", amount);
            } else {
                System.out.println("you don't have enough money");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    private static void outFromSaving(SavingAccount saving, Account account, Statement state) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount");

        if (checkIfDouble(scan)) { // Will enter only when input is number
            double amount = scan.nextDouble();
            //Will take money out of saving account back to balance
            if (amount <= saving.balance && amount > 0) {
                saving.balance -= amount;
                account.setAccountBalance(account.getAccountBalance() + amount);
                state.addToList("Saving ", -amount);
            } else {
                System.out.println("You don't have enough money");
            }
        } else {
            System.out.println("Invalid input!");
        }
    }

    private static void withdraw(Account account, Credit credit, MasterCard card, Statement state) {
        pinCheck(card); // This will ask for pin code and check if it's right.
        System.out.println("Enter Amount: ");
        Scanner scan = new Scanner(System.in);

        if (checkIfDouble(scan)) { //Will make sure input is double
            double amount = scan.nextDouble();
            if (amount > 0) { //Will not allow to withdraw in menus amount
                if (account.getAccountBalance() > amount) {
                    account.setAccountBalance(account.getAccountBalance() - amount);
                    state.addToList("Withdraw", amount);
                }
                //Will not allow to withdraw more than 500 out of credit
                if (account.getAccountBalance() < amount) {
                    double left = amount - account.getAccountBalance();
                    if (credit.getCreditLimit() - left >= 1000) {
                        credit.setCreditLimit(credit.getCreditLimit() - left);
                        account.setAccountBalance(0);
                    } else {
                        System.out.println("You are not allow to withdraw more than 500 from credit!");
                    }
                }
            } else {
                System.out.println("Nice try!");//For minus input
            }
        } else {
            System.out.println("Invalid input!");//For number check
        }
    }

    private static void pinCheck(MasterCard card) {
        //Check the pin code
        System.out.println("Enter Pin Cod: ");
        Scanner scan = new Scanner(System.in);
        int counter = 3;

        if (checkIfInteger(scan)) { //Will make sure the code is numbers
            while (counter > 0) {
                counter--;
                int pinCheck = scan.nextInt();
                if (pinCheck == card.getPinCode()) {
                    break;
                }
                System.out.println("Wrong pin! you can try 3 times");
                if (counter == 0) {
                    System.out.println("You have entered wrong pin code 3 times.");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Invalid input!");
            pinCheck(card);
        }
    }

    private static void cardPayment(Account account, Credit credit, MasterCard card, Statement state) {

        pinCheck(card);
        System.out.println("Enter Amount: ");
        Scanner scan = new Scanner(System.in);

        if (checkIfDouble(scan)) {
            double amount = scan.nextDouble();

            //Will not allow payment minus amount
            if (amount > 0) {
                if (amount <= account.getAccountBalance() && amount < credit.getCreditLimit()) {
                    account.setAccountBalance(account.getAccountBalance() - amount);
                    state.addToList("Card Payment ", amount);
                }
                if (amount > account.getAccountBalance() && amount <= credit.getCreditLimit()) {
                    double left = amount - account.getAccountBalance();
                    account.setAccountBalance(0);
                    credit.setCreditLimit(credit.getCreditLimit() - left);
                    state.addToList("Card Payment ", amount);
                } else {
                    System.out.println("You don't have enough money!");
                }
            } else {
                System.out.println("Nice try!");
            }

        } else {
            System.out.println("Invalid input!");
        }
    }

    private static void ChangePinCode(MasterCard card) {
        Scanner scan = new Scanner(System.in);

        pinCheck(card); //Will check the old pin code
        System.out.println("Enter new Pin code: ");
        int newPin = scan.nextInt();
        card.setPinCode(newPin);// Will change the pin code
        System.out.println("Your new PinCode is " + card.getPinCode());
    }

    private static boolean checkIfInteger(Scanner scan) {
        boolean itIs = scan.hasNextInt();
        return itIs;
    }

    private static boolean checkIfDouble(Scanner scan) {
        boolean itIs = scan.hasNextDouble();
        return itIs;
    }
}
