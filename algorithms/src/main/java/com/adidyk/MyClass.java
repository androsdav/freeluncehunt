package com.adidyk;

public class MyClass implements IFirst, ISecond {

    @Override
    public String getString(String first, String second) {
        return first + second;
    }

    @Override
    public String getString(String first) {
        return first + "bla bla bla";
    }

}