import java.util.Scanner;

public class CardEntry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input card details
        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        // Save to cards.txt
        FileManager.saveCard(cardNumber, pin);

        // Validate card
        boolean isValid = CardValidator.isValid(cardNumber, pin);

        // Create transaction object
        Transaction tx = new Transaction("TXN" + System.currentTimeMillis(), cardNumber, pin, isValid ? "SAFE" : "FRAUD");

        // Save transaction result
        FileManager.saveTransaction(tx);

        System.out.println("Result: " + (isValid ? "Transaction Safe" : "Fraud Detected"));

        scanner.close();
    }
}