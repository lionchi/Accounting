alter table ACCOUNTING_MOTHERBOARD_ENTITY add constraint FK_ACCOUNTING_MOTHERBOARD_ENTITY_ON_PK foreign key (PK_ID) references ACCOUNTING_PK_ENTITY(ID) on delete SET NULL;
create index IDX_ACCOUNTING_MOTHERBOARD_ENTITY_ON_PK on ACCOUNTING_MOTHERBOARD_ENTITY (PK_ID);
