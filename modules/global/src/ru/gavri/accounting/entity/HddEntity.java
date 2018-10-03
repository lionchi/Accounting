package ru.gavri.accounting.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

@NamePattern("%s (%s)|model,serialNumber")
@Table(name = "ACCOUNTING_HDD_ENTITY")
@Entity(name = "accounting$HddEntity")
public class HddEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = -5390207863778580939L;

    @Column(name = "UUID")
    protected UUID uuid;

    @Column(name = "MODEL", length = 100)
    protected String model;

    @Column(name = "SERIAL_NUMBER", length = 100)
    protected String serialNumber;

    @Column(name = "SIZE_", length = 100)
    protected String size;

    @Column(name = "IS_FORMATTED")
    protected Boolean isFormatted;

    @Column(name = "DATE_OF_FORMATTING", length = 50)
    protected String dateOfFormatting;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PK_ID")
    protected PkEntity pk;

    public PkEntity getPk() {
        return pk;
    }

    public void setPk(PkEntity pk) {
        this.pk = pk;
    }



    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setIsFormatted(Boolean isFormatted) {
        this.isFormatted = isFormatted;
    }

    public Boolean getIsFormatted() {
        return isFormatted;
    }

    public void setDateOfFormatting(String dateOfFormatting) {
        this.dateOfFormatting = dateOfFormatting;
    }

    public String getDateOfFormatting() {
        return dateOfFormatting;
    }


}