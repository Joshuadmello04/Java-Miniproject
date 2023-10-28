package Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class Balance extends JFrame implements ActionListener {
    private JButton backButton;
    private int balance;
    private String username;
    private String password;

    public Balance(String username, String password,int balance) {
        this.username = username;
        this.password = password;
        this.balance=balance;

        setTitle("Balance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600, 400);

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

        String databaseUrl = "jdbc:mysql://localhost:3306/atm%20user"; // Replace "atm_user" with your actual database name
        String dbUsername = "root"; // Replace with your database username
        String dbPassword = ""; // Replace with your database password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(databaseUrl, dbUsername, dbPassword);

            String query = "SELECT balance FROM user WHERE username = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getInt("balance");
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to fetch balance.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JLabel balanceLabel = new JLabel("Username: " + username.toUpperCase());
        balanceLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(100, 100, 400, 40);
        contentPane.add(balanceLabel);

        JLabel amountLabel = new JLabel("Current Balance: Rs. " + balance);
        amountLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBounds(100, 150, 400, 40);
        contentPane.add(amountLabel);

        backButton = createStyledButton("Back");
        backButton.setBounds(100, 250, 200, 40);
        backButton.addActionListener(this);
        contentPane.add(backButton);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button.setBackground(new Color(68, 108, 179));
        button.setForeground(Color.WHITE);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            Landing2 landingPage = new Landing2(username, password, balance);
            landingPage.setVisible(true);
            dispose();
        }
    }
}
