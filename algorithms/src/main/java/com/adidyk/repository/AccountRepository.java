package com.adidyk.repository;

import com.adidyk.model.Account;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;

/**
 * Class AccountRepository used for save list account to file accounts.json in json format and
 * gets all accounts from fil.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 13.04.2021.
 * @version 1.0.
 */
public class AccountRepository implements IAccountRepository {

    /**
     * @param fileAccounts - file name in json format for save list accounts.
     */
    private final String fileAccounts = "accounts.json";

    /**
     * @param name - account name.
     */
    private final String name = "name";

    /**
     * @param email - account email.
     */
    private final String email = "email";

    /**
     * getMap - returns map.
     * @param account - account.
     * @return - returns map.
     */
    private Map<String, String> getMap(Account account) {
        Map<String, String> map = new HashMap<>();
        map.put(this.name, account.getName());
        map.put(this.email, account.getEmail());
        return map;
    }

    /**
     * getAccountJson - gets json object account.
     * @param account - account.
     * @return - returns json object account.
     */
    private JSONObject getAccountJson(Account account) {
        return new JSONObject(this.getMap(account));
    }

    /**
     * getAccountJsonArray - gets array json object account.
     * @param accounts - json array accounts.
     * @return - returns json array account.
     */
    private JSONArray getAccountJsonArray(List<Account> accounts) {
        JSONArray accountJsonArray = new JSONArray();
        for (Account account : accounts) {
            accountJsonArray.add(this.getAccountJson(account));
        }
        return accountJsonArray;
    }

    /**
     * saveAllAccount - save all accounts from table model account to file accounts.json.
     * @param accounts - list accounts from table model accounts.
     */
    @Override
    public void saveAllAccount(List<Account> accounts) {
        try {
            FileWriter file = new FileWriter(this.fileAccounts);
            file.write(this.getAccountJsonArray(accounts).toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getAllAccount - get all accounts from file accounts.json.
     * @return - returns list accounts from file accounts.json.
     */
    @Override
    public List<Account> getAllAccount() {
        JSONParser parser = new JSONParser();
        ArrayList<Account> accounts = new ArrayList<>();
        try(FileReader reader = new FileReader(this.fileAccounts)) {
            JSONArray array = (JSONArray) parser.parse(reader);
            for (Object o : array) {
                JSONObject obj = (JSONObject) o;
                accounts.add(new Account((String) obj.get(this.name), (String) obj.get(this.email)));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return accounts;
    }

}