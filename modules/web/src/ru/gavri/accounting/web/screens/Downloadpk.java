package ru.gavri.accounting.web.screens;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.FileUploadField;
import ru.gavri.accounting.service.DownloadPKService;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Scanner;

public class Downloadpk extends AbstractWindow {
    @Inject
    private FileUploadField uploadFile;
    @Inject
    private DownloadPKService downloadPKService;
    @Inject
    private DataManager dataManager;
    private Long newPkId;

    @Override
    public void ready() {
        uploadFile.addFileUploadSucceedListener(event -> {
            Scanner scanner = new Scanner(Objects.requireNonNull(uploadFile.getFileContent()));
            StringBuilder jsonStrBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                jsonStrBuilder.append(scanner.nextLine());
            }
            newPkId = downloadPKService.downloadPK(jsonStrBuilder.toString());
            if (newPkId != null) {
                showNotification("Загрузка прошла успешно", NotificationType.HUMANIZED);
            } else {
                showNotification("Такой ПК уже существует", NotificationType.ERROR);
            }
        });

        uploadFile.addFileUploadErrorListener(event ->
                showNotification("Ошибка загрузки файла", NotificationType.ERROR));
    }
}