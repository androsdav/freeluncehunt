package com.adidyk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountValidator {

    private Pattern patternName;
    private Pattern patternEmail;
    private Matcher matcher;
    private static final String NAME_PATTERN =
            "(^[A-Za-z\\s]*$)|" +
            "(^[A-Za-z\\s]*\\s[A-Za-z\\s]*$)|" +
            "(^[A-Za-z\\s]*\\s[A-Za-z\\s]*\\s[A-Za-z\\s]*$)|" +
            "(^[A-Za-z\\s]*\\s[A-Za-z\\s]*\\s[A-Za-z\\s]*\\s[A-Za-z\\s]*$)";

    private static final String EMAIL_PATTERN = "^[A-Za-z]+[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public AccountValidator() {
        this.patternEmail = Pattern.compile(EMAIL_PATTERN);
        this.patternName = Pattern.compile(NAME_PATTERN);
    }


    public boolean validateName(String string) {
        this.matcher = patternName.matcher(string);
        return this.matcher.matches();
    }

    public boolean validateEmail(String string) {
        this.matcher = patternEmail.matcher(string);
        return this.matcher.matches();
    }



}