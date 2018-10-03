update ACCOUNTING_PK_ENTITY set SERIAL_NUMBER_PK = '' where SERIAL_NUMBER_PK is null ;
alter table ACCOUNTING_PK_ENTITY modify column SERIAL_NUMBER_PK varchar(100) not null ;
