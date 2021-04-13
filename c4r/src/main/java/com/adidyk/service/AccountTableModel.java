package com.adidyk.service;

import com.adidyk.model.Account;
import com.adidyk.repository.AccountRepository;
import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AccountTableModel used for creates account table models.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 03.04.2021.
 * @version 1.0.
 */
public class AccountTableModel extends AbstractTableModel {

    /**
     * @param accounts - list accounts.
     */
    private List<Account> accounts = new ArrayList<>();

    /**
     * @param fields - all fields in class Account.
     */
    private Field[] fields = Account.class.getDeclaredFields();

    /**
     * @param accountRepository - account repository.
     */
    private AccountRepository accountRepository;

    /**
     * AccountTableModel - constructor.
     *
     * @param accountRepository - account repository.
     */
    public AccountTableModel(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.getAllAccount();
    }

    /**
     * addAccount - adds account to list accounts and make fire table data change.
     * @param account - accounts.
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
        this.fireTableDataChanged();
    }

    /**
     * saveAllAccount - save all accounts from table model account to file accounts.json.
     */
    public void saveAllAccount() {
        this.accountRepository.saveAllAccount(this.accounts);
    }

    /**
     * getAllAccount - get all accounts from file accounts.json and adds in list accounts.
     */
    private void getAllAccount() {
        this.accounts = this.accountRepository.getAllAccount();
    }

    /**
     * getRowCount - returns the number of rows in the model. A JTable uses this method to determine how many rows it
     * should display. This method should be quick, as it is called frequently during rendering.
     *
     * @return - returns the number of rows in the model.
     */
    @Override
    public int getRowCount() {
        return this.accounts.size();
    }

    /**
     * getColumnCount - returns the number of columns in the model. A JTable uses this method to determine how
     * many columns it should create and display by default.
     *
     * @return - returns the number of columns in the model.
     */
    @Override
    public int getColumnCount() {
        return this.fields.length;
    }

    /**
     * getColumnName - returns the name of the column at columnIndex. This is used to initialize the table's
     * column header name.  Note: this name does not need to be unique; two columns in a table can have the same name.
     *
     * @param columnIndex - the index of the column.
     * @return - returns the name of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return this.fields[columnIndex].getName();
    }

    /**
     * getValueAt - returns the value for the cell at columnIndex and rowIndex.
     * @param rowIndex    - the row whose value is to be queried.
     * @param columnIndex - the column whose value is to be queried.
     * @return returns - the value Object at the specified cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Account account = this.accounts.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return account.getName();
            case 1:
                return account.getEmail();
        }
        return "";
    }

    /**
     * getColumnClass - returns String.class.
     * @param columnIndex -  the column being queried.
     * @return - returns the String.class.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

}