public class MasterCard {
    double CardNumber;
    double CardBalance;
    public MasterCard(double cardNumber, double cardBalance) {
        CardNumber = cardNumber;
        CardBalance = cardBalance;
    }
    public double getCardNumber() {
        return CardNumber;
    }
    public void setCardNumber(double cardNumber) {
        CardNumber = cardNumber;
    }
    public double getCardBalance() {
        return CardBalance;
    }
    public void setCardBalance(double cardBalance) {
        CardBalance = cardBalance;
    }
}
