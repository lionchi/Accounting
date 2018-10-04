-- begin ACCOUNTING_DISPLAY_ENTITY
alter table ACCOUNTING_DISPLAY_ENTITY add constraint FK_ACCOUNTING_DISPLAY_ENTITY_ON_PK foreign key (PK_ID) references ACCOUNTING_PK_ENTITY(ID) on delete SET NULL^
create index IDX_ACCOUNTING_DISPLAY_ENTITY_ON_PK on ACCOUNTING_DISPLAY_ENTITY (PK_ID)^
-- end ACCOUNTING_DISPLAY_ENTITY
-- begin ACCOUNTING_HDD_ENTITY
alter table ACCOUNTING_HDD_ENTITY add constraint FK_ACCOUNTING_HDD_ENTITY_ON_PK foreign key (PK_ID) references ACCOUNTING_PK_ENTITY(ID) on delete SET NULL^
create index IDX_ACCOUNTING_HDD_ENTITY_ON_PK on ACCOUNTING_HDD_ENTITY (PK_ID)^
-- end ACCOUNTING_HDD_ENTITY
-- begin ACCOUNTING_NETWORK_INTERFACE_ENTITY
alter table ACCOUNTING_NETWORK_INTERFACE_ENTITY add constraint FK_ACCOUNTING_NETWORK_INTERFACE_ENTITY_ON_PK foreign key (PK_ID) references ACCOUNTING_PK_ENTITY(ID) on delete SET NULL^
create index IDX_ACCOUNTING_NETWORK_INTERFACE_ENTITY_ON_PK on ACCOUNTING_NETWORK_INTERFACE_ENTITY (PK_ID)^
-- end ACCOUNTING_NETWORK_INTERFACE_ENTITY
-- begin ACCOUNTING_PK_ENTITY
alter table ACCOUNTING_PK_ENTITY add constraint FK_ACCOUNTING_PK_ENTITY_ON_CPU foreign key (CPU_ID) references ACCOUNTING_CPU_ENTITY(ID) on delete SET NULL^
create index IDX_ACCOUNTING_PK_ENTITY_ON_CPU on ACCOUNTING_PK_ENTITY (CPU_ID)^
-- end ACCOUNTING_PK_ENTITY
-- begin ACCOUNTING_MOTHERBOARD_ENTITY
alter table ACCOUNTING_MOTHERBOARD_ENTITY add constraint FK_ACCOUNTING_MOTHERBOARD_ENTITY_ON_PK foreign key (PK_ID) references ACCOUNTING_PK_ENTITY(ID) on delete SET NULL^
create index IDX_ACCOUNTING_MOTHERBOARD_ENTITY_ON_PK on ACCOUNTING_MOTHERBOARD_ENTITY (PK_ID)^
-- end ACCOUNTING_MOTHERBOARD_ENTITY
-- begin ACCOUNTING_VIDEO_CARD_ENTITY
alter table ACCOUNTING_VIDEO_CARD_ENTITY add constraint FK_ACCOUNTING_VIDEO_CARD_ENTITY_ON_PK_ENTITY foreign key (PK_ENTITY_ID) references ACCOUNTING_PK_ENTITY(ID) on delete SET NULL^
create index IDX_ACCOUNTING_VIDEO_CARD_ENTITY_ON_PK_ENTITY on ACCOUNTING_VIDEO_CARD_ENTITY (PK_ENTITY_ID)^
-- end ACCOUNTING_VIDEO_CARD_ENTITY
