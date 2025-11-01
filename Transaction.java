public class Transaction {
    public String id;
    public String cardNumber;
    public String pin;
    public String status;

    public Transaction(String id, String cardNumber, String pin, String status) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.status = status;
    }

    public String toText() {
        return id + "," + cardNumber + "," + pin + "," + status;
    }
}