alter table ACCOUNTING_DISPLAY_ENTITY add constraint FK_ACCOUNTING_DISPLAY_ENTITY_ON_PK foreign key (PK_ID) references ACCOUNTING_PK_ENTITY(ID);
create index IDX_ACCOUNTING_DISPLAY_ENTITY_ON_PK on ACCOUNTING_DISPLAY_ENTITY (PK_ID);
