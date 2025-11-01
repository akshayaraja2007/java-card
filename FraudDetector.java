public class FraudDetector {
    // Configurable threshold for fraud detection
    private static final double THRESHOLD = 10000.0;

    /**
     * Checks if a transaction is potentially fraudulent.
     * Flags transactions over the threshold or from unknown/missing locations.
     *
     * @param amount   The transaction amount
     * @param location The location of the transaction
     * @return true if suspicious, false if safe
     */
    public static boolean isFraud(double amount, String location) {
        // Check for null or empty location
        if (location == null || location.trim().isEmpty()) {
            System.out.println("Flagged: Location is missing.");
            return true;
        }

        // Check for suspicious amount
        if (amount > THRESHOLD) {
            System.out.println("Flagged: Amount exceeds threshold.");
            return true;
        }

        // Check for unknown location
        if (location.trim().equalsIgnoreCase("unknown")) {
            System.out.println("Flagged: Location is unknown.");
            return true;
        }

        // If none of the conditions are met, transaction is safe
        return false;
    }
}