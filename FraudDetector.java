public class FraudDetector {
    public static boolean isFraud(double amount, String location) {
        double threshold = 10000.0;
        return amount > threshold || location.equalsIgnoreCase("unknown");
    }
}