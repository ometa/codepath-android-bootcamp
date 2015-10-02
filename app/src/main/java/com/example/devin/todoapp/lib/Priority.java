package com.example.devin.todoapp.lib;

/**
 * I want to move the priority code to use an enum but I did
 * not get to that point for this demo.
 * Created by devin on 10/2/15.
 */
public enum Priority {

    HIGH (1, "High"),
    MEDIUM (2, "Medium"),
    LOW (3, "Low");

    int priority;
    String text;

    Priority(int priority, String text) {
        this.priority = priority;
        this.text = text;
    }

    public int getPriority() {
        return this.priority;
    }
    public String getText() {
        return this.text;
    }
}
