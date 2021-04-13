package com.adidyk;

import com.adidyk.controller.AccountPanel;
import com.adidyk.controller.AccountFrame;
import com.adidyk.service.AccountTableModel;
import com.adidyk.repository.AccountRepository;
import javax.swing.*;

/**
 * Class AccountFrame is main class, used to run project.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 13.04.2021.
 * @version 1.0.
 */
public class StartUi {

    /**
     * @param accountPanel - account panel.
     */
    private AccountPanel accountPanel;

    /**
     * StartUi - constructor.
     * @param accountPanel - account panel.
     */
    private StartUi(AccountPanel accountPanel) {
        this.accountPanel = accountPanel;
    }

    /**
     * run - run project.
     */
    private void run() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            new AccountFrame(this.accountPanel);
        });
    }

     /**
     * main - main class.
     * @param args - array string arg.
     */
    public static void main(String[] args) {
        AccountPanel accountPanel = new AccountPanel(new AccountTableModel(new AccountRepository()));
        new StartUi(accountPanel).run();
    }

}