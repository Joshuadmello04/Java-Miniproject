package Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class Landing2 extends JFrame implements ActionListener {
    private JButton backButton;
    private JButton withdrawButton;
    private JTextField withdrawalAmountField;
    private Font customFont = new Font("Tahoma", Font.PLAIN, 18);
    private Color primaryColor = new Color(68, 108, 179);

    private JButton[] digitButtons;
    private String inputBuffer = "";

    public Landing2() {
        setTitle("ATM Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(800, 600);

        JPanel contentPane = new JPanel() {
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
        contentPane.setLayout(null);
        setContentPane(contentPane);

        backButton = createStyledButton("Back");
        backButton.setBounds(600, 500, 150, 40);
        backButton.addActionListener(this);
        contentPane.add(backButton);

        withdrawalAmountField = new JTextField();
        withdrawalAmountField.setBounds(200, 100, 400, 40);
        contentPane.add(withdrawalAmountField);

        withdrawButton = createStyledButton("Withdraw");
        withdrawButton.setBounds(300, 150, 200, 40);
        withdrawButton.addActionListener(this);
        contentPane.add(withdrawButton);

        int centerX = (800 - 3 * 60) / 2;
        int centerY = 250;

        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = createStyledButton(String.valueOf(i));
            digitButtons[i].setBounds(centerX + 60 * (i % 3), centerY + 60 * (i / 3), 60, 60);
            digitButtons[i].addActionListener(this);
            contentPane.add(digitButtons[i]);
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(customFont);
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        } else if (e.getSource() == withdrawButton) {
            try {
                int withdrawalAmount = Integer.parseInt(inputBuffer); // Change to int

                // Update the user's balance in the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/atm_user", "root", "Andronicus@2210");

                String username = "user";


                // Update the balance in the database
                String updateQuery = "UPDATE user SET balance = balance - ? WHERE username = ?";
                
                PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
                preparedStatement.setInt(1, withdrawalAmount); // Set as INT
                preparedStatement.setString(2, username);
                 int rowsUpdated = preparedStatement.executeUpdate();
                 rowsUpdated = preparedStatement.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Withdrawal successful. New balance updated.");
                } else {
                    JOptionPane.showMessageDialog(this, "Withdrawal failed. Please try again.");
                }

                con.close();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid withdrawal amount. Please enter a valid number.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Clear the input buffer
            inputBuffer = "";
            withdrawalAmountField.setText(inputBuffer);
        }

        else {
            JButton digitButton = (JButton) e.getSource();
            String digit = digitButton.getText();
            inputBuffer += digit;
            withdrawalAmountField.setText(inputBuffer);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Landing2 landingPage = new Landing2();
            landingPage.setVisible(true);
        });
    }
}
