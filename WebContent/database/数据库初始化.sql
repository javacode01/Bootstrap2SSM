prompt PL/SQL Developer import file
prompt Created on 2018年2月2日 by Administrator
set feedback off
set define off
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
  is '系统附件表';
comment on column SYS_ATTACHMENT.RECID
  is '附件ID';
comment on column SYS_ATTACHMENT.MODULE
  is '附件所属模块，也可以是所属表';
comment on column SYS_ATTACHMENT.BUSINESSID
  is '关联主表ID';
comment on column SYS_ATTACHMENT.TYPE
  is '附件类型，比如首页图片，详情页图片';
comment on column SYS_ATTACHMENT.NAME
  is '附件名称';
comment on column SYS_ATTACHMENT.PATH
  is '附件保存路径';
comment on column SYS_ATTACHMENT.NOTE
  is '附件备注';
comment on column SYS_ATTACHMENT.CREATE_TIME
  is '附件上传时间';
comment on column SYS_ATTACHMENT.CREATER
  is '附件上传人';
comment on column SYS_ATTACHMENT.UPDATE_TIME
  is '附件更新时间';
comment on column SYS_ATTACHMENT.UPDATER
  is '附件更新人';
comment on column SYS_ATTACHMENT.ATTACHMENT_SIZE
  is '附件大小';
comment on column SYS_ATTACHMENT.SUFFIX
  is '附件后缀';
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
  is '系统数据字典';
comment on column SYS_DICTIONARIES.RECID
  is '记录ID';
comment on column SYS_DICTIONARIES.DIC_TYPE
  is '字典类型';
comment on column SYS_DICTIONARIES.DIC_NAME
  is '字典名称';
comment on column SYS_DICTIONARIES.NOTE
  is '备注';
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
  is '字典项数据表';
comment on column SYS_DICTIONARIES_ITEM.RECID
  is '记录ID';
comment on column SYS_DICTIONARIES_ITEM.DIC_TYPE
  is '字典类型';
comment on column SYS_DICTIONARIES_ITEM.ITEM_CODE
  is '字典项编号';
comment on column SYS_DICTIONARIES_ITEM.ITEM_NAME
  is '字典项名称';
comment on column SYS_DICTIONARIES_ITEM.NOTE
  is '备注';
comment on column SYS_DICTIONARIES_ITEM.STATUS
  is '是否有效0：否1：是';
comment on column SYS_DICTIONARIES_ITEM.SEQ
  is '排序';
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
  is '系统功能表';
comment on column SYS_FUNCTIONS.FUNCTION_ID
  is '功能ID';
comment on column SYS_FUNCTIONS.FUNCTION_NAME
  is '功能名称';
comment on column SYS_FUNCTIONS.FUNCTION_ICON
  is '功能图标';
comment on column SYS_FUNCTIONS.FUNCTION_LEVEL
  is '功能级别1：模块2：功能3：操作';
comment on column SYS_FUNCTIONS.PARENT_CODE
  is '上级功能编码';
comment on column SYS_FUNCTIONS.FUNCTION_CODE
  is '功能编码';
comment on column SYS_FUNCTIONS.FUNCTION_URL
  is '资源地址';
comment on column SYS_FUNCTIONS.FUNCTION_TYPE
  is '功能类型1：查询2：操作';
comment on column SYS_FUNCTIONS.SEQ
  is '排序';
comment on column SYS_FUNCTIONS.DEFAULT_ACTION
  is '默认访问0：否，1：是';
comment on column SYS_FUNCTIONS.CREATE_TIME
  is '创建时间';
comment on column SYS_FUNCTIONS.CREATER
  is '创建人';
comment on column SYS_FUNCTIONS.UPDATE_TIME
  is '修改时间';
comment on column SYS_FUNCTIONS.UPDATER
  is '修改人';
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
  ORGAN_CLASS CHAR(2),
  ORGAN_TYPE  CHAR(1),
  CREATE_TIME TIMESTAMP(6),
  CREATER     VARCHAR2(32),
  UPDATE_TIME TIMESTAMP(6),
  UPDATER     VARCHAR2(32)
)
;
comment on table SYS_ORGAN
  is '系统组织结构表';
