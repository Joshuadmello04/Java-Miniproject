package Home;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Landing2 extends JFrame implements ActionListener {
    private JButton withdrawButton, depositButton, balanceButton, transactionsButton, backButton;
    private Font customFont = new Font("Tahoma", Font.PLAIN, 18);
    private Color primaryColor = new Color(68, 108, 179);

    public Landing2() {
        setTitle("ATM Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600, 400); // Adjust the size according to your preference

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

        withdrawButton = createStyledButton("Withdraw");
        withdrawButton.setBounds(50, 50, 200, 50);
        withdrawButton.addActionListener(this);
        contentPane.add(withdrawButton);

        depositButton = createStyledButton("Deposit");
        depositButton.setBounds(50, 120, 200, 50);
        depositButton.addActionListener(this);
        contentPane.add(depositButton);

        balanceButton = createStyledButton("Check Balance");
        balanceButton.setBounds(50, 190, 200, 50);
        balanceButton.addActionListener(this);
        contentPane.add(balanceButton);

        transactionsButton = createStyledButton("See Transactions");
        transactionsButton.setBounds(50, 260, 200, 50);
        transactionsButton.addActionListener(this);
        contentPane.add(transactionsButton);

        backButton = createStyledButton("Back");
        backButton.setBounds(400, 300, 100, 40);
        backButton.addActionListener(this);
        contentPane.add(backButton);
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
        if (e.getSource() == withdrawButton) {
            Deposit Depo =new Deposit();
            Depo.setVisible(true);
            dispose();
        } else if (e.getSource() == depositButton) {
            // Handle Deposit button action
        } else if (e.getSource() == transactionsButton) {
            // Handle Transactions button action
        } else if (e.getSource() == balanceButton) {
            // Handle Check Balance button action
        } else if (e.getSource() == backButton) {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Landing2 landingPage = new Landing2();
            landingPage.setVisible(true);
        });
    }
}
