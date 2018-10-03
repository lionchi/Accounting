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

@NamePattern("%s|name")
@Table(name = "ACCOUNTING_PK_ENTITY")
@Entity(name = "accounting$PkEntity")
public class PkEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = 8223469002524033124L;

    @Column(name = "UUID")
    protected UUID uuid;

    @NotNull
    @Column(name = "SERIAL_NUMBER_PK", nullable = false, length = 100)
    protected String serialNumberPk;

    @Column(name = "IS_LAPTOP")
    protected Boolean isLaptop;

    @Column(name = "VERSION_BIOS", length = 100)
    protected String versionBios;

    @Column(name = "MOTHERBOARD_MANUFACTURER", length = 150)
    protected String motherboardManufacturer;

    @Column(name = "MOTHERBOARD_SERIAL_NUMBER", length = 150)
    protected String motherboardSerialNumber;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open"})
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

    public void setSerialNumberPk(String serialNumberPk) {
        this.serialNumberPk = serialNumberPk;
    }

    public String getSerialNumberPk() {
        return serialNumberPk;
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

    public void setVersionBios(String versionBios) {
        this.versionBios = versionBios;
    }

    public String getVersionBios() {
        return versionBios;
    }

    public void setMotherboardManufacturer(String motherboardManufacturer) {
        this.motherboardManufacturer = motherboardManufacturer;
    }

    public String getMotherboardManufacturer() {
        return motherboardManufacturer;
    }

    public void setMotherboardSerialNumber(String motherboardSerialNumber) {
        this.motherboardSerialNumber = motherboardSerialNumber;
    }

    public String getMotherboardSerialNumber() {
        return motherboardSerialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}