package ru.gavri.accounting.web.screens;

import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.components.TextField;
import ru.gavri.accounting.service.DownloadPKService;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
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
    private Long start;
    private Long end;

    @Override
    public void ready() {
        start = System.currentTimeMillis();
        uploadFile.addFileUploadSucceedListener(event -> {
            if (map.containsKey(uploadFile.getFileName())) {
                Boolean aBoolean = map.get(uploadFile.getFileName());
                if (aBoolean.equals(Boolean.TRUE)) {
                    Scanner scanner = new Scanner(Objects.requireNonNull(uploadFile.getFileContent()));
                    StringBuilder jsonStrBuilder = new StringBuilder();
                    while (scanner.hasNext()) {
                        jsonStrBuilder.append(scanner.nextLine());
                    }
                    newPkId = downloadPKService.downloadPK(jsonStrBuilder.toString());
                    end = System.currentTimeMillis();
                    System.out.println("Время выгрузки данных из результирующего файла в милисекундах равно " + (end - start));
                    if (newPkId != null) {
                        showNotification("Загрузка прошла успешно", NotificationType.HUMANIZED);
                    } else {
                        showNotification("Такой ПК уже существует", NotificationType.ERROR);
                    }
                } else {
                    showNotification("У файла нарушена целостность. Загрузка остановлена", NotificationType.ERROR);
                }
            } else {
                showNotification("Выбранный файл не проверялся на целостность", NotificationType.ERROR);
            }

        });
        uploadFile.addFileUploadErrorListener(event ->
                showNotification("Ошибка при загрузке файла", NotificationType.ERROR));
    }

    public void onClearButtonClick() {
        if (pathFileField.getValue() != null) {
            pathFileField.setValue(null);
        }
    }

    public void onCheckButtonClick() throws FileNotFoundException, ParseException {
        if (pathFileField.getValue() == null) {
            showNotification("Укажите путь(-и) к файлу(-ам)", NotificationType.TRAY);
            return;
        }
        String[] paths = pathFileField.getValue().toString().split(",");
        for (String path : paths) {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            Date dateLastModified = new Date(file.lastModified());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyHHmm");

            Calendar old = Calendar.getInstance();
            old.setTime(simpleDateFormat.parse(simpleDateFormat.format(dateLastModified)));
            int yearLastModified = old.get(Calendar.YEAR);

            Calendar now = Calendar.getInstance();
            int currentYear = now.get(Calendar.YEAR);

            StringBuilder jsonStrBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                jsonStrBuilder.append(scanner.nextLine());
            }
            scanner.close();

            if (yearLastModified != currentYear) {
                showNotification(String.format("Файл %s устарел", file.getName()), NotificationType.ERROR);
                continue;
            }

            simpleDateFormat = new SimpleDateFormat("ddMMHHmm");
            boolean result = downloadPKService.checkLabel(jsonStrBuilder.toString(), simpleDateFormat.format(dateLastModified));
            if (!result) {
                showNotification(String.format("Нарушена целостность файла %s", file.getName()), NotificationType.ERROR);
            } else {
                showNotification(String.format("Целостность файла %s не нарушена", file.getName()), NotificationType.TRAY);
            }
            map.put(file.getName(), result);
        }
    }
}