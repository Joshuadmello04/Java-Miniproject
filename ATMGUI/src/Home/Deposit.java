package Home;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Deposit extends JFrame implements ActionListener {

    private Label label;
    private TextField textField;
    private Button button;

    public Deposit() {
        setLayout(new GridBagLayout()); // Use GridBagLayout for precise component positioning
        
        // Create constraints for the title label to center it
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.insets = new Insets(10, 10, 10, 10);
        titleConstraints.gridwidth = 2; // Span 2 columns
        titleConstraints.anchor = GridBagConstraints.CENTER;

        label = new Label("Deposit amount");
        add(label, titleConstraints);

        // Create constraints for the amount label
        GridBagConstraints amountLabelConstraints = new GridBagConstraints();
        amountLabelConstraints.gridx = 0;
        amountLabelConstraints.gridy = 1;
        amountLabelConstraints.insets = new Insets(10, 10, 10, 10);

        label = new Label("Amount:");
        add(label, amountLabelConstraints);

        // Create constraints for the amount TextField
        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.gridx = 1;
        textFieldConstraints.gridy = 1;
        textFieldConstraints.insets = new Insets(10, 10, 10, 10);
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;

        textField = new TextField("Amount", 20);
        add(textField, textFieldConstraints);

        // Create constraints for the OK button
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 2;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        button = new Button("OK");
        button.addActionListener(this);
        add(button, buttonConstraints);

        setTitle("Deposit GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) 
        {   
            String amount = textField.getText();
            {
                JOptionPane.showMessageDialog(this, "You have successfully deposited " + amount + " amount", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            }
            System.out.println("Deposited: " + amount);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Deposit deposit = new Deposit();
            deposit.setVisible(true);
        });
    }
}
