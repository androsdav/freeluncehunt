package com.adidyk;

import com.adidyk.model.Account;
import com.adidyk.model.AccountTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {

    private EmailValidator emailValidator = new EmailValidator();

    private AccountTableModel accountTableModel;

    private JTable accountTable;


    Action action = new AccountPanelAction();


    private JTextField nameField = new JTextField(20);
    private JTextField emailField = new JTextField(20);

    private JLabel nameLabel = new JLabel("name");
    private JLabel emailLabel = new JLabel("email");

    private JButton addButton = new JButton(action);

    public AccountPanel(AccountTableModel accountTableModel) {
        this.accountTableModel = accountTableModel;
        this.accountTable = new JTable(this.accountTableModel);
        this.setLayout(new GridBagLayout());
    }


    public void saveJsonToFile() {
        this.accountTableModel.saveJsonToFile();
    }

    public void init() {
        JScrollPane accountTableScrollPage = new JScrollPane(accountTable);
        accountTableScrollPage.setPreferredSize(new Dimension(400, 300));
        this.add(accountTableScrollPage, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        this.add(nameLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 1, 1, 1), 0, 0));
        this.add(nameField, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        this.add(emailLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 1, 1, 1), 0, 0));
        this.add(emailField, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        this.add(addButton, new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(30, 1, 1, 1), 0, 0));

        /*
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String email = emailField.getText();
                //System.out.println("email: " + email);
                if (emailValidator.validate(String.valueOf(emailField.getText()))) {
                    accountTableModel.addAccount(new Account(String.valueOf(nameField.getText()), String.valueOf(emailField.getText())));
                    for (Component component : getComponents()) {
                        if (component instanceof JTextField) {
                            JTextField field = (JTextField) component;
                            field.setText("");
                        }
                    }

                } else {
                    System.out.println("email invalid");
                    JOptionPane.showMessageDialog(AccountPanel.this,
                            "Inputted email invalid. Input correct email.");

                }
/*
                for (Component component : getComponents()) {
                    if (component instanceof JTextField) {
                        JTextField field = (JTextField) component;
                        field.setText("");
                    }
                }*/

        //  }
        //  });
    }

    class AccountPanelAction extends AbstractAction {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (emailValidator.validate(String.valueOf(emailField.getText()))) {
                accountTableModel.addAccount(new Account(String.valueOf(nameField.getText()), String.valueOf(emailField.getText())));
                for (Component component : getComponents()) {
                    if (component instanceof JTextField) {
                        JTextField field = (JTextField) component;
                        field.setText("");
                    }
                }
            } else {
                System.out.println("email invalid");
                JOptionPane.showMessageDialog(AccountPanel.this, "Inputted email invalid. Input correct email.");
            }


        }

    }

}