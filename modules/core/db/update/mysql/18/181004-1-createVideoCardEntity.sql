create table ACCOUNTING_VIDEO_CARD_ENTITY (
    ID bigint,
    UUID varchar(32),
    --
    CARD_NAME varchar(100),
    CURRENT_MODE varchar(100),
    DRIVER_VERSION varchar(100),
    DEVICE_PROBLEM_CODE varchar(100),
    CHIP_TYPE varchar(100),
    PK_ENTITY_ID bigint,
    DEVICE_KEY varchar(100),
    --
    primary key (ID)
);