comment on column SYS_ORGAN.ORGAN_ID
  is '组织机构ID';
comment on column SYS_ORGAN.ORGAN_CODE
  is '组织机构编号';
comment on column SYS_ORGAN.ORGAN_NAME
  is '组织机构名称';
comment on column SYS_ORGAN.PARENT_CODE
  is '上级组织机构';
comment on column SYS_ORGAN.ICON_URL
  is '组织图标';
comment on column SYS_ORGAN.SEQ
  is '排序';
comment on column SYS_ORGAN.ORGAN_LEVEL
  is '机构级别如1：省局2：市局';
comment on column SYS_ORGAN.ORGAN_CLASS
  is '机构类型如1：工商2：质检';
comment on column SYS_ORGAN.ORGAN_TYPE
  is '机构类型:如1:单位2:部门';
comment on column SYS_ORGAN.CREATE_TIME
  is '创建时间';
comment on column SYS_ORGAN.CREATER
  is '创建人';
comment on column SYS_ORGAN.UPDATE_TIME
  is '修改时间';
comment on column SYS_ORGAN.UPDATER
  is '修改人';
alter table SYS_ORGAN
  add constraint PK_SYS_ORGAN primary key (ORGAN_ID);

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
  is '系统角色表';
comment on column SYS_ROLES.ROLE_ID
  is '角色ID';
comment on column SYS_ROLES.ROLE_NAME
  is '角色名';
comment on column SYS_ROLES.DESCRIPTION
  is '角色描述';
comment on column SYS_ROLES.ORGAN_CODE
  is '所属组织结构';
comment on column SYS_ROLES.CREATE_TIME
  is '创建时间';
comment on column SYS_ROLES.CREATER
  is '创建人';
comment on column SYS_ROLES.UPDATE_TIME
  is '修改时间';
comment on column SYS_ROLES.UPDATER
  is '修改人';
comment on column SYS_ROLES.ROLE_CODE
  is '角色编号';
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
  is '系统角色功能关系表';
comment on column SYS_ROLE_FUNCTION.ID
  is '角色功能ID';
comment on column SYS_ROLE_FUNCTION.ROLE_CODE
  is '角色编码';
comment on column SYS_ROLE_FUNCTION.FUNCTION_CODE
  is '功能编码';
comment on column SYS_ROLE_FUNCTION.CREATE_TIME
  is '创建时间';
comment on column SYS_ROLE_FUNCTION.CREATER
  is '创建人';
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
  is '系统用户表';
comment on column SYS_USERS.USER_ID
  is '用户ID';
comment on column SYS_USERS.USER_NAME
  is '用户名';
comment on column SYS_USERS.PASSWORD
  is '密码';
comment on column SYS_USERS.STATUS
  is '状态0：锁定，1：正常';
comment on column SYS_USERS.SYSTEM
  is '是否系统用户0：否，1：是';
comment on column SYS_USERS.LOCK_TIME
  is '锁定时间';
comment on column SYS_USERS.EXPIRED_TIME
  is '逾期时间';
comment on column SYS_USERS.ORGAN_CODE
  is '所属组织机构';
comment on column SYS_USERS.CREATE_TIME
  is '创建时间';
comment on column SYS_USERS.UPDATE_TIME
  is '修改时间';
comment on column SYS_USERS.CREATER
  is '创建人';
comment on column SYS_USERS.UPDATER
  is '修改人';
comment on column SYS_USERS.NICKNAME
  is '昵称';
comment on column SYS_USERS.USER_CODE
  is '用户编码';
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
  is '保存用户角色关系';
comment on column SYS_USER_ROLE.ID
  is '用户角色ID';
comment on column SYS_USER_ROLE.USER_CODE
  is '用户编码';
comment on column SYS_USER_ROLE.ROLE_CODE
  is '角色编码';
comment on column SYS_USER_ROLE.CREATE_TIME
  is '创建时间';
comment on column SYS_USER_ROLE.CREATER
  is '创建人';
alter table SYS_USER_ROLE
  add constraint PK_SYS_USER_ROLE primary key (ID);

