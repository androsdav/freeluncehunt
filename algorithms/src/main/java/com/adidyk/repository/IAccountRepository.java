package com.adidyk.repository;

import com.adidyk.model.Account;
import java.util.List;

/**
 * Interface IAccountRepository describes methods:  saveAllAccount - save all accounts to storage, getAllAccount
 * - get all accounts from storage.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 13.04.2021.
 * @version 1.0.
 */
public interface IAccountRepository {

    /**
     * saveAllAccount - save all accounts to storage.
     * @param accounts - list accounts from table model accounts.
     */
    void saveAllAccount(List<Account> accounts);

    /**
     * getAllAccount - get all accounts from storage.
     * @return - returns list accounts from storage.
     */
    List<Account> getAllAccount();

}