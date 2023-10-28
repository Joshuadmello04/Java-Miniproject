package Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Deposit extends JFrame implements ActionListener {

    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JButton backButton;
    private JPanel contentPane;
    private double userBalance;
    private String username;
    private String password;

    public Deposit(double balance, String username, String password) {
        this.userBalance = balance;
        this.username = username;
        this.password = password;

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(37, 78, 123), getWidth(), getHeight(),
                        new Color(0, 128, 255));
                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        label = new JLabel("Deposit amount");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(label, gbc);

        label = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(label, gbc);

        textField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(textField, gbc);

        button = new JButton("OK");
        gbc.gridx = 1;
        gbc.gridy = 2;
        button.addActionListener(this);
        contentPane.add(button, gbc);

        backButton = createStyledButton("Back");
        gbc.gridx = 3;
        gbc.gridy = 4;
        backButton.addActionListener(this);
        contentPane.add(backButton, gbc);

        setTitle("Deposit GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setPreferredSize(new Dimension(600, 400));
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String amountString = textField.getText();
            try {
                double amount = Double.parseDouble(amountString);
                DatabaseHelper.deposit(username, amount);
                double newBalance = DatabaseHelper.getBalanceFromDatabase(username, password);
                JOptionPane.showMessageDialog(this,
                        "You have successfully deposited Rs. " + amount + ". New Balance: Rs. " + newBalance, "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Deposited: " + amount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            Landing2 land = new Landing2(userBalance, username, password);
            land.setVisible(true);
            dispose();
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Deposit deposit = new Deposit(0.0, "default", "default");
            deposit.setVisible(true);
        });
    }
}
