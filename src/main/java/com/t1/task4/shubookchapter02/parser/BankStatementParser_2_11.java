package com.t1.task4.shubookchapter02.parser;

import com.t1.task4.shubookchapter02.domain.BankTransaction;

import java.util.List;

public interface BankStatementParser_2_11 {
    BankTransaction parseFrom(final String line);
    List<BankTransaction> parseLinesFrom(final List<String> lines);
}