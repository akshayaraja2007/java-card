import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void saveCard(String cardNumber, String pin) {
        try (FileWriter writer = new FileWriter("cards.txt", true)) {
            writer.write(cardNumber + "," + pin + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to cards.txt");
        }
    }

    public static void saveTransaction(Transaction tx) {
        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            writer.write(tx.toText() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to transactions.txt");
        }
    }
}