package com.adidyk;

import com.adidyk.frame.FrameAccount;
import javax.swing.*;

/**
 * Class StartUi. Start project.
 */
public class StartUi {

     /**
     * main - main.
     * @param arg - arg.
     */
    public  static void main(String[] arg) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            new FrameAccount();
        });
    }

}