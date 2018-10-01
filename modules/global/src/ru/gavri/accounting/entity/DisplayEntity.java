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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|name")
@Table(name = "ACCOUNTING_DISPLAY_ENTITY")
@Entity(name = "accounting$DisplayEntity")
public class DisplayEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = 8678911233661040489L;

    @Column(name = "UUID")
    protected UUID uuid;

    @Column(name = "MANUF_ID", length = 100)
    protected String manufId;

    @Column(name = "NAME", length = 100)
    protected String name;

    @Column(name = "DIAGONAL", length = 50)
    protected String diagonal;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
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

    public void setManufId(String manufId) {
        this.manufId = manufId;
    }

    public String getManufId() {
        return manufId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    public String getDiagonal() {
        return diagonal;
    }


}