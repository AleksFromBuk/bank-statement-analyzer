package com.t1.task4.shubookchapter02.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class BankTransaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private  LocalDate date;
    private  double amount;
    private  String description;

    public BankTransaction(final LocalDate date, final double amount, final String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BankTransaction{"
                + "date=" + date
                + ", amount=" + amount
                + ", description='" + description + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return Double.compare(that.amount, amount) == 0
                && date.equals(that.date) && description.equals(that.description);

    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description);
    }
}
