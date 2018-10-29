package ru.gavri.accounting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;
import ru.gavri.accounting.dto.*;
import ru.gavri.accounting.entity.*;
import ru.gavri.accounting.exceptions.DownloadJsonFileException;

import javax.inject.Inject;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
        if (!checkExistPkEntity(pk.getInventoryNumber())) {
            return createEntities(pk);
        }
        return null;
    }

    @Override
    public boolean checkLabel(String jsonStr, String format) {
        //Получаем двоичное представление метки
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < jsonStr.length(); i++) {
            if (jsonStr.charAt(i) == ':' && jsonStr.charAt(i + 1) == ' ' && jsonStr.charAt(i + 2) == ' ') {
                stringBuilder.append("1");
            } else if (jsonStr.charAt(i) == ':' && jsonStr.charAt(i + 1) == ' ' && jsonStr.charAt(i + 2) != ' ') {
                stringBuilder.append("0");
            }
            if (i + 2 == jsonStr.length()) {
                break;
            }
        }
        String result = binaryToString(stringBuilder.toString());
        return result.replace("\u0000", "").equals(format);
    }

    private String binaryToString(String binary) {
        List<String> sequences = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < binary.length() / 6; i++) {
            start = end;
            end = end + 6;
            sequences.add(binary.substring(start, end));
        }
        StringBuilder sb = new StringBuilder();
        for (String sequence : sequences) {
            int ascii = Integer.parseInt(sequence, 2);
            char character = (char) ascii;
            sb.append(character);
        }
        return sb.toString();
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
            MotherboardEntity motherboardEntity;
            if ((motherboardEntity = checkExistMotherboardEntity(pk.getMotherboardSerialNumber(), newPk)) != null) {
                newPk.setMotherboardEntity(motherboardEntity);
            } else {
                motherboardEntity = metadata.create(MotherboardEntity.class);
                motherboardEntity.setVersionBios(pk.getVersionBios());
                motherboardEntity.setMotherboardManufacturer(pk.getMotherboardManufacturer());
                motherboardEntity.setMotherboardSerialNumber(pk.getMotherboardSerialNumber());
                motherboardEntity.setPk(newPk);
                entityManager.persist(motherboardEntity);
                newPk.setMotherboardEntity(motherboardEntity);
            }
            //Создание ЦПУ
            CpuEntity cpuEntity;
            if ((cpuEntity = checkExistCpuEntity(pk.getCpu().getProcessorID(), newPk)) != null) {
                newPk.setCpu(cpuEntity);
            } else {
                cpuEntity = metadata.create(CpuEntity.class);
                cpuEntity.setNameCpu(pk.getCpu().getName());
                cpuEntity.setIdentifier(pk.getCpu().getIdentifier());
                cpuEntity.setLogicalProcessorCount(pk.getCpu().getLogicalProcessorCount());
                cpuEntity.setPhysicalProcessorCount(pk.getCpu().getPhysicalProcessorCount());
                cpuEntity.setProcessorID(pk.getCpu().getProcessorID());
                cpuEntity.setPk(newPk);
                entityManager.persist(cpuEntity);
                newPk.setCpu(cpuEntity);
            }
            //Создание списка Видеокарт
            if (pk.getVideoCards() != null && pk.getVideoCards().size() > 0) {
                newPk.setVideoCards(createListVideoCard(pk.getVideoCards(), entityManager, newPk));
            }
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

    private List<VideoCardEntity> createListVideoCard(ArrayList<VideoCard> videoCards, EntityManager entityManager, PkEntity newPk) {
        ArrayList<VideoCardEntity> videoCardEntities = new ArrayList<>();
        for (VideoCard videoCard : videoCards) {
            VideoCardEntity videoCardEntity;
            if ((videoCardEntity = checkExistVideoCardEntity(videoCard.getDeviceKey(), newPk)) != null) {
                videoCardEntities.add(videoCardEntity);
            } else {
                videoCardEntity = metadata.create(VideoCardEntity.class);
                videoCardEntity.setDriverVersion(videoCard.getDriverVersion());
                videoCardEntity.setCardName(videoCard.getCardName());
                videoCardEntity.setChipType(videoCard.getChipType());
                videoCardEntity.setCurrentMode(videoCard.getCurrentMode().equals("Unknown") ? "" : videoCard.getCurrentMode());
                videoCardEntity.setDeviceKey(videoCard.getDeviceKey());
                videoCardEntity.setDeviceProblemCode(videoCard.getDeviceProblemCode().equals("No Problem") ? "Нет" : videoCard.getDeviceProblemCode());
                videoCardEntity.setPkEntity(newPk);
                entityManager.persist(videoCardEntity);
                videoCardEntities.add(videoCardEntity);
            }
        }
        return videoCardEntities;
    }

    private ArrayList<HddEntity> createListHdd(ArrayList<HDD> hdds, EntityManager entityManager, PkEntity newPk) {
        ArrayList<HddEntity> hddEntities = new ArrayList<>();
        for (HDD hdd : hdds) {
            HddEntity hddEntity;
            if ((hddEntity = checkExistHddEntity(hdd.getSerialNumber(), hdd, newPk)) != null) {
                hddEntities.add(hddEntity);
            } else {
                hddEntity = metadata.create(HddEntity.class);
                hddEntity.setDateOfFormatting(hdd.getDateOfFormatting());
                hddEntity.setIsFormatted(hdd.isFormatted());
                hddEntity.setModel(hdd.getModel());
                hddEntity.setSerialNumber(hdd.getSerialNumber().trim());
                hddEntity.setSize(hdd.getSize());
                hddEntity.setPk(newPk);
                entityManager.persist(hddEntity);
                hddEntities.add(hddEntity);
            }
        }
        return hddEntities;
    }

    private ArrayList<NetworkInterfaceEntity> createListNetworkInterface(ArrayList<NetworkInterface> interfaces,
                                                                         EntityManager entityManager, PkEntity newPk) {
        ArrayList<NetworkInterfaceEntity> networkInterfaceEntities = new ArrayList<>();
        for (NetworkInterface networkInterface : interfaces) {
            NetworkInterfaceEntity networkInterfaceEntity;
            if ((networkInterfaceEntity = checkExistNetworkInterface(networkInterface.getMacAddress(), newPk)) != null) {
                networkInterfaceEntities.add(networkInterfaceEntity);
            } else {
                networkInterfaceEntity = metadata.create(NetworkInterfaceEntity.class);
                networkInterfaceEntity.setIpv4(networkInterface.getIpv4());
                networkInterfaceEntity.setIpv6(networkInterface.getIpv6());
                networkInterfaceEntity.setMacAddress(networkInterface.getMacAddress());
                networkInterfaceEntity.setNameInterface(networkInterface.getName().replaceAll("\r\n", ""));
                networkInterfaceEntity.setTraffic(networkInterface.getTraffic().replaceAll("\r\n", ""));
                networkInterfaceEntity.setPk(newPk);
                entityManager.persist(networkInterfaceEntity);
                networkInterfaceEntities.add(networkInterfaceEntity);
            }
        }
        return networkInterfaceEntities;
    }

    private ArrayList<DisplayEntity> createListDisplays(ArrayList<Display> displays,
                                                        EntityManager entityManager, PkEntity newPk) {
        ArrayList<DisplayEntity> displayEntities = new ArrayList<>();
        for (Display display : displays) {
            DisplayEntity displayEntity;
            if ((displayEntity = checkExistDisplayEntity(display.getDisplayId(), newPk)) != null) {
                displayEntities.add(displayEntity);
            } else {
                displayEntity = metadata.create(DisplayEntity.class);
                displayEntity.setDiagonal(display.getDiagonal());
                displayEntity.setManufId(display.getManufId());
                displayEntity.setDisplayId(display.getDisplayId());
                displayEntity.setNameDisplay(display.getName());
                displayEntity.setPk(newPk);
                entityManager.persist(displayEntity);
                displayEntities.add(displayEntity);
            }
        }
        return displayEntities;
    }

    private boolean checkExistPkEntity(String inventoryNumber) {
        try (Transaction transaction = persistence.getTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            PkEntity pkEntity = entityManager.createQuery("select pk from accounting$PkEntity pk where pk.inventoryNumber = :inventoryNumber", PkEntity.class)
                    .setParameter("inventoryNumber", inventoryNumber)
                    .getFirstResult();
            transaction.commit();
            return pkEntity != null;
        }
    }

    private MotherboardEntity checkExistMotherboardEntity(String serialNumber, PkEntity pkEntity) {
        try (Transaction transaction = persistence.getTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            MotherboardEntity motherboardEntity = entityManager.createQuery("select m from accounting$MotherboardEntity m where m.motherboardSerialNumber = :serialNumber", MotherboardEntity.class)
                    .setParameter("serialNumber", serialNumber)
                    .getFirstResult();
            if (motherboardEntity != null) {
                motherboardEntity.setPk(pkEntity);
            }
            transaction.commit();
            return motherboardEntity;
        }
    }

    private CpuEntity checkExistCpuEntity(String processorID, PkEntity pkEntity) {
        try (Transaction transaction = persistence.getTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            CpuEntity cpuEntity = entityManager.createQuery("select c from accounting$CpuEntity c where c.processorID = :processorID", CpuEntity.class)
                    .setParameter("processorID", processorID)
                    .getFirstResult();
            if (cpuEntity != null) {
                cpuEntity.setPk(pkEntity);
            }
            transaction.commit();
            return cpuEntity;
        }
    }

    private VideoCardEntity checkExistVideoCardEntity(String deviceKey, PkEntity pkEntity) {
        try (Transaction transaction = persistence.getTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            VideoCardEntity videoCardEntity = entityManager.createQuery("select v from accounting$VideoCardEntity v where v.deviceKey = :deviceKey", VideoCardEntity.class)
                    .setParameter("deviceKey", deviceKey)
                    .getFirstResult();
            if (videoCardEntity != null) {
                videoCardEntity.setPkEntity(pkEntity);
            }
            transaction.commit();
            return videoCardEntity;
        }
    }

    private HddEntity checkExistHddEntity(String serialNumber, HDD hdd, PkEntity pkEntity) {
        try (Transaction transaction = persistence.getTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            HddEntity hddEntity = entityManager.createQuery("select h from accounting$HddEntity h where h.serialNumber = :serialNumber", HddEntity.class)
                    .setParameter("serialNumber", serialNumber)
                    .getFirstResult();
            if (hddEntity != null) {
                hddEntity.setPk(pkEntity);
                hddEntity.setIsFormatted(hdd.isFormatted());
                hdd.setDateOfFormatting(hdd.getDateOfFormatting());
            }
            transaction.commit();
            return hddEntity;
        }
    }

    private DisplayEntity checkExistDisplayEntity(String displayId, PkEntity pkEntity) {
        try (Transaction transaction = persistence.getTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            DisplayEntity displayEntity = entityManager.createQuery("select d from accounting$DisplayEntity d where d.displayId = :displayId", DisplayEntity.class)
                    .setParameter("displayId", displayId)
                    .getFirstResult();
            if (displayEntity != null) {
                displayEntity.setPk(pkEntity);
            }
            transaction.commit();
            return displayEntity;
        }
    }

    private NetworkInterfaceEntity checkExistNetworkInterface(String mac, PkEntity pkEntity) {
        try (Transaction transaction = persistence.getTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            NetworkInterfaceEntity networkInterfaceEntity = entityManager.createQuery("select n from accounting$NetworkInterfaceEntity n where n.macAddress = :mac", NetworkInterfaceEntity.class)
                    .setParameter("mac", mac)
                    .getFirstResult();
            if (networkInterfaceEntity != null) {
                networkInterfaceEntity.setPk(pkEntity);
            }
            transaction.commit();
            return networkInterfaceEntity;
        }
    }
}