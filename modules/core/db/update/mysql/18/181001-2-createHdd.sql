alter table ACCOUNTING_HDD add constraint FK_ACCOUNTING_HDD_ON_PK foreign key (PK_ID) references ACCOUNTING_PK(ID);
create index IDX_ACCOUNTING_HDD_ON_PK on ACCOUNTING_HDD (PK_ID);
