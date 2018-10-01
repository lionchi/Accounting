package ru.gavri.accounting.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Lob;
import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|name")
@Table(name = "ACCOUNTING_NETWORK_INTERFACE_ENTITY")
@Entity(name = "accounting$NetworkInterfaceEntity")
public class NetworkInterfaceEntity extends BaseLongIdEntity implements HasUuid {
    private static final long serialVersionUID = -4777545962943103046L;

    @Column(name = "UUID")
    protected UUID uuid;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "MAC_ADDRESS", length = 100)
    protected String macAddress;

    @Lob
    @Column(name = "IPV4")
    protected String ipv4;

    @Lob
    @Column(name = "IPV6")
    protected String ipv6;

    @Lob
    @Column(name = "TRAFFIC")
    protected String traffic;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTraffic() {
        return traffic;
    }


}