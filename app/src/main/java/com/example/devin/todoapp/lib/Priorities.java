package com.example.devin.todoapp.lib;

/**
 * Created by devin on 10/2/15.
 */
public class Priorities {
    public static final int LOW = 0;
    public static final int MEDIUM = 1;
    public static final int HIGH = 2;
    public static final int DEFAULT = LOW;

    public static final String[] textValues = {"Low", "Medium", "High"};

    public static String getText(int priority) {
        try {
            return textValues[priority];
        } catch (Exception ex) {
            return "Invalid";
        }
    }
}
