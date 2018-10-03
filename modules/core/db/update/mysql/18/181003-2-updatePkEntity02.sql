alter table ACCOUNTING_PK_ENTITY add constraint FK_ACCOUNTING_PK_ENTITY_ON_CPU foreign key (CPU_ID) references ACCOUNTING_CPU_ENTITY(ID) on delete SET NULL;
