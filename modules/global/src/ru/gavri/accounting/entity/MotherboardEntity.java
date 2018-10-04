package ru.gavri.accounting.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|motherboardManufacturer")
@Table(name = "ACCOUNTING_MOTHERBOARD_ENTITY")
@Entity(name = "accounting$MotherboardEntity")
public class MotherboardEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = -1041006514802131150L;

    @Column(name = "UUID")
    protected UUID uuid;

    @Column(name = "VERSION_BIOS", length = 100)
    protected String versionBios;

    @Column(name = "MOTHERBOARD_MANUFACTURER", length = 100)
    protected String motherboardManufacturer;

    @Column(name = "MOTHERBOARD_SERIAL_NUMBER", length = 100)
    protected String motherboardSerialNumber;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PK_ID")
    protected PkEntity pk;

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

    public void setPk(PkEntity pk) {
        this.pk = pk;
    }

    public PkEntity getPk() {
        return pk;
    }


}