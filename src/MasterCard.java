import java.util.Random;
public class MasterCard {
    private String CardNumber = null;
    private double CardBalance = 0;
    private int pinCode;
    private String name;
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
