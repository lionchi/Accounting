package ru.gavri.accounting.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|cardName")
@Table(name = "ACCOUNTING_VIDEO_CARD_ENTITY")
@Entity(name = "accounting$VideoCardEntity")
public class VideoCardEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = -4468151336794147902L;

    @Column(name = "UUID")
    protected UUID uuid;

    @Column(name = "CARD_NAME", length = 100)
    protected String cardName;

    @Column(name = "CURRENT_MODE", length = 100)
    protected String currentMode;

    @Column(name = "DRIVER_VERSION", length = 100)
    protected String driverVersion;

    @Column(name = "DEVICE_PROBLEM_CODE", length = 100)
    protected String deviceProblemCode;

    @Column(name = "CHIP_TYPE", length = 100)
    protected String chipType;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PK_ENTITY_ID")
    protected PkEntity pkEntity;

    @Column(name = "DEVICE_KEY", length = 100)
    protected String deviceKey;

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getDeviceKey() {
        return deviceKey;
    }


    public void setPkEntity(PkEntity pkEntity) {
        this.pkEntity = pkEntity;
    }

    public PkEntity getPkEntity() {
        return pkEntity;
    }


    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
    }

    public String getCurrentMode() {
        return currentMode;
    }

    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    public String getDriverVersion() {
        return driverVersion;
    }

    public void setDeviceProblemCode(String deviceProblemCode) {
        this.deviceProblemCode = deviceProblemCode;
    }

    public String getDeviceProblemCode() {
        return deviceProblemCode;
    }

    public void setChipType(String chipType) {
        this.chipType = chipType;
    }

    public String getChipType() {
        return chipType;
    }


}