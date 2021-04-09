package com.adidyk.frame;

import com.adidyk.AccountPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AccountFrame extends JFrame {


    private AccountPanel accountPanel;

    public AccountFrame(AccountPanel accountPanel) {
        this.accountPanel = accountPanel;
        this.init();
    }

    public void init() {
        this.setSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.accountPanel.init();
        this.add(accountPanel, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                accountPanel.saveJsonToFile();
            }
        });

        this.pack();
        this.setVisible(true);
        }

    }