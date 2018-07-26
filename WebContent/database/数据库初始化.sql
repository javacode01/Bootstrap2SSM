prompt PL/SQL Developer import file
prompt Created on 2018��7��26�� by Administrator
set feedback off
set define off
prompt Creating DEMO_ORDER...
create table DEMO_ORDER
(
  RECID       VARCHAR2(32) not null,
  ORDER_NO    VARCHAR2(50),
  DESCRIPTION VARCHAR2(200),
  CREATER     VARCHAR2(32),
  CREATE_TIME TIMESTAMP(6),
  UPDATER     VARCHAR2(32),
  UPDATE_TIME TIMESTAMP(6)
)
;
comment on table DEMO_ORDER
  is '������ʾ����';
comment on column DEMO_ORDER.RECID
  is '����id';
comment on column DEMO_ORDER.ORDER_NO
  is '�������';
comment on column DEMO_ORDER.DESCRIPTION
  is '�������';
comment on column DEMO_ORDER.CREATER
  is '������';
comment on column DEMO_ORDER.CREATE_TIME
  is '����ʱ��';
comment on column DEMO_ORDER.UPDATER
  is '������';
comment on column DEMO_ORDER.UPDATE_TIME
  is '����ʱ��';
alter table DEMO_ORDER
  add constraint PK_DEMO_ORDER primary key (RECID);

prompt Creating SYS_ATTACHMENT...
create table SYS_ATTACHMENT
(
  RECID           VARCHAR2(32) not null,
  MODULE          VARCHAR2(100),
  BUSINESSID      VARCHAR2(32),
  TYPE            VARCHAR2(100),
  NAME            VARCHAR2(100),
  PATH            VARCHAR2(200),
  NOTE            VARCHAR2(1000),
  CREATE_TIME     TIMESTAMP(6),
  CREATER         VARCHAR2(32),
  UPDATE_TIME     TIMESTAMP(6),
  UPDATER         VARCHAR2(32),
  ATTACHMENT_SIZE NUMBER(6,2),
  SUFFIX          VARCHAR2(10)
)
;
comment on table SYS_ATTACHMENT
  is 'ϵͳ������';
comment on column SYS_ATTACHMENT.RECID
  is '����ID';
comment on column SYS_ATTACHMENT.MODULE
  is '��������ģ�飬Ҳ������������';
comment on column SYS_ATTACHMENT.BUSINESSID
  is '��������ID';
comment on column SYS_ATTACHMENT.TYPE
  is '�������ͣ�������ҳͼƬ������ҳͼƬ';
comment on column SYS_ATTACHMENT.NAME
  is '��������';
comment on column SYS_ATTACHMENT.PATH
  is '��������·��';
comment on column SYS_ATTACHMENT.NOTE
  is '������ע';
comment on column SYS_ATTACHMENT.CREATE_TIME
  is '�����ϴ�ʱ��';
comment on column SYS_ATTACHMENT.CREATER
  is '�����ϴ���';
comment on column SYS_ATTACHMENT.UPDATE_TIME
  is '��������ʱ��';
comment on column SYS_ATTACHMENT.UPDATER
  is '����������';
comment on column SYS_ATTACHMENT.ATTACHMENT_SIZE
  is '������С';
comment on column SYS_ATTACHMENT.SUFFIX
  is '������׺';
alter table SYS_ATTACHMENT
  add constraint PK_SYS_ATTACHMENT primary key (RECID);

prompt Creating SYS_DICTIONARIES...
create table SYS_DICTIONARIES
(
  RECID    VARCHAR2(32) not null,
  DIC_TYPE VARCHAR2(100),
  DIC_NAME VARCHAR2(100),
  NOTE     VARCHAR2(500)
)
;
comment on table SYS_DICTIONARIES
  is 'ϵͳ�����ֵ�';
comment on column SYS_DICTIONARIES.RECID
  is '��¼ID';
comment on column SYS_DICTIONARIES.DIC_TYPE
  is '�ֵ�����';
comment on column SYS_DICTIONARIES.DIC_NAME
  is '�ֵ�����';
comment on column SYS_DICTIONARIES.NOTE
  is '��ע';
alter table SYS_DICTIONARIES
  add constraint PK_SYS_DICTIONARIES primary key (RECID);

prompt Creating SYS_DICTIONARIES_ITEM...
create table SYS_DICTIONARIES_ITEM
(
  RECID     VARCHAR2(32) not null,
  DIC_TYPE  VARCHAR2(100),
  ITEM_CODE VARCHAR2(100),
  ITEM_NAME VARCHAR2(1000),
  NOTE      VARCHAR2(1000),
  STATUS    CHAR(1),
  SEQ       NUMBER(10)
)
;
comment on table SYS_DICTIONARIES_ITEM
  is '�ֵ������ݱ�';
