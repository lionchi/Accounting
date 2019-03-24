package ru.gavri.accounting.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s -> %s (%s)|currentLocation,targetLocation,dateMoving")
@Table(name = "ACCOUNTING_PK_MOVING_ENTITY")
@Entity(name = "accounting$PkMovingEntity")
public class PkMovingEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = -2443305419157449716L;

    @Column(name = "UUID")
    protected UUID uuid;

    @Column(name = "APPROVAL")
    protected Boolean approval;

    @Column(name = "TARGET")
    protected Integer target;

    @NotNull
    @Column(name = "CURRENT_LOCATION", nullable = false, length = 100)
    protected String currentLocation;

    @NotNull
    @Column(name = "TARGET_LOCATION", nullable = false, length = 100)
    protected String targetLocation;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "DATE_MOVING", nullable = false)
    protected Date dateMoving;







    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PK_ENTITY_ID")
    protected PkEntity pkEntity;

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }

    public Boolean getApproval() {
        return approval;
    }


    public void setPkEntity(PkEntity pkEntity) {
        this.pkEntity = pkEntity;
    }

    public PkEntity getPkEntity() {
        return pkEntity;
    }


    public void setTarget(Target target) {
        this.target = target == null ? null : target.getId();
    }

    public Target getTarget() {
        return target == null ? null : Target.fromId(target);
    }


    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setTargetLocation(String targetLocation) {
        this.targetLocation = targetLocation;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public void setDateMoving(Date dateMoving) {
        this.dateMoving = dateMoving;
    }

    public Date getDateMoving() {
        return dateMoving;
    }


}