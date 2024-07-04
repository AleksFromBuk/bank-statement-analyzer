package com.t1.task4.shubookchapter02.export;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t1.task4.shubookchapter02.domain.BankTransaction;

import java.util.List;

public class JSONExportFormat implements ExportFormat {
    @Override
    public String export(List<BankTransaction> transactions) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(transactions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
