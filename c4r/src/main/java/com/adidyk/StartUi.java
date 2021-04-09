package com.adidyk;

import com.adidyk.frame.AccountFrame;
import com.adidyk.model.AccountTableModel;

import javax.swing.*;

/**
 * Class StartUi. Start project.
 */
public class StartUi {

    private AccountPanel accountPanel;

    private StartUi(AccountPanel accountPanel) {
        this.accountPanel = accountPanel;
    }

    private void run() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            new AccountFrame(this.accountPanel);
        });
    }

     /**
     * main - main.
     * @param arg - arg.
     */
    public  static void main(String[] arg) {

        EmailValidator emailValidator = new EmailValidator();
        System.out.println("alex@yandex.ru: " + emailValidator.validate("alex@yandex.ru"));
        System.out.println("alex-27@yandex.com: " + emailValidator.validate("alex-27@yandex.com"));
        System.out.println("alex.27@yandex.com: " + emailValidator.validate("alex.27@yandex.com"));
        System.out.println("alex111@devcolibri.com:" + emailValidator.validate("alex111@devcolibri.com"));
        System.out.println("alex.100@devcolibri.com.ua: " + emailValidator.validate("alex.100@devcolibri.com.ua"));
        System.out.println("alex@1.com: " + emailValidator.validate("alex@1.com"));
        System.out.println("alex@gmail.com.com: " + emailValidator.validate("alex@gmail.com.com"));
        System.out.println("alex+27@gmail.com: " + emailValidator.validate("alex+27@gmail.com"));
        System.out.println("alex-27@yandex-test.com: " + emailValidator.validate("alex-27@yandex-test.com"));

        System.out.println();
        System.out.println("alex27@yandex+test.com: " + emailValidator.validate("alex27@yandex+test.com"));
        System.out.println("11111111: " + emailValidator.validate("11111111"));

        AccountPanel accountPanel = new AccountPanel(new AccountTableModel(new JsonParserAccount()));
        new StartUi(accountPanel).run();

    }

}