comment on column SYS_DICTIONARIES_ITEM.RECID
  is '��¼ID';
comment on column SYS_DICTIONARIES_ITEM.DIC_TYPE
  is '�ֵ�����';
comment on column SYS_DICTIONARIES_ITEM.ITEM_CODE
  is '�ֵ�����';
comment on column SYS_DICTIONARIES_ITEM.ITEM_NAME
  is '�ֵ�������';
comment on column SYS_DICTIONARIES_ITEM.NOTE
  is '��ע';
comment on column SYS_DICTIONARIES_ITEM.STATUS
  is '�Ƿ���Ч0����1����';
comment on column SYS_DICTIONARIES_ITEM.SEQ
  is '����';
alter table SYS_DICTIONARIES_ITEM
  add constraint PK_SYS_DICTIONARIES_ITEM primary key (RECID);

prompt Creating SYS_FUNCTIONS...
create table SYS_FUNCTIONS
(
  FUNCTION_ID    VARCHAR2(32) not null,
  FUNCTION_NAME  VARCHAR2(50),
  FUNCTION_ICON  VARCHAR2(50),
  FUNCTION_LEVEL CHAR(1),
  PARENT_CODE    VARCHAR2(20),
  FUNCTION_CODE  VARCHAR2(20) not null,
  FUNCTION_URL   VARCHAR2(100),
  FUNCTION_TYPE  CHAR(1),
  SEQ            INTEGER,
  DEFAULT_ACTION CHAR(1),
  CREATE_TIME    TIMESTAMP(6),
  CREATER        VARCHAR2(32),
  UPDATE_TIME    TIMESTAMP(6),
  UPDATER        VARCHAR2(32)
)
;
comment on table SYS_FUNCTIONS
  is 'ϵͳ���ܱ�';
comment on column SYS_FUNCTIONS.FUNCTION_ID
  is '����ID';
comment on column SYS_FUNCTIONS.FUNCTION_NAME
  is '��������';
comment on column SYS_FUNCTIONS.FUNCTION_ICON
  is '����ͼ��';
comment on column SYS_FUNCTIONS.FUNCTION_LEVEL
  is '���ܼ���1��ģ��2������3������';
comment on column SYS_FUNCTIONS.PARENT_CODE
  is '�ϼ����ܱ���';
comment on column SYS_FUNCTIONS.FUNCTION_CODE
  is '���ܱ���';
comment on column SYS_FUNCTIONS.FUNCTION_URL
  is '��Դ��ַ';
comment on column SYS_FUNCTIONS.FUNCTION_TYPE
  is '��������1����ѯ2������';
comment on column SYS_FUNCTIONS.SEQ
  is '����';
comment on column SYS_FUNCTIONS.DEFAULT_ACTION
  is 'Ĭ�Ϸ���0����1����';
comment on column SYS_FUNCTIONS.CREATE_TIME
  is '����ʱ��';
comment on column SYS_FUNCTIONS.CREATER
  is '������';
comment on column SYS_FUNCTIONS.UPDATE_TIME
  is '�޸�ʱ��';
comment on column SYS_FUNCTIONS.UPDATER
  is '�޸���';
alter table SYS_FUNCTIONS
  add constraint PK_SYS_FUNCTIONS primary key (FUNCTION_ID);

prompt Creating SYS_ORGAN...
create table SYS_ORGAN
(
  ORGAN_ID    VARCHAR2(32) not null,
  ORGAN_CODE  VARCHAR2(20) not null,
  ORGAN_NAME  VARCHAR2(50),
  PARENT_CODE VARCHAR2(20),
  ICON_URL    VARCHAR2(50),
  SEQ         INTEGER,
  ORGAN_LEVEL CHAR(1),
  ORGAN_CLASS CHAR(1),
  ORGAN_TYPE  CHAR(1),
  CREATE_TIME TIMESTAMP(6),
  CREATER     VARCHAR2(32),
  UPDATE_TIME TIMESTAMP(6),
  UPDATER     VARCHAR2(32)
)
;
comment on table SYS_ORGAN
  is 'ϵͳ��֯�ṹ��';
comment on column SYS_ORGAN.ORGAN_ID
  is '��֯����ID';
comment on column SYS_ORGAN.ORGAN_CODE
  is '��֯�������';
comment on column SYS_ORGAN.ORGAN_NAME
  is '��֯��������';
comment on column SYS_ORGAN.PARENT_CODE
  is '�ϼ���֯����';
comment on column SYS_ORGAN.ICON_URL
  is '��֯ͼ��';
