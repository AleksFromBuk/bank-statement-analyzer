package com.t1.task4.shubookchapter02.report;

import com.t1.task4.shubookchapter02.util.BankStatementProcessor;

import java.util.Map;

public interface ReportPrinter {
    void printSummary(BankStatementProcessor processor, String...args);
    void printHistogram(Map<?, Double> histogram, String title);
}
