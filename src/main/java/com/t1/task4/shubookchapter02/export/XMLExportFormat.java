package com.t1.task4.shubookchapter02.export;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t1.task4.shubookchapter02.domain.BankTransaction;

import java.io.IOException;
import java.util.List;

public class XMLExportFormat implements ExportFormat {
    @Override
    public String export(List<BankTransaction> transactions) {
        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(transactions);
        } catch (IOException e) {
            throw new RuntimeException("Error exporting to XML", e);
        }
    }
}
