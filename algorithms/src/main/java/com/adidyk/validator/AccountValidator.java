package com.adidyk.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class AccountRepository used for save list account to file accounts.json in json format and
 * gets all accounts from fil.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 13.04.2021.
 * @version 1.0.
 */
public class AccountValidator {

    /**
     * @param NAME_PATTERN - regex, only Latin letters and no more than 4 words
     */
    private static final String NAME_PATTERN = "[A-Za-z]+(\\s*[A-Za-z]+){3}$";

    /**
     * @param EMAIL_PATTERN - regex email.
     */
    private static final String EMAIL_PATTERN = "^[A-Za-z]+[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * @param namePattern - name pattern.
     */
    private Pattern namePattern;

    /**
     * @param emailPattern - email pattern.
     */
    private Pattern emailPattern;

    /**
     * @param matcher - matcher.
     */
    private Matcher matcher;

    /**
     * AccountValidator - constructor, init params.
     */
    public AccountValidator() {
        this.emailPattern = Pattern.compile(EMAIL_PATTERN);
        this.namePattern = Pattern.compile(NAME_PATTERN);
    }

    /**
     * validateName - validate name account and returns true if inputted account name is valid and returns false
     * if inputted account name is invalid.
     * @param accountName - account name.
     * @return - returns true if inputted account name is valid and returns false if inputted account name
     * is invalid.
     */
    public boolean validateName(String accountName) {
        this.matcher = namePattern.matcher(accountName);
        return this.matcher.matches();
    }

    /**
     * validateEmail - validate email account and returns true if inputted account email is valid and returns false
     * if inputted email is invalid.
     * @param accountEmail - account email.
     * @return - returns true if inputted account email is valid and returns false if inputted email is invalid.
     */
    public boolean validateEmail(String accountEmail) {
        this.matcher = emailPattern.matcher(accountEmail);
        return this.matcher.matches();
    }

}