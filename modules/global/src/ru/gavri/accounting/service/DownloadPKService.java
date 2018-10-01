package ru.gavri.accounting.service;

public interface DownloadPKService {
    String NAME = "accounting_DownloadPKService";

    void downloadPK(Object object);
}