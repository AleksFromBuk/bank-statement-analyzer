package com.t1.task4.shubookchapter02.datapresent;

import com.t1.task4.shubookchapter02.domain.BankTransaction;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseHistogram {
    public static Map<Month, Double> groupByMonth(List<BankTransaction> transactions) {
        return transactions.stream().collect(
                Collectors.groupingBy(
                        t -> t.getDate().getMonth(), Collectors.summingDouble(BankTransaction::getAmount)
                ));
    }

    public static Map<String, Double> groupByDescription(List<BankTransaction> transactions) {
        return transactions.stream().collect(
                Collectors.groupingBy(
                        BankTransaction::getDescription,
                        Collectors.summingDouble(BankTransaction::getAmount))
        );
    }
}
