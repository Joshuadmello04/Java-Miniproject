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

public class TransactinHistory extends JPanel {
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private String username;
    private String password;

    public TransactinHistory(String username, String password) {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        this.username=username;
        this.password=password;

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 591, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(109, Short.MAX_VALUE)
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(87, Short.MAX_VALUE)
                        )
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Transaction History");
            TransactinHistory TH = new TransactinHistory(username,password);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(TH);
            frame.pack();
            frame.setVisible(true);
        });
    }
}