comment on column SYS_ORGAN.SEQ
  is '����';
comment on column SYS_ORGAN.ORGAN_LEVEL
  is '����������1��ʡ��2���о�';
comment on column SYS_ORGAN.ORGAN_CLASS
  is '����������1������2���ʼ�';
comment on column SYS_ORGAN.ORGAN_TYPE
  is '��������:��1:��λ2:����';
comment on column SYS_ORGAN.CREATE_TIME
  is '����ʱ��';
comment on column SYS_ORGAN.CREATER
  is '������';
comment on column SYS_ORGAN.UPDATE_TIME
  is '�޸�ʱ��';
comment on column SYS_ORGAN.UPDATER
  is '�޸���';
alter table SYS_ORGAN
  add constraint PK_SYS_ORGAN primary key (ORGAN_ID);

prompt Creating SYS_ORGAN_DESIGN...
create table SYS_ORGAN_DESIGN
(
  RECID            VARCHAR2(32) not null,
  ORGAN_LEVEL      CHAR(1),
  ORGAN_LEVEL_NAME VARCHAR2(200),
  NEXT_LEVEL       VARCHAR2(10),
  HAVE_USER        CHAR(1),
  ICON_URL         VARCHAR2(50)
)
;
comment on table SYS_ORGAN_DESIGN
  is '������֯�ṹ�Ĺ���';
comment on column SYS_ORGAN_DESIGN.RECID
  is '����';
comment on column SYS_ORGAN_DESIGN.ORGAN_LEVEL
  is '��ӦSYS_ORGAN��ORGAN_LEVEL�ֶ�';
comment on column SYS_ORGAN_DESIGN.ORGAN_LEVEL_NAME
  is '��֯��������';
comment on column SYS_ORGAN_DESIGN.NEXT_LEVEL
  is '���������¼�����';
comment on column SYS_ORGAN_DESIGN.HAVE_USER
  is '0����1����';
comment on column SYS_ORGAN_DESIGN.ICON_URL
  is 'Ĭ��ͼ��';
alter table SYS_ORGAN_DESIGN
  add constraint PK_SYS_ORGAN_DESIGN primary key (RECID);

prompt Creating SYS_QUARTZ_JOB...
create table SYS_QUARTZ_JOB
(
  RECID           VARCHAR2(32) not null,
  JOB_NAME        VARCHAR2(100),
  JOB_GROUP_NAME  VARCHAR2(100),
  JOB_CLASS       VARCHAR2(500),
  CRON_EXPRESSION VARCHAR2(100),
  STATUS          VARCHAR2(2),
  REMARK          VARCHAR2(500),
  CREATER         VARCHAR2(32),
  CREATETIME      TIMESTAMP(6),
  UPDATER         VARCHAR2(32),
  UPDATETIME      TIMESTAMP(6)
)
;
comment on table SYS_QUARTZ_JOB
  is '��ʱ�������';
comment on column SYS_QUARTZ_JOB.RECID
  is '��ʱ�����¼ID';
comment on column SYS_QUARTZ_JOB.JOB_NAME
  is '��������';
comment on column SYS_QUARTZ_JOB.JOB_GROUP_NAME
  is '������';
comment on column SYS_QUARTZ_JOB.JOB_CLASS
  is '����ִ����';
comment on column SYS_QUARTZ_JOB.CRON_EXPRESSION
  is '����ʱ��';
comment on column SYS_QUARTZ_JOB.STATUS
  is '״̬';
comment on column SYS_QUARTZ_JOB.REMARK
  is '��ע';
comment on column SYS_QUARTZ_JOB.CREATER
  is '������';
comment on column SYS_QUARTZ_JOB.CREATETIME
  is '����ʱ��';
comment on column SYS_QUARTZ_JOB.UPDATER
  is '�޸���';
comment on column SYS_QUARTZ_JOB.UPDATETIME
  is '�޸�ʱ��';
alter table SYS_QUARTZ_JOB
  add constraint PK_SYS_QUARTZ_JOB primary key (RECID);

prompt Creating SYS_ROLES...
create table SYS_ROLES
(
  ROLE_ID     VARCHAR2(32) not null,
  ROLE_NAME   VARCHAR2(100),
  DESCRIPTION VARCHAR2(200),
  ORGAN_CODE  VARCHAR2(20),
  CREATE_TIME TIMESTAMP(6),
  CREATER     VARCHAR2(32),
  UPDATE_TIME TIMESTAMP(6),
  UPDATER     VARCHAR2(32),
  ROLE_CODE   VARCHAR2(20) not null
)
;
comment on table SYS_ROLES
  is 'ϵͳ��ɫ��';
