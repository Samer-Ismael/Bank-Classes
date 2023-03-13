import java.util.Random;
public class MasterCard {
    String CardNumber = null;
    double CardBalance = 0;
    int pinCode;
    String name;
    public MasterCard(String name, int pinCode) {
        this.name = name;
        this.pinCode = pinCode;
    }
    public String getCardNumber() {
        return CardNumber;
    }
    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }
    public double getCardBalance() {
        return CardBalance;
    }
    public void setCardBalance(double cardBalance) {
        CardBalance = cardBalance;
    }
    public void pay (double input){
        CardBalance -= input;
    }
    public String newCardNumber (){
        Random rand = new Random();
        String CardNumber = rand.nextInt(1000, 9999) + "-" + rand.nextInt(1000,9999) + "-" + rand.nextInt(1000,9999) + "-" + rand.nextInt(1000,9999);
        return CardNumber;
    }
    public int getPinCode() {
        return pinCode;
    }
    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
