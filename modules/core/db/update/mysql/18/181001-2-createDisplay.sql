alter table ACCOUNTING_DISPLAY add constraint FK_ACCOUNTING_DISPLAY_ON_PK foreign key (PK_ID) references ACCOUNTING_PK(ID);
create index IDX_ACCOUNTING_DISPLAY_ON_PK on ACCOUNTING_DISPLAY (PK_ID);
