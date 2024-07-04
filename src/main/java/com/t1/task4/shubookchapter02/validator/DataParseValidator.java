package com.t1.task4.shubookchapter02.validator;

import com.t1.task4.shubookchapter02.domain.Notification;

public interface DataParseValidator {
    Notification validate(String line);
}
