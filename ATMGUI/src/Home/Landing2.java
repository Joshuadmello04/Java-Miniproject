package Home;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class Landing2 extends JFrame implements ActionListener {
    private JButton backButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JTextField withdrawalAmountField;
    private JButton balanceButton;
    private Font customFont = new Font("Tahoma", Font.PLAIN, 18);
    private Color primaryColor = new Color(68, 108, 179);
    private JButton[] digitButtons;
    private JButton backspaceButton;
    private String inputBuffer = "";
    private String username;
    private String password;
    private int balance;
    public Landing2(String username, String password, int balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        setTitle("ATM Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(800, 600);
        int centerX = (800 - 3 * 60) / 2;
        int centerY = 250;
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

        backButton = new JButton("Back");
        backButton.setFont(customFont);
        backButton.setBackground(primaryColor);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(600, 500, 175, 40);
        backButton.addActionListener(this);
        contentPane.add(backButton);

        balanceButton = new JButton("Check Balance");
        balanceButton.setFont(customFont);
        balanceButton.setBackground(primaryColor);
        balanceButton.setForeground(Color.WHITE);
        balanceButton.setBounds(600, 360, 175, 40);
        balanceButton.addActionListener(this);
        contentPane.add(balanceButton);

        withdrawalAmountField = new JTextField();
        withdrawalAmountField.setBounds(200, 100, 400, 40);
        contentPane.add(withdrawalAmountField);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(customFont);
        withdrawButton.setBackground(new Color(255, 140, 0));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setBounds(250, 175, 150, 40); // Adjusted position for Withdraw button
        withdrawButton.addActionListener(this);
        contentPane.add(withdrawButton);

        backspaceButton = new JButton("←");
        backspaceButton.setFont(customFont);
        backspaceButton.setBackground(primaryColor);
        backspaceButton.setForeground(Color.WHITE);
        backspaceButton.setBounds(650, 100, 60, 40);
        backspaceButton.addActionListener(this);
        contentPane.add(backspaceButton);

        depositButton = new JButton("Deposit");
        depositButton.setFont(customFont);
        depositButton.setBackground(new Color(0, 255, 0));
        depositButton.setForeground(Color.WHITE);
        depositButton.setBounds(450, 175, 150, 40); // Adjusted position for Deposit button
        depositButton.addActionListener(this);
        contentPane.add(depositButton);

        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].setFont(customFont);
            digitButtons[i].setBackground(primaryColor);
            digitButtons[i].setForeground(Color.WHITE);
            digitButtons[i].setBounds(centerX + 60 * (i % 3), centerY + 60 * (i / 3), 60, 60);
            digitButtons[i].addActionListener(this);
            contentPane.add(digitButtons[i]);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String databaseUrl = "jdbc:mysql://localhost:3306/atm%20user";
        String dbUsername = "root";
        String dbPassword = "";

        if (e.getSource() == backButton) {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        }else if (e.getSource() == backspaceButton) {
            if (inputBuffer.length() > 0) {
                inputBuffer = inputBuffer.substring(0, inputBuffer.length() - 1);
                withdrawalAmountField.setText(inputBuffer);
            }
        }
        else if (e.getSource() == withdrawButton) {
            try {
                int withdrawalAmount = Integer.parseInt(inputBuffer);
                if (withdrawalAmount > balance) {
                    JOptionPane.showMessageDialog(this, "Error...Insufficient funds..Crash!");
                    System.exit(0);
                }
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(databaseUrl, dbUsername, dbPassword);

                String updateQuery = "UPDATE user SET balance = balance - ? WHERE username = ?";
                PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
                preparedStatement.setInt(1, withdrawalAmount);
                preparedStatement.setString(2, username);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    balance -= withdrawalAmount;
                    JOptionPane.showMessageDialog(this, "Rs."+withdrawalAmount+" successfully withdrawn . Updated Balance is : " + (balance));
                } else {
                    JOptionPane.showMessageDialog(this, "Withdrawal failed. Please try again.");
                }
                con.close();
            } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            inputBuffer = "";
            withdrawalAmountField.setText(inputBuffer);
        } else if (e.getSource() == depositButton) {
            try {
                int depositAmount = Integer.parseInt(inputBuffer);
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(databaseUrl, dbUsername, dbPassword);
                String updateQuery = "UPDATE user SET balance = balance + ? WHERE username = ?";
                PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
                preparedStatement.setInt(1, depositAmount);
                preparedStatement.setString(2, username);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    balance += depositAmount;
                    JOptionPane.showMessageDialog(this, "Rs."+depositAmount+" successfully deposited. Updated Balance is: " + (balance));
                } else {
                    JOptionPane.showMessageDialog(this, "Deposit failed. Please try again.");
                }
                con.close();
            } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            inputBuffer = "";
            withdrawalAmountField.setText(inputBuffer);
        } else if (e.getSource() == balanceButton) {
            Balance bal = new Balance(username, password, balance);
            bal.setVisible(true);
        } else {
            JButton digitButton = (JButton) e.getSource();
            String digit = digitButton.getText();
            inputBuffer += digit;
            withdrawalAmountField.setText(inputBuffer);
        }
    }
}