comment on column SYS_ROLES.ROLE_ID
  is '��ɫID';
comment on column SYS_ROLES.ROLE_NAME
  is '��ɫ��';
comment on column SYS_ROLES.DESCRIPTION
  is '��ɫ����';
comment on column SYS_ROLES.ORGAN_CODE
  is '������֯�ṹ';
comment on column SYS_ROLES.CREATE_TIME
  is '����ʱ��';
comment on column SYS_ROLES.CREATER
  is '������';
comment on column SYS_ROLES.UPDATE_TIME
  is '�޸�ʱ��';
comment on column SYS_ROLES.UPDATER
  is '�޸���';
comment on column SYS_ROLES.ROLE_CODE
  is '��ɫ���';
alter table SYS_ROLES
  add constraint PK_SYS_ROLES primary key (ROLE_ID);

prompt Creating SYS_ROLE_FUNCTION...
create table SYS_ROLE_FUNCTION
(
  ID            VARCHAR2(32) not null,
  ROLE_CODE     VARCHAR2(20),
  FUNCTION_CODE VARCHAR2(20),
  CREATE_TIME   TIMESTAMP(6),
  CREATER       VARCHAR2(32)
)
;
comment on table SYS_ROLE_FUNCTION
  is 'ϵͳ��ɫ���ܹ�ϵ��';
comment on column SYS_ROLE_FUNCTION.ID
  is '��ɫ����ID';
comment on column SYS_ROLE_FUNCTION.ROLE_CODE
  is '��ɫ����';
comment on column SYS_ROLE_FUNCTION.FUNCTION_CODE
  is '���ܱ���';
comment on column SYS_ROLE_FUNCTION.CREATE_TIME
  is '����ʱ��';
comment on column SYS_ROLE_FUNCTION.CREATER
  is '������';
alter table SYS_ROLE_FUNCTION
  add constraint PK_SYS_ROLE_FUNCTION primary key (ID);

prompt Creating SYS_USERS...
create table SYS_USERS
(
  USER_ID      VARCHAR2(32) not null,
  USER_NAME    VARCHAR2(50) not null,
  PASSWORD     VARCHAR2(128) not null,
  STATUS       CHAR(1),
  SYSTEM       CHAR(1),
  LOCK_TIME    TIMESTAMP(6),
  EXPIRED_TIME TIMESTAMP(6),
  ORGAN_CODE   VARCHAR2(20),
  CREATE_TIME  TIMESTAMP(6),
  UPDATE_TIME  TIMESTAMP(6),
  CREATER      VARCHAR2(32),
  UPDATER      VARCHAR2(32),
  NICKNAME     VARCHAR2(50),
  USER_CODE    VARCHAR2(20) not null
)
;
comment on table SYS_USERS
  is 'ϵͳ�û���';
comment on column SYS_USERS.USER_ID
  is '�û�ID';
comment on column SYS_USERS.USER_NAME
  is '�û���';
comment on column SYS_USERS.PASSWORD
  is '����';
comment on column SYS_USERS.STATUS
  is '״̬0��������1������';
comment on column SYS_USERS.SYSTEM
  is '�Ƿ�ϵͳ�û�0����1����';
comment on column SYS_USERS.LOCK_TIME
  is '����ʱ��';
comment on column SYS_USERS.EXPIRED_TIME
  is '����ʱ��';
comment on column SYS_USERS.ORGAN_CODE
  is '������֯����';
comment on column SYS_USERS.CREATE_TIME
  is '����ʱ��';
comment on column SYS_USERS.UPDATE_TIME
  is '�޸�ʱ��';
comment on column SYS_USERS.CREATER
  is '������';
comment on column SYS_USERS.UPDATER
  is '�޸���';
comment on column SYS_USERS.NICKNAME
  is '�ǳ�';
comment on column SYS_USERS.USER_CODE
  is '�û�����';
alter table SYS_USERS
  add constraint PK_SYS_USERS primary key (USER_ID);

prompt Creating SYS_USER_ROLE...
create table SYS_USER_ROLE
(
  ID          VARCHAR2(32) not null,
  USER_CODE   VARCHAR2(20),
  ROLE_CODE   VARCHAR2(20),
  CREATE_TIME TIMESTAMP(6),
  CREATER     VARCHAR2(32)
)
;
comment on table SYS_USER_ROLE
  is '�����û���ɫ��ϵ';
comment on column SYS_USER_ROLE.ID
  is '�û���ɫID';
comment on column SYS_USER_ROLE.USER_CODE
  is '�û�����';
comment on column SYS_USER_ROLE.ROLE_CODE
  is '��ɫ����';
