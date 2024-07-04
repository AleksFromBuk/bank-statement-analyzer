package com.t1.task4.shubookchapter02;

import com.t1.task4.shubookchapter02.analyzer.BankStatementAnalyzer;
import com.t1.task4.shubookchapter02.datapresent.ExpenseHistogram;
import com.t1.task4.shubookchapter02.domain.BankTransaction;
import com.t1.task4.shubookchapter02.parser.BankStatementCSVParser;
import com.t1.task4.shubookchapter02.parser.BankStatementParser_2_11;
import com.t1.task4.shubookchapter02.report.ConsoleReportPrinter;
import com.t1.task4.shubookchapter02.report.ReportPrinter;
import com.t1.task4.shubookchapter02.util.BankStatementProcessor;
import com.t1.task4.shubookchapter02.validator.CSVLineValidator;
import com.t1.task4.shubookchapter02.validator.DataParseValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication(proxyBeanMethods = false)
public class MainApplication extends Application {
    private static final String RESOURCES = "src/main/resources/";
    private static final String DEFAULT_RESOURCES = "transactional.csv";

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Bank Statement Analyzer");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        analyzeBankStatements();
    }

    private void analyzeBankStatements() throws IOException {
        final ReportPrinter reportPrinter = new ConsoleReportPrinter();
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser_2_11 bankStatementParser = new BankStatementCSVParser();
        final DataParseValidator validator = new CSVLineValidator();
        List<BankTransaction> bankTransactionList = bankStatementAnalyzer.analyze(
                RESOURCES + (getParameters().getRaw().isEmpty() ? DEFAULT_RESOURCES : getParameters().getRaw().get(0)),
                bankStatementParser,
                validator
        );
        BankStatementProcessor processor = new BankStatementProcessor(bankTransactionList);
        reportPrinter.printSummary(processor);
        reportPrinter.printHistogram(
                ExpenseHistogram.groupByMonth(bankTransactionList), "Expenses grouped by month: "
        );
        reportPrinter.printHistogram(
                ExpenseHistogram.groupByDescription(bankTransactionList), "Expenses grouped by description: "
        );
    }
}
