alter table ACCOUNTING_NETWORK_INTERFACE add constraint FK_ACCOUNTING_NETWORK_INTERFACE_ON_PK foreign key (PK_ID) references ACCOUNTING_PK(ID);
create index IDX_ACCOUNTING_NETWORK_INTERFACE_ON_PK on ACCOUNTING_NETWORK_INTERFACE (PK_ID);