comment on column SYS_USER_ROLE.CREATE_TIME
  is '����ʱ��';
comment on column SYS_USER_ROLE.CREATER
  is '������';
alter table SYS_USER_ROLE
  add constraint PK_SYS_USER_ROLE primary key (ID);

prompt Loading DEMO_ORDER...
prompt Table is empty
prompt Loading SYS_ATTACHMENT...
prompt Table is empty
prompt Loading SYS_DICTIONARIES...
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('a249bc4a95d344b5a65a869cff5d8918', 'ISORNOT', '�Ƿ�', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('47a9fe6ff2e54f259dba2f36ebe90b72', 'FLEVEL', '���ܼ���', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('60f95adaf02c4abf8a1f4df597373041', 'FTYPE', '��������', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('ad862009a0654ddf953bb6ee166fc18d', 'ORGANCLASS', '��������', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('866b97126e614d1e9d71f1c214c240e6', 'ORGANTYPE', '��������', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('5abc36d1b33e4b5d90eda11cf44cd68d', 'USERSSYSTEM', '�Ƿ�ϵͳ�û�', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('36078061cd8e47cc95490897c55db540', 'USERSSTATUS', '�û�״̬', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('0fac0c2a71884287b5ade2dcff2ad80e', 'QUARTZJOBSTATUS', '��ʱ����״̬', null);
commit;
prompt 8 records loaded
prompt Loading SYS_DICTIONARIES_ITEM...
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('dfec3514f1ae4715b547997e96edf845', 'ISORNOT', '0', '��', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('e612a780ee91408585ac5336852a1e9a', 'ISORNOT', '1', '��', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('37d00e367e0247a5a0276d3d941c9140', 'FLEVEL', '2', '����', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('16f4a8ff9ef340828b1fbf8e69071c4a', 'FLEVEL', '3', '����', null, null, 3);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('1aa7563c62284709971752b3f9ff46e4', 'FTYPE', '1', '��ѯ', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('9b709ca220b04a2da828722e7af75f25', 'FTYPE', '2', '����', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('daf8f6d78ece49abb64574cc3f4b4a19', 'FLEVEL', '1', 'ģ��', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('d2192510336342179e7b5d8951326877', 'ORGANCLASS', '1', '��˾', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('d6bb5f9a45ba440a934d33b88c9b67f8', 'ORGANCLASS', '2', '����', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('79546522e8d44abb86f35c1e2fd86a43', 'ORGANTYPE', '1', '��˾', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('3708f99e35e54c9fa36e773d1f937022', 'ORGANTYPE', '2', '����', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('d7ca56bc8a5349b9b6249cd906df6904', 'USERSSTATUS', '0', '����', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('5f93b42fee7d41f9821e9732e5be5ec1', 'USERSSTATUS', '1', '����', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('027ba33b6bfb48eeb66cfffe91b9c565', 'USERSSYSTEM', '0', '��', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('84a641f73a3d4a5a9ac39451af9cbb5b', 'USERSSYSTEM', '1', '��', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('6bcf8ae61c10426ab4adcf55b288ff4e', 'QUARTZJOBSTATUS', 'NONE', '����', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('8e834940bae84ba1ad590a7c7376b82b', 'QUARTZJOBSTATUS', 'NORMAL', '����״̬', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('81f3f8ea96ee4c928ca61eed33090a45', 'QUARTZJOBSTATUS', 'PAUSED', '��ͣ״̬', null, null, 3);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('0283268b51a849919a7b0edc71269dd3', 'QUARTZJOBSTATUS', 'COMPLETE', '���������', null, null, 4);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('c687b285c7db4ce4854e547944fbd66c', 'QUARTZJOBSTATUS', 'BLOCKED', '�߳�����״̬', null, null, 5);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('4d98864322874aac81625b5ebe01b767', 'QUARTZJOBSTATUS', 'ERROR', '���ִ���', null, null, 6);
commit;
prompt 21 records loaded
prompt Loading SYS_FUNCTIONS...
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('74e0d26990d0410cb9d5ebf890009c8b', 'ģ��DEMO', 'fa fa-folder', '1', 'root', '0001', null, null, 1, null, to_timestamp('26-06-2018 13:59:26.107000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-07-2018 18:26:35.292000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('68ae14af2e1f437cbd374141beb74376', '��ѯ', null, '3', '00010001', '000100010001', 'module/demoorder/initManageDemoOrder', '1', 1, '1', to_timestamp('26-06-2018 14:00:27.147000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('29-06-2018 17:39:36.272000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('3F78BF1C4B424F4F9A8E0F2E62183B07', 'ϵͳ����', 'fa fa-th-large', '1', 'root', '0099', null, null, 99, null, to_timestamp('22-11-2017 15:16:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:16:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('C83773F6A9854502A2883DC15ED7B367', '�û�����', 'fa fa-user', '2', '0099', '00990001', null, null, 1, null, to_timestamp('22-11-2017 15:18:49.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('24-12-2017 02:28:32.004000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('A9D81C42E46A44E7802440DBE90618ED', '��ɫ����', 'fa fa-unlock', '2', '0099', '00990002', null, null, 2, null, to_timestamp('22-11-2017 15:19:33.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('24-12-2017 02:28:58.205000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('B51F8D51CB6B4320BB5898C23965F220', '���ܹ���', 'fa fa-wrench', '2', '0099', '00990003', null, null, 3, null, to_timestamp('22-11-2017 15:19:33.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('24-12-2017 02:32:29.577000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('8ABACF0F7A2E433B959F93F1563B765C', '��ѯ', null, '3', '00990001', '009900010001', 'sys/users/initmanagerusers', '1', 1, '1', to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('C0906A8B563946208CE577C6EC06820B', '��ѯ', null, '3', '00990002', '009900020001', 'sys/roles/initmanagerroles', '1', 1, '1', to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('26EF7BFAF2E74427A89E569E1B063A56', '��ѯ', null, '3', '00990003', '009900030001', 'sys/functions/initmanagerfunctions', '1', 1, '1', to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('32B92F3D5194494A981069218CD9BC3D', '�����ֵ����', 'fa fa-book', '2', '0099', '00990004', null, null, 4, null, to_timestamp('22-12-2017 19:17:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('24-12-2017 02:32:50.176000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('1EC561807E9746DE829F420EA48FFEE9', '��ѯ', null, '3', '00990004', '009900040001', 'sys/dictionaries/initmanagerdictionaries', '1', 1, '1', to_timestamp('22-12-2017 19:17:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-12-2017 19:17:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('ab8776f89a1344768af1811b34bbdd0b', '��ʱ�������', 'fa fa-clock-o', '2', '0099', '00990006', null, null, 6, null, to_timestamp('11-03-2018 13:47:49.176000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('11-03-2018 13:55:20.669000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('a13a6812a6fd41af9ca2e6aa23d8f32a', '��ѯ', null, '3', '00990006', '009900060001', 'sys/quartzjob/initmanagerquartzjob', '1', 1, '1', to_timestamp('11-03-2018 13:58:32.760000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('11-03-2018 13:58:32.760000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('d64817bfe00a4aae974fef29b2c1763c', '����DEMO', 'fa fa-folder', '2', '0001', '00010001', null, null, 1, null, to_timestamp('26-06-2018 13:59:47.055000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-07-2018 18:27:04.286000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('507f3311980143ceb1459962b6f3a97e', '��֯�ṹ����', 'fa fa-sitemap', '1', '0099', '00990005', null, null, 5, null, to_timestamp('25-07-2018 11:53:40.818000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('25-07-2018 11:53:40.818000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('51e0de47c2444cb39fb699a7a29fe562', '��֯�ṹά��', 'fa fa-sitemap', '2', '00990005', '009900050001', null, null, 1, null, to_timestamp('25-07-2018 11:57:48.444000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('25-07-2018 11:57:48.444000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('153e9fe6d8b54b3e843bdf72d44beefe', '��ѯ', null, '3', '009900050001', '0099000500010001', 'sys/organ/initmanagerorgan', '1', 1, '1', to_timestamp('25-07-2018 11:58:33.773000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('25-07-2018 11:58:33.773000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('1d335c186631410da41dbdfa927f4d4d', '������ϵ����', 'fa fa-bars', '2', '00990005', '009900050002', null, null, 2, null, to_timestamp('25-07-2018 12:00:35.728000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('25-07-2018 12:00:35.728000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('f4d56bc5979f4460ba51e2d0e7628c24', '��ѯ', null, '3', '009900050002', '0099000500020001', 'sys/sysorgandesign/initManageSysOrganDesign', '1', 1, '1', to_timestamp('25-07-2018 12:02:36.370000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('25-07-2018 12:02:36.370000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
commit;
prompt 19 records loaded
prompt Loading SYS_ORGAN...
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('ecbad8f195c242508da418103b25cd79', '370100001', '������������', '370100', 'fa fa-tag', 1, '5', null, null, to_timestamp('26-07-2018 14:20:33.791000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('26-07-2018 14:20:33.791000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('d26f424179fc49b6a7b7fc5e424bc62a', '370000', 'ɽ��ʡ���̾�', 'root', 'fa fa-university', 1, '1', null, null, to_timestamp('26-07-2018 13:29:37.459000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('26-07-2018 13:29:37.459000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('69a6b1e6543047a6b03deff852517eef', '370100', '�����й��̾�', '370000', 'fa fa-home', 1, '2', null, null, to_timestamp('26-07-2018 13:34:59.156000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('26-07-2018 14:01:06.980000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('b5b549b8f7b5464185e678322fbf6c4c', '370200', '�ൺ�й��̾�', '370000', 'fa fa-home', 2, '2', null, null, to_timestamp('26-07-2018 13:35:55.342000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('26-07-2018 13:35:55.342000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
commit;
prompt 4 records loaded
prompt Loading SYS_ORGAN_DESIGN...
insert into SYS_ORGAN_DESIGN (RECID, ORGAN_LEVEL, ORGAN_LEVEL_NAME, NEXT_LEVEL, HAVE_USER, ICON_URL)
values ('3b393dff183f4c22aed1d881c598ed13', '3', '�־�', '4,5', '0', 'fa fa-suitcase');
insert into SYS_ORGAN_DESIGN (RECID, ORGAN_LEVEL, ORGAN_LEVEL_NAME, NEXT_LEVEL, HAVE_USER, ICON_URL)
values ('cb3354ce3ed447ea97cb3a6090de04af', '4', '������', null, '1', 'fa fa-flag');
insert into SYS_ORGAN_DESIGN (RECID, ORGAN_LEVEL, ORGAN_LEVEL_NAME, NEXT_LEVEL, HAVE_USER, ICON_URL)
values ('b365ab939c6446978f1c1c80fc1b6334', '5', '����', null, '1', 'fa fa-tag');
insert into SYS_ORGAN_DESIGN (RECID, ORGAN_LEVEL, ORGAN_LEVEL_NAME, NEXT_LEVEL, HAVE_USER, ICON_URL)
values ('cdf10b3a3dfc45d4bda73a23b439c41d', '1', 'ʡ��', '2,4,5', '0', 'fa fa-university');
insert into SYS_ORGAN_DESIGN (RECID, ORGAN_LEVEL, ORGAN_LEVEL_NAME, NEXT_LEVEL, HAVE_USER, ICON_URL)
values ('68337a6251d84e969879f6b31f0503f3', '2', '�о�', '3,4,5', '0', 'fa fa-home');
commit;
prompt 5 records loaded
prompt Loading SYS_QUARTZ_JOB...
insert into SYS_QUARTZ_JOB (RECID, JOB_NAME, JOB_GROUP_NAME, JOB_CLASS, CRON_EXPRESSION, STATUS, REMARK, CREATER, CREATETIME, UPDATER, UPDATETIME)
values ('58b9878cf6d348b28d6a89614571fbf1', '��ʱ�������', '1', 'com.sys.utils.schedule.QuartzJob', '0/2 * * * * ?', null, '����', '1', to_timestamp('11-03-2018 20:51:02.757000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('11-03-2018 21:01:26.290000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 1 records loaded
prompt Loading SYS_ROLES...
insert into SYS_ROLES (ROLE_ID, ROLE_NAME, DESCRIPTION, ORGAN_CODE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER, ROLE_CODE)
values ('FD5E07609C0940DAB801A575C88B422F', '���Խ�ɫ', '���ڹ��ܲ���', null, to_timestamp('22-11-2017 15:26:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:26:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0001');
insert into SYS_ROLES (ROLE_ID, ROLE_NAME, DESCRIPTION, ORGAN_CODE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER, ROLE_CODE)
values ('8920c67439b44c29a195a0b4310c09dd', '����', null, null, to_timestamp('24-12-2017 18:15:05.445000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 18:15:05.445000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', '0002');
commit;
prompt 2 records loaded
prompt Loading SYS_ROLE_FUNCTION...
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('a5beeb7ef8294f638e0f59d57ec59d67', '0001', '00990001', to_timestamp('26-07-2018 17:13:37.291000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('4ee31f67a20e4ee1917ce1440806a99e', '0001', '00990003', to_timestamp('26-07-2018 17:13:37.298000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('eadf3f612ac545da89f6b3ab0f4c1e1a', '0001', '00990002', to_timestamp('26-07-2018 17:13:37.310000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('32a6425de52f439bbd2e7410556b654e', '0001', '009900040001', to_timestamp('26-07-2018 17:13:37.320000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('6421cde22575457cbd160fbba9497830', '0001', '00990005', to_timestamp('26-07-2018 17:13:37.327000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('b59bb8d24f8b4375b81b415255342cda', '0001', '009900060001', to_timestamp('26-07-2018 17:13:37.332000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('f49c06000a6c41ecb09b021572ddb17a', '0001', '00990004', to_timestamp('26-07-2018 17:13:37.341000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('a66a404ab6764338bb6bea02d9570332', '0001', '0099000500010001', to_timestamp('26-07-2018 17:13:37.349000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('39179f578fcf4501b1c8e8eb481b35ce', '0001', '000100010001', to_timestamp('26-07-2018 17:13:37.355000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('59ecec485fae46eb9950cbac4f64d877', '0001', '0099000500020001', to_timestamp('26-07-2018 17:13:37.360000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('f7da6774f121431682381e32de50f6e5', '0001', '00990006', to_timestamp('26-07-2018 17:13:37.366000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('1c8e20b386254ff1a7200a7514b02e2a', '0001', '009900020001', to_timestamp('26-07-2018 17:13:37.372000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('6053db23ce8b4522b10851f2548c6fb9', '0001', '009900050001', to_timestamp('26-07-2018 17:13:37.378000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('3cf0ee4117f04f8e85c11710f36a8e11', '0001', '009900050002', to_timestamp('26-07-2018 17:13:37.384000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('2e731a6f226a41dd8ef81cba1b0563b8', '0001', '0099', to_timestamp('26-07-2018 17:13:37.390000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('3d71408f34b14c1283cc55a3f44c8973', '0001', '0001', to_timestamp('26-07-2018 17:13:37.395000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('85b1ceb340074d14b4bc71f88fc8ce70', '0001', '00010001', to_timestamp('26-07-2018 17:13:37.401000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('66f273db7351479c86e607dba041d2c4', '0001', '009900030001', to_timestamp('26-07-2018 17:13:37.407000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('8129ea9290284f3b8b140632282932ce', '0001', '009900010001', to_timestamp('26-07-2018 17:13:37.412000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('3cca9adfa76c42f58c4602483b46e898', '0002', '00990002', to_timestamp('26-07-2018 17:13:55.366000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('828c9c81dfd141e084ec075619f0498f', '0002', '0099', to_timestamp('26-07-2018 17:13:55.375000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('1ecff413e3d04490a63b95e1da1de671', '0002', '0001', to_timestamp('26-07-2018 17:13:55.383000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('bc1c4a53faa448dbae2591b645314530', '0002', '00010001', to_timestamp('26-07-2018 17:13:55.388000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('17988c8363364d69a002167f19737b78', '0002', '000100010001', to_timestamp('26-07-2018 17:13:55.395000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('5ffc796c005d4c95849e2378d044a854', '0002', '009900020001', to_timestamp('26-07-2018 17:13:55.402000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('fd6258110dcd4157827d6b808ffead97', '0002', '009900010001', to_timestamp('26-07-2018 17:13:55.408000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('4c5671fcb5484aa081b191327b803b7d', '0002', '00990001', to_timestamp('26-07-2018 17:13:55.359000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
commit;
prompt 27 records loaded
prompt Loading SYS_USERS...
insert into SYS_USERS (USER_ID, USER_NAME, PASSWORD, STATUS, SYSTEM, LOCK_TIME, EXPIRED_TIME, ORGAN_CODE, CREATE_TIME, UPDATE_TIME, CREATER, UPDATER, NICKNAME, USER_CODE)
values ('1', 'admin', '96e79218965eb72c92a549dd5a330112', null, null, null, null, null, null, to_timestamp('31-12-2017 17:37:46.258000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '1', '�û�1', '1');
insert into SYS_USERS (USER_ID, USER_NAME, PASSWORD, STATUS, SYSTEM, LOCK_TIME, EXPIRED_TIME, ORGAN_CODE, CREATE_TIME, UPDATE_TIME, CREATER, UPDATER, NICKNAME, USER_CODE)
values ('99b35d386e024c71aa5e06f28add8eac', 'login1', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, null, '370100001', to_timestamp('26-07-2018 15:27:46.805000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('26-07-2018 15:27:46.805000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', '1', '����1', '0001');
commit;
prompt 2 records loaded
prompt Loading SYS_USER_ROLE...
insert into SYS_USER_ROLE (ID, USER_CODE, ROLE_CODE, CREATE_TIME, CREATER)
values ('C37ED3DFDD0C4143897FDC24C0B4DD9D', '1', '0001', to_timestamp('25-12-2017 18:28:39.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_USER_ROLE (ID, USER_CODE, ROLE_CODE, CREATE_TIME, CREATER)
values ('d65636658932451a84670b19e5d03ca1', '0001', '0002', to_timestamp('26-07-2018 17:14:14.654000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
commit;
prompt 2 records loaded
set feedback on
set define on
prompt Done.
