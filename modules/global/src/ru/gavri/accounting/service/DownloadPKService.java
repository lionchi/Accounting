package ru.gavri.accounting.service;

import java.util.Scanner;

public interface DownloadPKService {
    String NAME = "accounting_DownloadPKService";

    Long downloadPK(String jsonStr);
}