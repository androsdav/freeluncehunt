package com.adidyk.frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstFrame extends JFrame {
    private JPanel firstPanel;
    private JTextField textField1;
    private JLabel nameLabel;
    private JButton button1;

    public FirstFrame() {
        button1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
