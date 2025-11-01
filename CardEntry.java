import java.util.Scanner;

public class CardEntry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Card Registration ===");

        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.nextLine().trim();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine().trim();

        // Save to cards.txt
        FileManager.saveCard(cardNumber, pin, location);

        System.out.println("✅ Card details saved successfully.");
        System.out.println("You can now proceed to transaction validation or fraud check.");

        scanner.close();
    }
}