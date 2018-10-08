-- alter table ACCOUNTING_PK_MOVING_ENTITY add column PK_ID bigint ^
-- update ACCOUNTING_PK_MOVING_ENTITY set PK_ID = <default_value> ;
-- alter table ACCOUNTING_PK_MOVING_ENTITY modify column PK_ID bigint not null ;
alter table ACCOUNTING_PK_MOVING_ENTITY add column PK_ID bigint not null ;
