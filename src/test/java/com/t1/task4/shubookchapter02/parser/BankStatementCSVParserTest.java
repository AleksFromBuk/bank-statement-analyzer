package com.t1.task4.shubookchapter02.parser;

import com.t1.task4.shubookchapter02.domain.BankTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BankStatementCSVParserTest {
    private final BankStatementParser_2_11 bankStatementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() {
        final String line = "30-01-2017, -50,Tesco";
        final BankTransaction result = bankStatementParser.parseFrom(line);
        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void test_correctly_parses_valid_csv_line() {
        BankStatementCSVParser parser = new BankStatementCSVParser();
        String csvLine = "12-01-2023,1000.50,Salary";
        BankTransaction expectedTransaction = new BankTransaction(
                LocalDate.of(2023, 1, 12),
                1000.50,
                "Salary"
        );
        BankTransaction result = parser.parseFrom(csvLine);
        assertEquals(expectedTransaction, result);
    }
}