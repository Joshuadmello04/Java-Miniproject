package Home;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Withdraw extends JFrame implements ActionListener {

    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JButton backButton;
    private JPanel contentPane;

    public Withdraw() {



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

        label = new JLabel("Withdraw amount");
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

        setTitle("Withdraw GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setPreferredSize(new Dimension(600, 400));
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String amount = textField.getText();
            JOptionPane.showMessageDialog(this, "You have successfully withdrawn " + amount + " amount", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Withdrawn: " + amount);
        } else if (e.getSource() == backButton) {
            Landing2 land = new Landing2();
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
            Withdraw withdraw = new Withdraw();
            withdraw.setVisible(true);
        });
    }
}