package com.t1.task4.shubookchapter02;

import com.t1.task4.shubookchapter02.analyzer.BankStatementAnalyzer;
import com.t1.task4.shubookchapter02.domain.BankTransaction;
import com.t1.task4.shubookchapter02.export.ExportFormat;
import com.t1.task4.shubookchapter02.export.JSONExportFormat;
import com.t1.task4.shubookchapter02.export.XMLExportFormat;
import com.t1.task4.shubookchapter02.parser.BankStatementCSVParser;
import com.t1.task4.shubookchapter02.validator.CSVLineValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML
    private Button loadButton;

    @FXML
    private Button exportJSONButton;

    @FXML
    private Button exportXMLButton;

    @FXML
    private TextArea textArea;

    private List<BankTransaction> transactions;

    @FXML
    public void loadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BankStatementAnalyzer analyzer = new BankStatementAnalyzer();
                transactions = analyzer.analyze(file.getAbsolutePath(), new BankStatementCSVParser(),new CSVLineValidator());
                textArea.setText("Transactions loaded successfully.");
            } catch (IOException e) {
                showError("Error loading file: " + e.getMessage());
            }
        }
    }

    @FXML
    public void exportToJSON(ActionEvent event) {
        if (transactions == null || transactions.isEmpty()) {
            showError("No transactions to export. Please load a file first.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                ExportFormat jsonExport = new JSONExportFormat();
                writer.write(jsonExport.export(transactions));
                textArea.setText("Transactions exported to JSON successfully.");
            } catch (IOException e) {
                showError("Error exporting to JSON: " + e.getMessage());
            }
        }
    }

    @FXML
    public void exportToXML(ActionEvent event) {
        if (transactions == null || transactions.isEmpty()) {
            showError("No transactions to export. Please load a file first.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                ExportFormat xmlExport = new XMLExportFormat();
                writer.write(xmlExport.export(transactions));
                textArea.setText("Transactions exported to XML successfully.");
            } catch (IOException e) {
                showError("Error exporting to XML: " + e.getMessage());
            }
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
