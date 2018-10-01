-- begin ACCOUNTING_CPU_ENTITY
create table ACCOUNTING_CPU_ENTITY (
    ID bigint,
    UUID varchar(32),
    --
    NAME varchar(255),
    LOGICAL_PROCESSOR_COUNT integer,
    PHYSICAL_PROCESSOR_COUNT integer,
    IDENTIFIER varchar(100),
    PROCESSOR_ID varchar(100),
    --
    primary key (ID)
)^
-- end ACCOUNTING_CPU_ENTITY
-- begin ACCOUNTING_DISPLAY_ENTITY
create table ACCOUNTING_DISPLAY_ENTITY (
    ID bigint,
    UUID varchar(32),
    --
    MANUF_ID varchar(100),
    NAME varchar(100),
    DIAGONAL varchar(50),
    PK_ID bigint,
    --
    primary key (ID)
)^
-- end ACCOUNTING_DISPLAY_ENTITY
-- begin ACCOUNTING_HDD_ENTITY
create table ACCOUNTING_HDD_ENTITY (
    ID bigint,
    UUID varchar(32),
    --
    MODEL varchar(100),
    SERIAL_NUMBER varchar(100),
    SIZE_ varchar(100),
    IS_FORMATTED boolean,
    DATE_OF_FORMATTING varchar(50),
    PK_ID bigint,
    --
    primary key (ID)
)^
-- end ACCOUNTING_HDD_ENTITY
-- begin ACCOUNTING_NETWORK_INTERFACE_ENTITY
create table ACCOUNTING_NETWORK_INTERFACE_ENTITY (
    ID bigint,
    UUID varchar(32),
    --
    NAME varchar(255),
    MAC_ADDRESS varchar(100),
    IPV4 longtext,
    IPV6 longtext,
    TRAFFIC longtext,
    PK_ID bigint,
    --
    primary key (ID)
)^
-- end ACCOUNTING_NETWORK_INTERFACE_ENTITY
-- begin ACCOUNTING_PK_ENTITY
create table ACCOUNTING_PK_ENTITY (
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
)^
-- end ACCOUNTING_PK_ENTITY
