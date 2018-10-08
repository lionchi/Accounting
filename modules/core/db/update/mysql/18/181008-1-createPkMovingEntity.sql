create table ACCOUNTING_PK_MOVING_ENTITY (
    ID bigint,
    UUID varchar(32),
    --
    TARGET integer,
    CURRENT_LOCATION varchar(100) not null,
    TARGET_LOCATION varchar(100) not null,
    DATE_MOVING date not null,
    --
    primary key (ID)
);
