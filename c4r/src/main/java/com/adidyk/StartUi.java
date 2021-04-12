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

        AccountValidator emailValidator = new AccountValidator();
        System.out.println("alex@yandex.ru: " + emailValidator.validateEmail("alex@yandex.ru"));
        System.out.println("alex-27@yandex.com: " + emailValidator.validateEmail("alex-27@yandex.com"));
        System.out.println("alex.27@yandex.com: " + emailValidator.validateEmail("alex.27@yandex.com"));
        System.out.println("alex111@devcolibri.com:" + emailValidator.validateEmail("alex111@devcolibri.com"));
        System.out.println("alex.100@devcolibri.com.ua: " + emailValidator.validateEmail("alex.100@devcolibri.com.ua"));
        System.out.println("alex@1.com: " + emailValidator.validateEmail("alex@1.com"));
        System.out.println("alex@gmail.com.com: " + emailValidator.validateEmail("alex@gmail.com.com"));
        System.out.println("alex+27@gmail.com: " + emailValidator.validateEmail("alex+27@gmail.com"));
        System.out.println("alex-27@yandex-test.com: " + emailValidator.validateEmail("alex-27@yandex-test.com"));

        System.out.println();
        System.out.println("alex27@yandex+test.com: " + emailValidator.validateEmail("alex27@yandex+test.com"));
        System.out.println();


        System.out.println("one: " + emailValidator.validateName("firAst"));
        System.out.println(" one: " + emailValidator.validateName(" AfiArstA"));
        System.out.println("one : " + emailValidator.validateName("AfirstA "));
        System.out.println(" one : " + emailValidator.validateName(" firstA "));
        System.out.println(" one : " + emailValidator.validateName(""));
        System.out.println();
        System.out.println("one two: " + emailValidator.validateName("first second"));
        System.out.println(" one two: " + emailValidator.validateName(" first second"));
        System.out.println("one two : " + emailValidator.validateName("first second "));
        System.out.println(" one two : " + emailValidator.validateName(" first second "));
        System.out.println();
        System.out.println("one two three: " + emailValidator.validateName("first second three"));
        System.out.println(" one two three: " + emailValidator.validateName(" first second three"));
        System.out.println("one two three : " + emailValidator.validateName("first second three "));
        System.out.println(" one two three : " + emailValidator.validateName(" first second three "));
        System.out.println();
        System.out.println("one two three foo: " + emailValidator.validateName("first second three foo"));
        System.out.println(" one two three foo: " + emailValidator.validateName(" first second three foo"));
        System.out.println("one two three foo : " + emailValidator.validateName("first second three foo "));
        System.out.println(" one two three : " + emailValidator.validateName(" first second three foo "));
        System.out.println();
        System.out.println("one two three foo fifty: " + emailValidator.validateName("first second three foo fifty"));
        System.out.println(" one two three foo fifty: " + emailValidator.validateName(" first second three foo fifty"));
        System.out.println("one two three foo fifty : " + emailValidator.validateName("first second three foo fifty "));
        System.out.println(" one two three foo fifty : " + emailValidator.validateName(" first second three foo fifty "));

        AccountPanel accountPanel = new AccountPanel(new AccountTableModel(new JsonParserAccount()));
        new StartUi(accountPanel).run();

    }

}