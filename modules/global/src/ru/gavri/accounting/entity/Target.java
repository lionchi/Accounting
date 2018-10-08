package ru.gavri.accounting.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Target implements EnumClass<Integer> {

    INTO_OPERATION(10),
    REPAIRS(20),
    WRITE_OFF(30),
    WAREHOUSE(40),
    SERVICE_MOVEMENT(50);

    private Integer id;

    Target(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Target fromId(Integer id) {
        for (Target at : Target.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}