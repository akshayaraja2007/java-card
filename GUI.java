import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GUI {
    public void showForm() {
        JFrame frame = new JFrame("💳 Credit Card Fraud Detection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Gradient background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(50, 60, 150), getWidth(), getHeight(), new Color(90, 150, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        // Credit-card style panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setOpaque(false);
        cardPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(255, 255, 255, 150), 2, true),
                new EmptyBorder(20, 25, 20, 25)
        ));

        // Components
        JLabel title = new JLabel("Credit Card Verification");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel cardLabel = createLabel("Card Number:");
        JTextField cardField = createTextField("Enter your 16-digit card number");

        JLabel pinLabel = createLabel("PIN:");
        JPasswordField pinField = new JPasswordField(4);
        pinField.setToolTipText("Enter your 4-digit PIN");
        pinField.setBorder(new RoundedBorder(10));

        JLabel amountLabel = createLabel("Amount (₹):");
        JTextField amountField = createTextField("Enter transaction amount");

        JLabel locationLabel = createLabel("Location:");
        JTextField locationField = createTextField("Enter transaction location");

        JButton submit = new JButton("Verify Transaction");
        submit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        submit.setBackground(new Color(255, 215, 0));
        submit.setForeground(Color.BLACK);
        submit.setFocusPainted(false);
        submit.setBorder(new RoundedBorder(20));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Layout config
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        cardPanel.add(title, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        cardPanel.add(cardLabel, gbc);
        gbc.gridx = 1;
        cardPanel.add(cardField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        cardPanel.add(pinLabel, gbc);
        gbc.gridx = 1;
        cardPanel.add(pinField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        cardPanel.add(amountLabel, gbc);
        gbc.gridx = 1;
        cardPanel.add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        cardPanel.add(locationLabel, gbc);
        gbc.gridx = 1;
        cardPanel.add(locationField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        cardPanel.add(submit, gbc);

        backgroundPanel.add(cardPanel);
        frame.add(backgroundPanel);
        frame.setVisible(true);

        // Button logic
        submit.addActionListener(e -> {
            String cardNumber = cardField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();
            String location = locationField.getText().trim();
            double amount;

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
            String status = (!validCard || suspicious) ? "⚠️ FRAUD" : "✅ SAFE";

            Transaction tx = new Transaction("TXN" + System.currentTimeMillis(), cardNumber, pin, status);
            FileManager.saveTransaction(tx);

            JOptionPane.showMessageDialog(frame,
                "Transaction ID: " + tx.id + "\nStatus: " + status + "\nAmount: ₹" + amount,
                "Verification Result", status.contains("SAFE") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);

            cardField.setText("");
            pinField.setText("");
            amountField.setText("");
            locationField.setText("");
        });
    }

    // Helper for styled labels
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    // Helper for styled text fields
    private JTextField createTextField(String tooltip) {
        JTextField tf = new JTextField();
        tf.setToolTipText(tooltip);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBorder(new RoundedBorder(10));
        return tf;
    }

    // Rounded border class
    static class RoundedBorder extends LineBorder {
        private int radius;
        RoundedBorder(int radius) {
            super(Color.WHITE, 1, true);
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }
    }
}
