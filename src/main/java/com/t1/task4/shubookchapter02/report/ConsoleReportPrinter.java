package com.t1.task4.shubookchapter02.report;

import com.t1.task4.shubookchapter02.util.BankStatementProcessor;

import java.util.Map;

public class ConsoleReportPrinter implements ReportPrinter {

    @Override
    public void printSummary(BankStatementProcessor processor, String... args) {
        System.out.println("The total for all transactions is " + processor.calculateTotalAmount());

        final String date = args.length > 1 ? args[1] : "JANUARY";
        System.out.println("The total for transactions in " + date + " is " + processor.calculateTotalInMonth(date));
        System.out.println("The total for transactions in February is " + processor.calculateTotalInMonth("February"));
        System.out.println("The total salary received is " + processor.calculateTotalForCategory("Salary"));
        processor.findMostExpensiveTransaction().ifPresent(tx ->
                System.out.println("The most expensive transaction is " + tx)
        );
        processor.findLeastExpensiveTransaction().ifPresent(tx ->
                System.out.println("The least expensive transaction is " + tx)
        );
    }

    @Override
    public void printHistogram(Map<?, Double> histogram, String title) {
        System.out.println(title);
        for (Map.Entry<?, Double> entry : histogram.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
