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
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.JoinColumn;

@NamePattern("%s|nameCpu")
@Table(name = "ACCOUNTING_CPU_ENTITY")
@Entity(name = "accounting$CpuEntity")
public class CpuEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = -6703121372695071386L;

    @Column(name = "UUID")
    protected UUID uuid;

    @Column(name = "NAME_CPU")
    protected String nameCpu;

    @Column(name = "LOGICAL_PROCESSOR_COUNT")
    protected Integer logicalProcessorCount;

    @Column(name = "PHYSICAL_PROCESSOR_COUNT")
    protected Integer physicalProcessorCount;

    @Column(name = "IDENTIFIER", length = 100)
    protected String identifier;

    @Column(name = "PROCESSOR_ID", length = 100)
    protected String processorID;

    @JoinColumn(name = "PK_ID")
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @OneToOne(fetch = FetchType.LAZY)
    protected PkEntity pk;

    public void setNameCpu(String nameCpu) {
        this.nameCpu = nameCpu;
    }

    public String getNameCpu() {
        return nameCpu;
    }


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

    public void setLogicalProcessorCount(Integer logicalProcessorCount) {
        this.logicalProcessorCount = logicalProcessorCount;
    }

    public Integer getLogicalProcessorCount() {
        return logicalProcessorCount;
    }

    public void setPhysicalProcessorCount(Integer physicalProcessorCount) {
        this.physicalProcessorCount = physicalProcessorCount;
    }

    public Integer getPhysicalProcessorCount() {
        return physicalProcessorCount;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setProcessorID(String processorID) {
        this.processorID = processorID;
    }

    public String getProcessorID() {
        return processorID;
    }


}