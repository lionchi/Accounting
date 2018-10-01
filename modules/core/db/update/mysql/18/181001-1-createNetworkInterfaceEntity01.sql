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
);
