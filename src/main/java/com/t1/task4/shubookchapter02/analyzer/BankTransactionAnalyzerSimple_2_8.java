package com.t1.task4.shubookchapter02.analyzer;

import com.t1.task4.shubookchapter02.domain.BankTransaction;
import com.t1.task4.shubookchapter02.parser.BankStatementCSVParser;
import com.t1.task4.shubookchapter02.util.BankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple_2_8 {
    private static final String RESOURCES = "src/main/resources/";
    private static final String DEFAULT_RESOURCES = "transactional.csv";
    private static final String TARGET_DATE = "JANUARY";
    private static final BankStatementCSVParser parser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCES + (args.length != 0 ? args[0] : DEFAULT_RESOURCES));
        final List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = parser.parseLinesFrom(lines);
        final BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);
        collectSummary(processor);
    }

    private static void collectSummary(BankStatementProcessor processor, String...args) {
        System.out.println("The total for all transactions is "
        + processor.calculateTotalAmount());

        final String date = args.length > 1 ? args[1] : TARGET_DATE;

        System.out.println("The total for transactions in "
                + date + " is " + processor.calculateTotalInMonth(date));

        System.out.println("The total for transactions in February is "
        + processor.calculateTotalInMonth("February"));

        System.out.println("The total salary received is "
        + processor.calculateTotalForCategory("Salary"));
    }

}
