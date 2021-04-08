package com.adidyk;

import com.adidyk.model.Account;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;

public class JsonParserAccount {

    private Map<String, String> getMap(Account account) {
        Map<String, String> map = new HashMap<>();
        map.put("name", account.getName());
        map.put("email", account.getEmail());
        map.put("description", account.getDescription());
        return map;

    }

    public JSONObject getAccountJson(Account account) {
        Map<String, String> map = this.getMap(account);
        return new JSONObject(map);
    }

    public JSONArray getAccountJsonArray(List<Account> accounts) {
        JSONArray accountJsonArray = new JSONArray();
        for (Account account : accounts) {
            accountJsonArray.add(this.getAccountJson(account));
        }
        return accountJsonArray;
    }

    public void saveJsonToFile(List<Account> accounts) {
        try {
            FileWriter file = new FileWriter("test.json");
            file.write(this.getAccountJsonArray(accounts).toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Account> getJsonFromFile() {
        JSONParser parser = new JSONParser();
        ArrayList<Account> accounts = new ArrayList<>();
        try(FileReader reader = new FileReader("test.json")) {
            JSONArray array = (JSONArray) parser.parse(reader);
            for (Object o : array) {
                JSONObject obj = (JSONObject) o;
                accounts.add(new Account((String) obj.get("name"), (String) obj.get("email"), (String) obj.get("description")));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return accounts;

    }

}