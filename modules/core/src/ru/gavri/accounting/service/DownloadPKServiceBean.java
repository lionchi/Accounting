package ru.gavri.accounting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;
import ru.gavri.accounting.dto.Display;
import ru.gavri.accounting.dto.HDD;
import ru.gavri.accounting.dto.NetworkInterface;
import ru.gavri.accounting.dto.PK;
import ru.gavri.accounting.entity.*;
import ru.gavri.accounting.exceptions.DownloadJsonFileException;

import javax.inject.Inject;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

@Service(DownloadPKService.NAME)
public class DownloadPKServiceBean implements DownloadPKService {

    @Inject
    private Metadata metadata;
    @Inject
    private Persistence persistence;

    @Override
    public Long downloadPK(String jsonStr) {
        PK pk;
        StringReader stringReader = new StringReader(jsonStr);
        ObjectMapper mapper = new ObjectMapper();
        try {
            pk = mapper.readValue(stringReader, PK.class);
            System.out.println();
        } catch (IOException e) {
            throw new DownloadJsonFileException(e.getMessage());
        }
        return createEntities(pk);
    }

    private Long createEntities(PK pk) {
        try (Transaction transaction = persistence.getTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            //Создание ПК
            PkEntity newPk = metadata.create(PkEntity.class);
            newPk.setName(String.format("ПК №%d", newPk.getId()));
            newPk.setInventoryNumber(pk.getInventoryNumber());
            newPk.setIsLaptop(pk.isLaptop());
            newPk.setLocation(pk.getLocation());
            newPk.setModelPk(pk.getModelPk());
            newPk.setSerialNumberPk(pk.getSerialNumberPk().equals("Default string") ? "" : pk.getSerialNumberPk());
            newPk.setManufacture(pk.getManufacture());
            //Создание МП
            MotherboardEntity motherboardEntity = metadata.create(MotherboardEntity.class);
            motherboardEntity.setVersionBios(pk.getVersionBios());
            motherboardEntity.setMotherboardManufacturer(pk.getMotherboardManufacturer());
            motherboardEntity.setMotherboardSerialNumber(pk.getMotherboardSerialNumber());
            motherboardEntity.setPk(newPk);
            entityManager.persist(motherboardEntity);
            newPk.setMotherboardEntity(motherboardEntity);
            //Создание ЦПУ
            CpuEntity cpuEntity = metadata.create(CpuEntity.class);
            cpuEntity.setNameCpu(pk.getCpu().getName());
            cpuEntity.setIdentifier(pk.getCpu().getIdentifier());
            cpuEntity.setLogicalProcessorCount(pk.getCpu().getLogicalProcessorCount());
            cpuEntity.setPhysicalProcessorCount(pk.getCpu().getPhysicalProcessorCount());
            cpuEntity.setProcessorID(pk.getCpu().getProcessorID());
            cpuEntity.setPk(newPk);
            entityManager.persist(cpuEntity);
            newPk.setCpu(cpuEntity);
            //Создание списка HDD
            if (pk.getHardDisks() != null && pk.getHardDisks().size() > 0) {
                newPk.setHardDisks(createListHdd(pk.getHardDisks(), entityManager, newPk));
            }
            //Создание списка Сетевых интерфейсов
            if (pk.getNetworkInterfaces() != null && pk.getNetworkInterfaces().size() > 0) {
                newPk.setNetworkInterfaces(createListNetworkInterface(pk.getNetworkInterfaces(), entityManager, newPk));
            }
            //Создание списка мониторов
            if (pk.getDisplays() != null && pk.getDisplays().size() > 0) {
                newPk.setDisplays(createListDisplays(pk.getDisplays(), entityManager, newPk));
            }
            entityManager.persist(newPk);
            transaction.commit();
            return newPk.getId();
        }
    }

    private ArrayList<HddEntity> createListHdd(ArrayList<HDD> hdds, EntityManager entityManager, PkEntity newPk) {
        ArrayList<HddEntity> hddEntities = new ArrayList<>();
        for (HDD hdd : hdds) {
            HddEntity hddEntity = metadata.create(HddEntity.class);
            hddEntity.setDateOfFormatting(hdd.getDateOfFormatting());
            hddEntity.setIsFormatted(hdd.isFormatted());
            hddEntity.setModel(hdd.getModel());
            hddEntity.setSerialNumber(hdd.getSerialNumber().trim());
            hddEntity.setSize(hdd.getSize());
            hddEntity.setPk(newPk);
            entityManager.persist(hddEntity);
            hddEntities.add(hddEntity);
        }
        return hddEntities;
    }

    private ArrayList<NetworkInterfaceEntity> createListNetworkInterface(ArrayList<NetworkInterface> interfaces,
                                                                         EntityManager entityManager, PkEntity newPk) {
        ArrayList<NetworkInterfaceEntity> networkInterfaceEntities = new ArrayList<>();
        for (NetworkInterface networkInterface : interfaces) {
            NetworkInterfaceEntity networkInterfaceEntity = metadata.create(NetworkInterfaceEntity.class);
            networkInterfaceEntity.setIpv4(networkInterface.getIpv4());
            networkInterfaceEntity.setIpv6(networkInterface.getIpv6());
            networkInterfaceEntity.setMacAddress(networkInterface.getMacAddress());
            networkInterfaceEntity.setNameInterface(networkInterface.getName());
            networkInterfaceEntity.setTraffic(networkInterface.getTraffic());
            networkInterfaceEntity.setPk(newPk);
            entityManager.persist(networkInterfaceEntity);
            networkInterfaceEntities.add(networkInterfaceEntity);
        }
        return networkInterfaceEntities;
    }

    private ArrayList<DisplayEntity> createListDisplays(ArrayList<Display> displays,
                                                        EntityManager entityManager, PkEntity newPk) {
        ArrayList<DisplayEntity> displayEntities = new ArrayList<>();
        for (Display display : displays) {
            DisplayEntity displayEntity = metadata.create(DisplayEntity.class);
            displayEntity.setDiagonal(display.getDiagonal());
            displayEntity.setManufId(display.getManufId());
            displayEntity.setDiagonal(display.getDiagonal());
            displayEntity.setNameDisplay(display.getName());
            displayEntity.setPk(newPk);
            entityManager.persist(displayEntity);
            displayEntities.add(displayEntity);
        }
        return displayEntities;
    }
}