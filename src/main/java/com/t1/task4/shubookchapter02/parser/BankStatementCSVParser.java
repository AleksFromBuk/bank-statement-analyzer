package com.t1.task4.shubookchapter02.parser;

import com.t1.task4.shubookchapter02.domain.BankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Когда вы программируете, ваша задача — постараться сделать код хорошо
 * обслуживаемым. Что это значит? Лучше всего объяснить это при помощи
 * списка свойств, которыми должен обладать ваш код.
 *
 * • Должно быть легко находить участок кода, отвечающий за определенную
 * функцию.
 *
 * • Должно быть понятно, как код работает.
 *
 * • Должно быть просто убрать или добавить новую опцию.
 *
 * • Должна обеспечиваться инкапсуляция. Другими словами, реализация
 * деталей должна быть скрыта от пользователя таким образом, чтобы ему
 * было легко разобраться и внести изменения.
 *
 * Хороший способ оценить эффективность кода — это представить, что вы
 * ушли в другую компанию, предоставив разбираться с вашим кодом коллеге.
 *
 * Вернемся к нашему примеру. В первую очередь необходимо создать отдельный
 * класс, предназначенный для извлечения данных из CSV-файла. Таким
 * образом, вы сможете многократно использовать его в различных задачах.
 * Давайте назовем его BankStatementCSVParser, чтобы по названию сразу понимать
 * его предназначение
 */
public class BankStatementCSVParser implements BankStatementParser_2_11 {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankTransaction parseFrom(final String line) {
        final String[] columns = line.split(",");
        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];
        return new BankTransaction(date, amount, description);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(final List<String> lines) {
        final List<BankTransaction> transactions = new ArrayList<>();
        for (final String line: lines) {
            transactions.add(parseFrom(line));
        }
        return transactions;
    }
}
