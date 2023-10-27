package Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Balance extends JFrame implements ActionListener {
    private JLabel label;
    private JLabel balanceLabel;
    private JButton backButton;
    private JPanel contentPane;

    public Balance() {
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
        gbc.insets = new Insets(10, 10, 10, 10); // Padding for components

        label = new JLabel("Dear User");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(label, gbc);

        label = new JLabel("Your Current Balance:");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(label, gbc);

        balanceLabel = new JLabel("Rs. 12,000"); // Sample balance, replace it with your actual balance value
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        balanceLabel.setForeground(Color.WHITE);
        gbc.gridx = 5;
        gbc.gridy = 1;
        contentPane.add(balanceLabel, gbc);

        backButton = createStyledButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPane.add(backButton, gbc);

        setTitle("Balance GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            Landing2 land = new Landing2();
            land.setVisible(true);
            dispose();
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(37, 78, 123));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(this); // Add ActionListener to the button
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Balance balance = new Balance();
            balance.setVisible(true);
        });
    }
}
