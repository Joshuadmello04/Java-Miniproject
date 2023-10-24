package Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {

    private JLabel userLabel, passwordLabel, titleLabel;
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private Font customFont = new Font("Tahoma", Font.PLAIN, 18);
    private Color primaryColor = new Color(68, 108, 179);
    private String defaultUsername = "hello";
    private String defaultPassword = "123";

    public LoginPage() {
        setTitle("ATM Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the gradient panel with GridBagLayout
        JPanel gradientPanel = new JPanel(new GridBagLayout());
        gradientPanel.setBackground(primaryColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("ATM Login");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gradientPanel.add(titleLabel, gbc);

        userLabel = new JLabel("Username:");
        userLabel.setFont(customFont);
        userLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gradientPanel.add(userLabel, gbc);

        userText = new JTextField(15);
        userText.setFont(customFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gradientPanel.add(userText, gbc);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(customFont);
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gradientPanel.add(passwordLabel, gbc);

        passwordText = new JPasswordField(15);
        passwordText.setFont(customFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gradientPanel.add(passwordText, gbc);

        loginButton = new JButton("Login");
        loginButton.setFont(customFont);
        loginButton.setBackground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gradientPanel.add(loginButton, gbc);

        loginButton.addActionListener(this);
        add(gradientPanel);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userText.getText();
        String password = new String(passwordText.getPassword());

        if (username.equals(defaultUsername) && password.equals(defaultPassword)) {
            Landing2 landingPage = new Landing2();
            landingPage.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}
