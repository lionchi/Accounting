create table ACCOUNTING_HDD (
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
);
