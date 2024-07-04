package com.t1.task4.shubookchapter02.util;

import com.t1.task4.shubookchapter02.domain.BankTransaction;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalInMonth(final String month) {
        return summarizeTransactions(
                (acc, bankTransaction) -> bankTransaction.getDate().getMonth().toString().equalsIgnoreCase(month) ? acc
                        + bankTransaction.getAmount() : acc
        );
    }

    public double calculateTotalAmount() {
        return summarizeTransactions(
                (acc, bankTransaction) -> acc + bankTransaction.getAmount()
        );
    }

    public double calculateTotalForCategory(final String category) {
        return summarizeTransactions(
                (acc, bankTransactions) -> bankTransactions.getDescription().equalsIgnoreCase(category)
                ? acc + bankTransactions.getAmount() : acc
        );
    }

    public Optional<BankTransaction> findMostExpensiveTransaction() {
        return bankTransactions.stream().max(Comparator.comparingDouble(BankTransaction::getAmount));
    }

    public Optional<BankTransaction> findLeastExpensiveTransaction() {
        return bankTransactions.stream().min(Comparator.comparingDouble(BankTransaction::getAmount));
    }

    // глава 3
    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        return bankTransactions.stream()
                .filter(bankTransactionFilter::test)
                .toList();
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}
