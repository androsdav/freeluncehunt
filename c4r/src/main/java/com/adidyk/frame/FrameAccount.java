package com.adidyk.frame;

import com.adidyk.model.Account;
import com.adidyk.model.AccountTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FrameAccount extends JFrame {

   // private List<Account> accounts = new ArrayList<>();

    public FrameAccount() {

        super("Account");
        this.setSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        JButton addButton = new JButton("add");
        JTextField nameField = new JTextField(25);
        JTextField emailField = new JTextField(25);
        JTextField descriptionField = new JTextField(25);


        AccountTableModel model = new AccountTableModel();
        JTable accountTable = new JTable(model);
        JScrollPane accountTableScrollPage = new JScrollPane(accountTable);
        accountTableScrollPage.setPreferredSize(new Dimension(400, 400));

        this.add(accountTableScrollPage, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));


        this.add(nameField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(emailField, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(descriptionField, new GridBagConstraints(3, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));


        /*
        JTextField fieldName = new JTextField(25);
        JTextField fieldEmail = new JTextField(25);
        JTextField fieldDescription = new JTextField(25);

        JButton buttonSave = new JButton("save");

        JPanel contents = new JPanel();
        contents.add(new JScrollPane(accountTable));
        contents.add(fieldName);
        contents.add(fieldEmail);
        contents.add(buttonSave);
        contents.add(fieldDescription);
        this.setContentPane(contents);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addAccount(new Account(String.valueOf(fieldName.getText()), String.valueOf(fieldEmail.getText()), String.valueOf(fieldDescription.getText())));
                for (Component component : contents.getComponents()) {
                    if (component instanceof JTextField) {
                        JTextField field = (JTextField) component;
                        field.setText("");
                    }
                }
            }
        });

*/
        this.pack();
        this.setVisible(true);

    }

}
