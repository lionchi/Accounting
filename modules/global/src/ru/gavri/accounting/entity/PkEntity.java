package ru.gavri.accounting.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.validation.constraints.NotNull;

@NamePattern("%s (%s)|name,modelPk")
@Table(name = "ACCOUNTING_PK_ENTITY")
@Entity(name = "accounting$PkEntity")
public class PkEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = 8223469002524033124L;

    @Column(name = "UUID")
    protected UUID uuid;


    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OneToMany(mappedBy = "pkEntity")
    protected List<VideoCardEntity> videoCards;

    @Column(name = "MODEL_PK", length = 50)
    protected String modelPk;

    @Column(name = "SERIAL_NUMBER_PK", length = 100)
    protected String serialNumberPk;

    @Column(name = "MANUFACTURE")
    protected String manufacture;

    @Column(name = "LOCATION", length = 100)
    protected String location;

    @NotNull
    @Column(name = "INVENTORY_NUMBER", nullable = false, length = 100)
    protected String inventoryNumber;

    @Column(name = "IS_LAPTOP")
    protected Boolean isLaptop;




    @OnDeleteInverse(DeletePolicy.UNLINK)
    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    protected CpuEntity cpu;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OneToMany(mappedBy = "pk")
    protected List<DisplayEntity> displays;

    @Column(name = "NAME", length = 100)
    protected String name;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OneToMany(mappedBy = "pk")
    protected List<HddEntity> hardDisks;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OneToMany(mappedBy = "pk")
    protected List<NetworkInterfaceEntity> networkInterfaces;




    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pk")
    protected MotherboardEntity motherboardEntity;

    public void setVideoCards(List<VideoCardEntity> videoCards) {
        this.videoCards = videoCards;
    }

    public List<VideoCardEntity> getVideoCards() {
        return videoCards;
    }


    public void setModelPk(String modelPk) {
        this.modelPk = modelPk;
    }

    public String getModelPk() {
        return modelPk;
    }

    public void setSerialNumberPk(String serialNumberPk) {
        this.serialNumberPk = serialNumberPk;
    }

    public String getSerialNumberPk() {
        return serialNumberPk;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getManufacture() {
        return manufacture;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }


    public void setMotherboardEntity(MotherboardEntity motherboardEntity) {
        this.motherboardEntity = motherboardEntity;
    }

    public MotherboardEntity getMotherboardEntity() {
        return motherboardEntity;
    }


    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }


    public void setIsLaptop(Boolean isLaptop) {
        this.isLaptop = isLaptop;
    }

    public Boolean getIsLaptop() {
        return isLaptop;
    }


    public List<NetworkInterfaceEntity> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public void setNetworkInterfaces(List<NetworkInterfaceEntity> networkInterfaces) {
        this.networkInterfaces = networkInterfaces;
    }


    public List<HddEntity> getHardDisks() {
        return hardDisks;
    }

    public void setHardDisks(List<HddEntity> hardDisks) {
        this.hardDisks = hardDisks;
    }


    public List<DisplayEntity> getDisplays() {
        return displays;
    }

    public void setDisplays(List<DisplayEntity> displays) {
        this.displays = displays;
    }


    public CpuEntity getCpu() {
        return cpu;
    }

    public void setCpu(CpuEntity cpu) {
        this.cpu = cpu;
    }


    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }







    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}