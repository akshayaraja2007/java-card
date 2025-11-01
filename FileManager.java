import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    // Save card with location
    public static void saveCard(String cardNumber, String pin, String location) {
        try (FileWriter writer = new FileWriter("cards.txt", true)) {
            writer.write(cardNumber + "," + pin + "," + location + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to cards.txt");
        }
    }

    // Save transaction
    public static void saveTransaction(Transaction tx) {
        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            writer.write(tx.toText() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to transactions.txt");
        }
    }
}