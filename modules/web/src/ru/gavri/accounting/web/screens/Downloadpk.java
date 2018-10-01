package ru.gavri.accounting.web.screens;

import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import ru.gavri.accounting.service.DownloadPKService;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Downloadpk extends AbstractWindow {
    @Inject
    private FileUploadField uploadFile;
    @Inject
    private DownloadPKService downloadPKService;
    @Inject
    private FileUploadingAPI fileUploadingAPI;

    @Override
    public void ready() {
        uploadFile.addFileUploadSucceedListener(event -> {
            try {
                File file = fileUploadingAPI.getFile(uploadFile.getFileId());
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream serial = new ObjectInputStream(fileInputStream);
                while(serial.available() > 0){
                    downloadPKService.downloadPK(serial.readObject());
                }
                serial.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        uploadFile.addFileUploadErrorListener(event ->
                showNotification("Ошибка загрузки файла", NotificationType.HUMANIZED));
    }
}