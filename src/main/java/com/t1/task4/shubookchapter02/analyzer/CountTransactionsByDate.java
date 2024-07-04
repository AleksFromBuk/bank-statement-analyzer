package com.t1.task4.shubookchapter02.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * плохой подход к реализации - применяется копипаста ранее написанного кода;
 *
 * Ваша первоочередная задача — контролировать сложность приложения,
 * которое вы создаете. При этом, если вы будете продолжать «копипастить»
 * одни и те же участки кода по мере добавления новых возможностей, то
 * в конце концов, вы столкнетесь со следующими проблемами, которые называются
 * антишаблонами, или антипаттернами, потому что являются
 * наиболее неэффективными решениями:
 *
 * • Тяжелый для понимания код, потому что у вас есть один огромный
 * «класс-бог».
 *
 * • Хрупкий код, который разваливается от любых изменений из-за многочисленного
 * дублирования.
 *
 * Поместив весь свой код в одном файле, вы придете к одному огромному классу,
 * с которым невероятно сложно разобраться, потому что он делает абсолютно
 * все! Если вам понадобится обновить логику существующего кода (например,
 * изменить алгоритм синтаксического анализа — парсинга — слов),
 * вы не сможете оперативно найти нужный участок кода и внести изменения.
 * Данная проблема вызвана использованием антишаблона под названием
 * «класс-бог». По сути, у вас есть класс, который делает все. Такой практики,
 * безусловно, следует избегать. В следующем разделе вы познакомитесь с принципом
 * единственной ответственности, который является «проводником»
 * в разработке простого для понимания и обслуживаемого кода.
 *
 * Дублирование кода
 * Для каждой задачи вы дублируете алгоритм чтения и анализа входных данных.
 * А что если формат входных данных изменится с CSV на JSON? Что
 * если понадобится поддержка нескольких форматов? Добавление этой опции
 * станет сущим адом, так как ваш код перегружен одним и тем же решением,
 * продублированным много раз в разных местах. Таким образом, вам придется
 * вносить изменения везде, повышая вероятность возникновения багов.
 *
 * Bbi будете часто слышать о принципе «Не повторяйся» (DRY — Don’t
 * Repeat Yourself). Его идея заключается в том, что, если успешно избегать
 * дублирования кода, внесение изменений в логику приложения не потребует
 * многочисленных изменений в коде.
 */
public class CountTransactionsByDate {
    private static final String RESOURCES = "src/main/resources/";
    private static final String DEFAULT_RESOURCES = "transactional.csv";
    private static final String TARGET_DATE = "JANUARY";

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCES + (args.length != 0 ? args[0] : DEFAULT_RESOURCES));
        final List<String> lines = Files.readAllLines(path);
        double total = 0;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (final String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if ((args.length == 2 ? args[1] : TARGET_DATE).equals(date.getMonth().toString())) {
                total += Double.parseDouble(columns[1]);
            }
        }
        System.out.println("The total for all transactions in January is " + total);
    }

}
