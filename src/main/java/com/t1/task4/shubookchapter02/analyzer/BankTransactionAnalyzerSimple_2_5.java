package com.t1.task4.shubookchapter02.analyzer;

import com.t1.task4.shubookchapter02.domain.BankTransaction;
import com.t1.task4.shubookchapter02.parser.BankStatementCSVParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple_2_5 {
    private static final String RESOURCES = "src/main/resources/";
    private static final String DEFAULT_RESOURCES = "transactional.csv";
    private static final String TARGET_DATE = "JANUARY";

    public static void main(String[] args) throws IOException {
        final BankStatementCSVParser bsp = new BankStatementCSVParser();
        final Path path = Paths.get(RESOURCES + (args.length != 0 ? args[0] : DEFAULT_RESOURCES));
        final List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = bsp.parseLinesFrom(lines);

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions is January " + selectInMonth(bankTransactions, args.length > 1 ? args[1] : TARGET_DATE));
    }

    private static List<BankTransaction> selectInMonth(List<BankTransaction> bankTransactions, String s) {
        return bankTransactions.stream()
                .filter(bts -> bts.getDate().getMonth().toString().equals(s))
                .toList();
    }

    private static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        return bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }
}
