package ru.gavri.accounting.exceptions;

public class DownloadJsonFileException extends RuntimeException {
    public DownloadJsonFileException() {
    }

    public DownloadJsonFileException(String message) {
        super(message);
    }
}
