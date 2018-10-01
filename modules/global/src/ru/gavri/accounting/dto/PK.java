package ru.gavri.accounting.dto;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;

public class PK {
    public transient static final PK INSTANCE = new PK();
    private String versionBios;
    private transient String versionOs;
    private String motherboardManufacturer;
    private String motherboardSerialNumber;
    private transient String nameHost;
    private transient String nameDomain;
    private transient String dnsServers;
    private transient boolean isLaptop = false;
    private CPU cpu;
    private ArrayList<HDD> hardDisks;
    private ArrayList<NetworkInterface> networkInterfaces;
    private ArrayList<Display> displays;

    public PK() {
    }

    public PK(String versionBios, String versionOs, String motherboardManufacturer, String motherboardSerialNumber,
              String nameHost, String nameDomain, String dnsServers, boolean isLaptop, CPU cpu, ArrayList<HDD> hardDisks,
              ArrayList<NetworkInterface> networkInterfaces, ArrayList<Display> displays) {
        this.versionBios = versionBios;
        this.versionOs = versionOs;
        this.motherboardManufacturer = motherboardManufacturer;
        this.motherboardSerialNumber = motherboardSerialNumber;
        this.nameHost = nameHost;
        this.nameDomain = nameDomain;
        this.dnsServers = dnsServers;
        this.isLaptop = isLaptop;
        this.cpu = cpu;
        this.hardDisks = hardDisks;
        this.networkInterfaces = networkInterfaces;
        this.displays = displays;
    }

    public boolean canSave() {
        return ObjectUtils.allNotNull(versionBios, motherboardManufacturer, motherboardSerialNumber, cpu, hardDisks)
                && hardDisks.size() > 0;
    }

    public String getVersionBios() {
        return versionBios;
    }

    public void setVersionBios(String versionBios) {
        this.versionBios = versionBios;
    }

    public String getVersionOs() {
        return versionOs;
    }

    public void setVersionOs(String versionOs) {
        this.versionOs = versionOs;
    }

    public String getMotherboardManufacturer() {
        return motherboardManufacturer;
    }

    public void setMotherboardManufacturer(String motherboardManufacturer) {
        this.motherboardManufacturer = motherboardManufacturer;
    }

    public String getMotherboardSerialNumber() {
        return motherboardSerialNumber;
    }

    public void setMotherboardSerialNumber(String motherboardSerialNumber) {
        this.motherboardSerialNumber = motherboardSerialNumber;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public ArrayList<HDD> getHardDisks() {
        return hardDisks;
    }

    public void setHardDisks(ArrayList<HDD> hardDisks) {
        this.hardDisks = hardDisks;
    }

    public ArrayList<NetworkInterface> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public void setNetworkInterfaces(ArrayList<NetworkInterface> networkInterfaces) {
        this.networkInterfaces = networkInterfaces;
    }

    public ArrayList<Display> getDisplays() {
        return displays;
    }

    public void setDisplays(ArrayList<Display> displays) {
        if (displays.size() == 0) {
            setLaptop(true);
        }
        this.displays = displays;
    }

    public boolean isLaptop() {
        return isLaptop;
    }

    public void setLaptop(boolean laptop) {
        isLaptop = laptop;
    }

    public String getNameHost() {
        return nameHost;
    }

    public void setNameHost(String nameHost) {
        this.nameHost = nameHost;
    }

    public String getNameDomain() {
        return nameDomain;
    }

    public void setNameDomain(String nameDomain) {
        this.nameDomain = nameDomain;
    }

    public String getDnsServers() {
        return dnsServers;
    }

    public void setDnsServers(String dnsServers) {
        this.dnsServers = dnsServers;
    }
}