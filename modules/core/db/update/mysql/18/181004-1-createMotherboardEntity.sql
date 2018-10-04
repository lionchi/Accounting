create table ACCOUNTING_MOTHERBOARD_ENTITY (
    ID bigint,
    UUID varchar(32),
    --
    VERSION_BIOS varchar(100),
    MOTHERBOARD_MANUFACTURER varchar(100),
    MOTHERBOARD_SERIAL_NUMBER varchar(100),
    PK_ID bigint,
    --
    primary key (ID)
);
