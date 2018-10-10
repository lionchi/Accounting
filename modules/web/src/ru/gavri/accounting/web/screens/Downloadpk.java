package ru.gavri.accounting.web.screens;

import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.components.TextField;
import ru.gavri.accounting.service.DownloadPKService;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Downloadpk extends AbstractWindow {
    @Inject
    private FileUploadField uploadFile;
    @Inject
    private DownloadPKService downloadPKService;
    @Inject
    private TextField pathFileField;
    @Inject
    private Button checkButton;
    @Inject
    private Button clearButton;

    private Long newPkId;
    private Map<String, Boolean> map = new HashMap<>();

    @Override
    public void ready() {
        uploadFile.addFileUploadSucceedListener(event -> {
            Boolean aBoolean = map.get(uploadFile.getFileName());
            if(aBoolean.equals(Boolean.TRUE)) {
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
            } else {
                showNotification("У файла нарушена целостность. Загрузка остановлена", NotificationType.ERROR);
            }
        });

        uploadFile.addFileUploadErrorListener(event ->
                showNotification("Ошибка загрузки файла", NotificationType.ERROR));
    }

    public void onClearButtonClick() {
        if (pathFileField.getValue()!=null) {
            pathFileField.setValue(null);
        }
    }

    public void onCheckButtonClick() throws FileNotFoundException {
        if(pathFileField.getValue()==null) {
            showNotification("Укажите путь к файлу", NotificationType.TRAY);
            return;
        }
        File file = new File(pathFileField.getValue().toString());
        Scanner scanner = new Scanner(file);
        Date dateLastModified = new Date(file.lastModified());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyHHmm");
        StringBuilder jsonStrBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            jsonStrBuilder.append(scanner.nextLine());
        }
        boolean result = downloadPKService.checkLabel(jsonStrBuilder.toString(), simpleDateFormat.format(dateLastModified));
        if(!result) {
            showNotification("Нарушена целостность файла", NotificationType.TRAY);
        }
        showNotification("Целостность не нарушена", NotificationType.TRAY);
        map.put(file.getName(),result);
    }
}