prompt Loading SYS_ATTACHMENT...
prompt Table is empty
prompt Loading SYS_DICTIONARIES...
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('a249bc4a95d344b5a65a869cff5d8918', 'ISORNOT', '是否', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('47a9fe6ff2e54f259dba2f36ebe90b72', 'FLEVEL', '功能级别', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('60f95adaf02c4abf8a1f4df597373041', 'FTYPE', '功能类型', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('2e6f4e4cb6734dadbc52d8437886a7fe', 'ORGANLEVEL', '机构级别', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('ad862009a0654ddf953bb6ee166fc18d', 'ORGANCLASS', '机构类型', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('866b97126e614d1e9d71f1c214c240e6', 'ORGANTYPE', '机构类型', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('5abc36d1b33e4b5d90eda11cf44cd68d', 'USERSSYSTEM', '是否系统用户', null);
insert into SYS_DICTIONARIES (RECID, DIC_TYPE, DIC_NAME, NOTE)
values ('36078061cd8e47cc95490897c55db540', 'USERSSTATUS', '用户状态', null);
commit;
prompt 8 records loaded
prompt Loading SYS_DICTIONARIES_ITEM...
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('dfec3514f1ae4715b547997e96edf845', 'ISORNOT', '0', '否', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('e612a780ee91408585ac5336852a1e9a', 'ISORNOT', '1', '是', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('37d00e367e0247a5a0276d3d941c9140', 'FLEVEL', '2', '功能', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('16f4a8ff9ef340828b1fbf8e69071c4a', 'FLEVEL', '3', '操作', null, null, 3);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('1aa7563c62284709971752b3f9ff46e4', 'FTYPE', '1', '查询', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('9b709ca220b04a2da828722e7af75f25', 'FTYPE', '2', '操作', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('daf8f6d78ece49abb64574cc3f4b4a19', 'FLEVEL', '1', '模块', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('66a8bfcc9a4f4dcb8bc0c02d8f88a85e', 'ORGANLEVEL', '1', '总公司', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('bc8196cbfa724bceae1518784772b298', 'ORGANLEVEL', '2', '分公司', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('97156546dd2a49ae839a43c0d1bb3b81', 'ORGANLEVEL', '3', '商务部', null, null, 3);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('617b37ae6d634926a7677d6455ee4143', 'ORGANLEVEL', '4', '财务部', null, null, 4);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('f6029e888cc545bf9b14719e2f62135f', 'ORGANLEVEL', '5', '后勤部', null, null, 5);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('d2192510336342179e7b5d8951326877', 'ORGANCLASS', '1', '公司', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('d6bb5f9a45ba440a934d33b88c9b67f8', 'ORGANCLASS', '2', '集团', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('79546522e8d44abb86f35c1e2fd86a43', 'ORGANTYPE', '1', '公司', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('3708f99e35e54c9fa36e773d1f937022', 'ORGANTYPE', '2', '部门', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('d7ca56bc8a5349b9b6249cd906df6904', 'USERSSTATUS', '0', '锁定', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('5f93b42fee7d41f9821e9732e5be5ec1', 'USERSSTATUS', '1', '正常', null, null, 2);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('027ba33b6bfb48eeb66cfffe91b9c565', 'USERSSYSTEM', '0', '否', null, null, 1);
insert into SYS_DICTIONARIES_ITEM (RECID, DIC_TYPE, ITEM_CODE, ITEM_NAME, NOTE, STATUS, SEQ)
values ('84a641f73a3d4a5a9ac39451af9cbb5b', 'USERSSYSTEM', '1', '是', null, null, 2);
commit;
prompt 20 records loaded
prompt Loading SYS_FUNCTIONS...
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('3F78BF1C4B424F4F9A8E0F2E62183B07', '系统管理', 'fa fa-th-large', '1', 'root', '0099', null, null, 99, null, to_timestamp('22-11-2017 15:16:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:16:35.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('C83773F6A9854502A2883DC15ED7B367', '用户管理', 'fa fa-user', '2', '0099', '00990001', null, null, 1, null, to_timestamp('22-11-2017 15:18:49.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('24-12-2017 02:28:32.004000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('A9D81C42E46A44E7802440DBE90618ED', '角色管理', 'fa fa-unlock', '2', '0099', '00990002', null, null, 2, null, to_timestamp('22-11-2017 15:19:33.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('24-12-2017 02:28:58.205000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('B51F8D51CB6B4320BB5898C23965F220', '功能管理', 'fa fa-wrench', '2', '0099', '00990003', null, null, 3, null, to_timestamp('22-11-2017 15:19:33.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('24-12-2017 02:32:29.577000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('8ABACF0F7A2E433B959F93F1563B765C', '查询', null, '3', '00990001', '009900010001', 'sys/users/initmanagerusers', '1', 1, '1', to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('C0906A8B563946208CE577C6EC06820B', '查询', null, '3', '00990002', '009900020001', 'sys/roles/initmanagerroles', '1', 1, '1', to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('26EF7BFAF2E74427A89E569E1B063A56', '查询', null, '3', '00990003', '009900030001', 'sys/functions/initmanagerfunctions', '1', 1, '1', to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:23:07.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('32B92F3D5194494A981069218CD9BC3D', '数据字典管理', 'fa fa-book', '2', '0099', '00990004', null, null, 4, null, to_timestamp('22-12-2017 19:17:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('24-12-2017 02:32:50.176000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('1EC561807E9746DE829F420EA48FFEE9', '查询', null, '3', '00990004', '009900040001', 'sys/dictionaries/initmanagerdictionaries', '1', 1, '1', to_timestamp('22-12-2017 19:17:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-12-2017 19:17:53.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('72086f4c94cd469ca9c65b5c750a7f68', '组织结构管理', 'fa fa-sitemap', '2', '0099', '00990005', null, null, 5, null, to_timestamp('24-12-2017 02:58:57.262000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 02:58:57.262000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_FUNCTIONS (FUNCTION_ID, FUNCTION_NAME, FUNCTION_ICON, FUNCTION_LEVEL, PARENT_CODE, FUNCTION_CODE, FUNCTION_URL, FUNCTION_TYPE, SEQ, DEFAULT_ACTION, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('fc962c16a18b472492cb173ca361d679', '查询', null, '3', '00990005', '009900050001', 'sys/organ/initmanagerorgan', '1', 1, '1', to_timestamp('24-12-2017 03:00:34.336000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 03:00:34.336000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
commit;
prompt 11 records loaded
prompt Loading SYS_ORGAN...
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('56f7d190549e4d2994314cd5ddf20e5f', '000100010002', '财务1部', '00010001', null, 2, '4', null, '2', to_timestamp('24-12-2017 21:58:36.517000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 21:58:36.517000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('28f5279e437f44eb8ebc7d80efdabcb4', '000100010003', '后勤1部', '00010001', null, 3, '5', null, '2', to_timestamp('24-12-2017 21:59:17.356000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 21:59:17.356000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('bbb730278da444ea96d65476fd991c76', '000100010004', '商务2部', '00010001', null, 4, '3', null, '2', to_timestamp('24-12-2017 22:04:07.312000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 22:04:17.278000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('19fd5e58816f4ea69f1bd341ddc8bc50', '0001', '有钱人的公司', 'root', null, 1, '1', null, '1', to_timestamp('24-12-2017 21:55:06.709000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 22:00:24.386000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('da514ada0cea41e39d6a0fdc5f30f860', '00010001', '有钱人的济南分公司', '0001', null, 1, '2', null, '1', to_timestamp('24-12-2017 21:56:53.278000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 21:59:57.633000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ORGAN (ORGAN_ID, ORGAN_CODE, ORGAN_NAME, PARENT_CODE, ICON_URL, SEQ, ORGAN_LEVEL, ORGAN_CLASS, ORGAN_TYPE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER)
values ('e10d1c6ebaea45e29f56b25ff40e8182', '000100010001', '商务1部', '00010001', null, 1, '3', null, '2', to_timestamp('24-12-2017 21:57:42.438000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 21:57:51.690000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
commit;
prompt 6 records loaded
prompt Loading SYS_ROLES...
insert into SYS_ROLES (ROLE_ID, ROLE_NAME, DESCRIPTION, ORGAN_CODE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER, ROLE_CODE)
values ('FD5E07609C0940DAB801A575C88B422F', '测试角色', '用于功能测试', null, to_timestamp('22-11-2017 15:26:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('22-11-2017 15:26:30.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '0001');
insert into SYS_ROLES (ROLE_ID, ROLE_NAME, DESCRIPTION, ORGAN_CODE, CREATE_TIME, CREATER, UPDATE_TIME, UPDATER, ROLE_CODE)
values ('8920c67439b44c29a195a0b4310c09dd', '测试', null, null, to_timestamp('24-12-2017 18:15:05.445000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', to_timestamp('24-12-2017 18:15:05.445000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', '0002');
commit;
prompt 2 records loaded
prompt Loading SYS_ROLE_FUNCTION...
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('56c4185401694e3a80afc532b54591a5', '0001', '009900050001', to_timestamp('26-12-2017 20:19:18.341000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('26760d3e8a604afb9a1989ed7953fd91', '0001', '009900040001', to_timestamp('26-12-2017 20:19:18.390000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('57f7da1dea4c4fd19ee6566f5f18cd36', '0001', '009900020001', to_timestamp('26-12-2017 20:19:18.400000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('e02c2eeceab9424492fef666fc796cd5', '0001', '009900030001', to_timestamp('26-12-2017 20:19:18.406000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('91f68009b1484e0b8d32e8de47890286', '0001', '009900010001', to_timestamp('26-12-2017 20:19:18.420000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('b7dd637735094cd291eb35b730e6d949', '0002', '009900050001', to_timestamp('26-12-2017 20:19:26.801000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('1efb68bf0f7341c38145558e44f53358', '0002', '009900040001', to_timestamp('26-12-2017 20:19:26.810000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('7cf584377dc248a0966f13c47113a87e', '0002', '009900020001', to_timestamp('26-12-2017 20:19:26.814000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('c6d5fc33b8734f9b9cb66f41a70f59ea', '0002', '009900030001', to_timestamp('26-12-2017 20:19:26.820000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
insert into SYS_ROLE_FUNCTION (ID, ROLE_CODE, FUNCTION_CODE, CREATE_TIME, CREATER)
values ('19e781e96c334be9baf30087f86f88e3', '0002', '009900010001', to_timestamp('26-12-2017 20:19:26.825000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
commit;
prompt 10 records loaded
prompt Loading SYS_USERS...
insert into SYS_USERS (USER_ID, USER_NAME, PASSWORD, STATUS, SYSTEM, LOCK_TIME, EXPIRED_TIME, ORGAN_CODE, CREATE_TIME, UPDATE_TIME, CREATER, UPDATER, NICKNAME, USER_CODE)
values ('1', 'admin', '96e79218965eb72c92a549dd5a330112', null, null, null, null, null, null, to_timestamp('31-12-2017 17:37:46.258000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '1', '用户1', '1');
insert into SYS_USERS (USER_ID, USER_NAME, PASSWORD, STATUS, SYSTEM, LOCK_TIME, EXPIRED_TIME, ORGAN_CODE, CREATE_TIME, UPDATE_TIME, CREATER, UPDATER, NICKNAME, USER_CODE)
values ('bcbb6dc97ed14f33b29fa4240c02e3de', 'login1', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, null, '000100010001', to_timestamp('26-12-2017 08:56:44.927000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('26-12-2017 08:56:44.927000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1', '1', '商务部用户1', '0001');
commit;
prompt 2 records loaded
prompt Loading SYS_USER_ROLE...
insert into SYS_USER_ROLE (ID, USER_CODE, ROLE_CODE, CREATE_TIME, CREATER)
values ('C37ED3DFDD0C4143897FDC24C0B4DD9D', '1', '0001', to_timestamp('25-12-2017 18:28:39.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_USER_ROLE (ID, USER_CODE, ROLE_CODE, CREATE_TIME, CREATER)
values ('e148c177ce8a41ae9d6bc2100717da3e', '0001', '0001', to_timestamp('26-12-2017 09:26:17.954000', 'dd-mm-yyyy hh24:mi:ss.ff'), '1');
commit;
prompt 2 records loaded
set feedback on
set define on
prompt Done.
