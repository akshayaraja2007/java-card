import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public String id;
    public String cardNumber;
    public String pin;
    public String status;
    public String timestamp;

    public Transaction(String id, String cardNumber, String pin, String status) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.status = status;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Converts transaction to a text line for file storage
    public String toText() {
        return id + "," + cardNumber + "," + pin + "," + status + "," + timestamp;
    }
}