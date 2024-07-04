package com.t1.task4.shubookchapter02.export;

import com.t1.task4.shubookchapter02.domain.BankTransaction;

import java.io.IOException;
import java.util.List;

public interface ExportFormat {
    String export(List<BankTransaction> transactions);
}
