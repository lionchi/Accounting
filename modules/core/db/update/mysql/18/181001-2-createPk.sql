alter table ACCOUNTING_PK add constraint FK_ACCOUNTING_PK_ON_CPU foreign key (CPU_ID) references ACCOUNTING_CPU(ID);
create index IDX_ACCOUNTING_PK_ON_CPU on ACCOUNTING_PK (CPU_ID);
