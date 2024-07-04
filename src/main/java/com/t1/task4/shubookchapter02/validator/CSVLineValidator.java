package com.t1.task4.shubookchapter02.validator;

import com.t1.task4.shubookchapter02.domain.Notification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CSVLineValidator implements DataParseValidator {
    @Override
    public Notification validate(String line) {
        final Notification notification = new Notification();
        final String[] columns = line.split(",");

        if (columns.length != 3) {
            notification.addError("Invalid number of columns: " + columns.length);
            return notification;
        }

        try {
            final LocalDate parseDate = LocalDate.parse(columns[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (parseDate.isAfter(LocalDate.now())) {
                notification.addError("date cannot be in the future: " + parseDate);
            }
        } catch (DateTimeParseException e) {
            notification.addError("Invalid date format: " + columns[0]);
        }

        try {
            double amount = Double.parseDouble(columns[1]);
        } catch (NumberFormatException e) {
            notification.addError("Invalid amount format: " + columns[1]);
        }

        if (columns[2].length() > 100) {
            notification.addError("Description too long: " + columns[2].length());
        }
        return notification;
    }
}
