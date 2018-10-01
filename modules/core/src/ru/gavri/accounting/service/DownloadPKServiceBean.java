package ru.gavri.accounting.service;

import org.springframework.stereotype.Service;
import ru.gavri.accounting.dto.PK;


@Service(DownloadPKService.NAME)
public class DownloadPKServiceBean implements DownloadPKService {

    @Override
    public void downloadPK(Object object) {
        PK pk = (PK) object;
    }
}