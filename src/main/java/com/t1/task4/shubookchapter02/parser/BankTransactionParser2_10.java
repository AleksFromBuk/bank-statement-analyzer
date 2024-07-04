package com.t1.task4.shubookchapter02.parser;

import com.t1.task4.shubookchapter02.domain.BankTransaction;

/**
 * Пример 2-10. Пример логической связности
 */
public class BankTransactionParser2_10 {

    public BankTransaction parserFromCSV(final String line) {
        //...
        throw new UnsupportedOperationException();
    }

    public BankTransaction parserFromJSON(final String line) {
        //...
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromXML(final String line) {
        //...
        throw new UnsupportedOperationException();
    }

    /*
    На самом деле данные методы логически относятся к категории «парсинга».
    При этом по своей сути они разные и мало связаны друг с другом. Их группировка
    также противоречит принципу SRP, о котором мы говорили ранее,
    потому что класс берет на себя ответственность за несколько различных задач.
    Из этого следует, что данный подход не является желательным.
    Из раздела «Связанность» вы узнаете, что существуют способы решения
    проблемы реализации различных способов парсинга, сохранив при этом
    высокую связность.
    */
}
