package com.t1.task4.shubookchapter02.util;

import com.t1.task4.shubookchapter02.domain.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction bankTransaction);
}
