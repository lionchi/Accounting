create table ACCOUNTING_CPU (
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
);
