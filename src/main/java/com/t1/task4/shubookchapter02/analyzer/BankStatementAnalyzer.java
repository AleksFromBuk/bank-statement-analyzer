package com.t1.task4.shubookchapter02.analyzer;

import com.t1.task4.shubookchapter02.domain.BankTransaction;
import com.t1.task4.shubookchapter02.domain.Notification;
import com.t1.task4.shubookchapter02.parser.BankStatementParser_2_11;
import com.t1.task4.shubookchapter02.validator.DataParseValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BankStatementAnalyzer {
    public List<BankTransaction> analyze(final String fileName, final BankStatementParser_2_11 bankStatementParser, final DataParseValidator dataParseValidator) throws IOException {
        final Path path = Paths.get(fileName);
        final List<String> dataBeforeValidate = Files.readAllLines(path);
        final List<String> dataAfterValidate = new ArrayList<>();
        for (final String line : dataBeforeValidate) {
            Notification validateResult = dataParseValidator.validate(line);
            if (validateResult.hasErrors()) {
                System.out.println("Data " + line + ": has errors: " + validateResult.getErrors());
                continue;
            }
            dataAfterValidate.add(line);
        }
        return bankStatementParser.parseLinesFrom(dataAfterValidate);
    }
}
