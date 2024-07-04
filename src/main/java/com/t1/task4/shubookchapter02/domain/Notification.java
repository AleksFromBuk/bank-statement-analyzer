package com.t1.task4.shubookchapter02.domain;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private final List<String> errors = new ArrayList<String>();

    public void addError(final String message) {
        errors.add(message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String errorMessage() {
        return String.join(", ", errors);
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
}
