import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CardValidator {
    public static boolean isValid(String cardNumber, String pin) {
        try (BufferedReader reader = new BufferedReader(new FileReader("cards.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Expecting format: cardNumber,pin,location
                if (parts.length == 3 && parts[0].equals(cardNumber) && parts[1].equals(pin)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading cards.txt");
        }
        return false;
    }
}