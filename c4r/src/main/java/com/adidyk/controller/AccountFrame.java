package com.adidyk.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class AccountFrame used to add panel, shows this panel and implement control.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 13.04.2021.
 * @version 1.0.
 */
public class AccountFrame extends JFrame {


    /**
     * @param accountPanel - account panel.
     */
    private AccountPanel accountPanel;

    /**
     * AccountFrame - constructor.
     * @param accountPanel - account panel.
     */
    public AccountFrame(AccountPanel accountPanel) {
        super("Account Panel");
        this.accountPanel = accountPanel;
        this.initJFrame();
    }

    /**
     * initJFrame - initialization frame.
     */
    private void initJFrame() {
        this.setSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(accountPanel, BorderLayout.CENTER);
        this.closeFrame();
        this.pack();
        this.setVisible(true);
    }

    /**
     * closeFrame - after closed frame all list account from table save to storage.
      */
    private void closeFrame() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                accountPanel.saveAllAccount();
            }
        });

    }

}