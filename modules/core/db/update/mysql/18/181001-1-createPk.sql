create table ACCOUNTING_PK (
    ID bigint,
    UUID varchar(32),
    --
    VERSION_BIOS varchar(100),
    MOTHERBOARD_MANUFACTURER varchar(150),
    MOTHERBOARD_SERIAL_NUMBER varchar(150),
    CPU_ID bigint,
    NAME varchar(100),
    --
    primary key (ID)
);
