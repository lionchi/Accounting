package ru.gavri.accounting.service;

public interface DownloadPKService {
    String NAME = "accounting_DownloadPKService";

    Long downloadPK(String jsonStr);

    boolean checkLabel(String jsonStr, String format);
}