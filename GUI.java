import javax.swing.*;
import java.awt.*;

public class GUI {
    public void showForm() {
        JFrame frame = new JFrame("Credit Card Fraud Detection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the window

        // Create components
        JLabel cardLabel = new JLabel("Card Number:");
        JTextField cardField = new JTextField(16);
        cardField.setToolTipText("Enter your 16-digit card number");

        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField(4);
        pinField.setToolTipText("Enter your 4-digit PIN");

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        amountField.setToolTipText("Enter transaction amount");

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();
        locationField.setToolTipText("Enter transaction location");

        JButton submit = new JButton("Check");

        // Layout setup
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(cardLabel, gbc);
        gbc.gridx = 1;
        panel.add(cardField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(pinLabel, gbc);
        gbc.gridx = 1;
        panel.add(pinField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(amountLabel, gbc);
        gbc.gridx = 1;
        panel.add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(locationLabel, gbc);
        gbc.gridx = 1;
        panel.add(locationField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(submit, gbc);

        frame.add(panel);
        frame.setVisible(true);

        // Button logic
        submit.addActionListener(e -> {
            String cardNumber = cardField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();
            String location = locationField.getText().trim();
            double amount;

            // Input validation
            if (cardNumber.isEmpty() || pin.isEmpty() || amountField.getText().trim().isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                amount = Double.parseDouble(amountField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a numeric value.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean validCard = CardValidator.isValid(cardNumber, pin);
            boolean suspicious = FraudDetector.isFraud(amount, location);

            String status = (!validCard || suspicious) ? "FRAUD" : "SAFE";
            Transaction tx = new Transaction("TXN" + System.currentTimeMillis(), cardNumber, pin, status);
            FileManager.saveTransaction(tx);

            JOptionPane.showMessageDialog(frame,
                "Transaction ID: " + tx.id + "\nStatus: " + status + "\nAmount: ₹" + amount,
                "Result", status.equals("SAFE") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);

            // Clear fields after submission
            cardField.setText("");
            pinField.setText("");
            amountField.setText("");
            locationField.setText("");
        });
    }
}