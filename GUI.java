import javax.swing.*;

public class GUI {
    public void showForm() {
        JFrame frame = new JFrame("Credit Card Fraud Detection");

        JTextField cardField = new JTextField();
        JPasswordField pinField = new JPasswordField();
        JTextField amountField = new JTextField();
        JTextField locationField = new JTextField();
        JButton submit = new JButton("Check");

        submit.addActionListener(e -> {
            String cardNumber = cardField.getText();
            String pin = new String(pinField.getPassword());
            double amount = Double.parseDouble(amountField.getText());
            String location = locationField.getText();

            boolean validCard = CardValidator.isValid(cardNumber, pin);
            boolean suspicious = FraudDetector.isFraud(amount, location);

            String status = (!validCard || suspicious) ? "FRAUD" : "SAFE";
            Transaction tx = new Transaction("TXN" + System.currentTimeMillis(), cardNumber, pin, status);
            FileManager.saveTransaction(tx);

            JOptionPane.showMessageDialog(frame, status.equals("SAFE") ? "Transaction Safe" : "Fraud Detected!");
        });

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(new JLabel("Card Number:"));
        frame.add(cardField);
        frame.add(new JLabel("PIN:"));
        frame.add(pinField);
        frame.add(new JLabel("Amount:"));
        frame.add(amountField);
        frame.add(new JLabel("Location:"));
        frame.add(locationField);
        frame.add(submit);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}