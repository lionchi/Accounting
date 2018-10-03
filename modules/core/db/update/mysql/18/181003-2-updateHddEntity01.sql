alter table ACCOUNTING_HDD_ENTITY add constraint FK_ACCOUNTING_HDD_ENTITY_ON_PK foreign key (PK_ID) references ACCOUNTING_PK_ENTITY(ID) on delete SET NULL;
