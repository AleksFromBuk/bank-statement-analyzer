package com.t1.task4.shubookchapter02.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    //Вы можете «сделать его коротким и простым» (Keep It Short and Simple —
    //KISS), уместив все приложение в одном классе
    private static final String RESOURCES = "src/main/resources/";
    private static final String DEFAULT = "transactional.csv";

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCES + (args.length != 0 ? args[0] : DEFAULT));
        //final Path path = Paths.get(RESOURCES +  DEFAULT);
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        for (final String line: lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }

        System.out.println("The total for all transactions is " + total);
    }


}


/**
 * 30-01-2017,-100,Deliveroo
 * 30-01-2017,-50,Tesco
 * 01-02-2017,6000,Salary
 * 02-02-2017,2000,Royalties
 * 02-02-2017,-4000,Rent
 * 03-02-2017,3000,Tesco
 * 05-02-2017,-30,Cinema
 */