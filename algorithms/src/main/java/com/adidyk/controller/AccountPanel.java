package com.adidyk.controller;

import com.adidyk.model.Account;
import com.adidyk.service.AccountTableModel;
import com.adidyk.validator.AccountValidator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Class AccountPanel used to add all the items to the panel, shows this panel and implement control.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 03.04.2021.
 * @version 1.0.
 */
public class AccountPanel extends JPanel {

    /**
     * @param ADD_BUTTON_NAME - add button name.
     */
    private final static String ADD_BUTTON_NAME = "addButton";

    /**
     * @param ADD_BUTTON_TEXT - add button text.
     */
    private final static String ADD_BUTTON_TEXT = "add";

    /**
     * @param accountValidator - account validator.
     */
    private AccountValidator accountValidator = new AccountValidator();

    /**
     * @param action - action.
     */
    private Action action = new AccountPanelAction();

    /**
     * @param nameFiled - name field.
     */
    private JTextField nameField;

    /**
     * @param emailField - email field.
     */
    private JTextField emailField;

    /**
     * @param nameLabel - name label.
     */
    private JLabel nameLabel;

    /**
     * @param emailLabel - email label.
     */
    private JLabel emailLabel;

    /**
     * @param addButton - add button.
     */
    private JButton addButton;

    /**
     * @param accountTableModel - account table model.
     */
    private AccountTableModel accountTableModel;

    /**
     * @param accountTable - account table.
     */
    private JTable accountTable;

    /**
     * AccountPanel - constructor.
     * @param accountTableModel - account table model.
     */
    public AccountPanel(AccountTableModel accountTableModel) {
        this.accountTableModel = accountTableModel;
        this.setLayout(new GridBagLayout());
        this.init();
    }

    /**
     * init - initialization all items.
     */
    private void init() {
        this.initJTable();
        this.initJLabelJField();
        this.initButton();
        this.initAddItemToJPanel();
    }

    /**
     * initJTable - initialization table.
     */
    private void initJTable() {
        this.accountTable = new JTable(this.accountTableModel);
    }

    /**
     * initJLabelJField - initialization label field.
     */
    private void initJLabelJField() {
        this.nameField = new JTextField(25);
        this.emailField = new JTextField(25);
        this.nameLabel = new JLabel("name");
        this.emailLabel = new JLabel("email");
    }

    /**
     * initButton - initialization button.
     */
    private void initButton() {
        this.addButton = new JButton(this.action);
        this.addButton.setName(ADD_BUTTON_NAME);
        this.addButton.setText(ADD_BUTTON_TEXT);
    }

    /**
     * initAddItemToJPane - adds all items to panel.
     */
    private void initAddItemToJPanel() {
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
    }

    /**
     * saveAllAccount - save all accounts from table model account to file accounts.json.
     */
    void saveAllAccount() {
        this.accountTableModel.saveAllAccount();
    }

    /**
     * Class AccountPanelAction used for adds inputted params to account table model.
     * @author Didyk Andrey (androsdav@gmail.com).
     * @since 03.04.2021.
     * @version 1.0.
     */
    private class AccountPanelAction extends AbstractAction {

        /**
         * actionPerformed  - invoked when an action occurs.
         * @param event - action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton jButton = (JButton) event.getSource();
            if (jButton.getName().equals(ADD_BUTTON_NAME)) {
                this.addAccount();
            }
        }

        /**
         * addAccount - checks inputted name and email by validate regex, if checks is true adds inputted name and
         * email to account table model, if check is false show message dialog.
         */
        private void addAccount() {
            if (accountValidator.validateName(String.valueOf(nameField.getText()))) {
                if (accountValidator.validateEmail(String.valueOf(emailField.getText()))) {
                    accountTableModel.addAccount(new Account(String.valueOf(nameField.getText()), String.valueOf(emailField.getText())));
                    this.clearFields();
                } else {
                    JOptionPane.showMessageDialog(AccountPanel.this, "Inputted email invalid. Input correct email.");
                }
            } else {
                JOptionPane.showMessageDialog(AccountPanel.this, "Inputted name invalid. Input correct name.");
            }
        }

        /**
         * clearFields - clears all fields in panel.
         */
        private void clearFields() {
            for (Component component : getComponents()) {
                if (component instanceof JTextField) {
                    JTextField field = (JTextField) component;
                    field.setText("");
                }
            }
        }

    }

}