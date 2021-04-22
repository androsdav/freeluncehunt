package com.adidyk;

import com.adidyk.controller.AccountPanel;
import com.adidyk.controller.AccountFrame;
import com.adidyk.service.AccountTableModel;
import com.adidyk.repository.AccountRepository;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

        IFirst first = new MyClass();
        String string = first.getString("dsdsdsd");
        System.out.println("Result: " + string);

        ISecond second = new MyClass();
        String string1 = ((MyClass) second).getString("test");
        System.out.println("Result: " + string1);

        /*
        MyClass myClass = new MyClass();
        String string2 = myClass.getString("third ", "word");
        System.out.println("Result: " + string2);
        */

        Duplicate dublicate = new Duplicate();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(5);
        //dublicate.removeDuplicate(list);

        for (Integer item : dublicate.removeDuplicate(list)) {
            System.out.println("bla bla: " + item);

        }

        AccountPanel accountPanel = new AccountPanel(new AccountTableModel(new AccountRepository()));
        new StartUi(accountPanel).run();
    }

}