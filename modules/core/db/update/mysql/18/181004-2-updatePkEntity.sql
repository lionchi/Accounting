alter table ACCOUNTING_PK_ENTITY change column SERIAL_NUMBER_PK SERIAL_NUMBER_PK__U44513 varchar(100)^
alter table ACCOUNTING_PK_ENTITY modify column SERIAL_NUMBER_PK__U44513 varchar(100) null ;
alter table ACCOUNTING_PK_ENTITY change column MOTHERBOARD_SERIAL_NUMBER MOTHERBOARD_SERIAL_NUMBER__U23069 varchar(150)^
alter table ACCOUNTING_PK_ENTITY change column MOTHERBOARD_MANUFACTURER MOTHERBOARD_MANUFACTURER__U61262 varchar(150)^
alter table ACCOUNTING_PK_ENTITY change column VERSION_BIOS VERSION_BIOS__U04905 varchar(100)^
alter table ACCOUNTING_PK_ENTITY add column INVENTORY_NUMBER varchar(100) ^
update ACCOUNTING_PK_ENTITY set INVENTORY_NUMBER = '' where INVENTORY_NUMBER is null ;
alter table ACCOUNTING_PK_ENTITY modify column INVENTORY_NUMBER varchar(100) not null ;