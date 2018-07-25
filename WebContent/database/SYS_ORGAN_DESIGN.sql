-----------------------------------------------------
-- Export file for user DYYDZF                     --
-- Created by Administrator on 2018/7/25, 11:31:25 --
-----------------------------------------------------

spool SYS_ORGAN_DESIGN.log

prompt
prompt Creating table SYS_ORGAN_DESIGN
prompt ===============================
prompt
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
  is '设置组织结构的规则';
comment on column SYS_ORGAN_DESIGN.RECID
  is '主键';
comment on column SYS_ORGAN_DESIGN.ORGAN_LEVEL
  is '对应SYS_ORGAN的ORGAN_LEVEL字段';
comment on column SYS_ORGAN_DESIGN.ORGAN_LEVEL_NAME
  is '组织级别名称';
comment on column SYS_ORGAN_DESIGN.NEXT_LEVEL
  is '允许创建的下级级别';
comment on column SYS_ORGAN_DESIGN.HAVE_USER
  is '0：否，1：是';
comment on column SYS_ORGAN_DESIGN.ICON_URL
  is '默认图标';
alter table SYS_ORGAN_DESIGN
  add constraint PK_SYS_ORGAN_DESIGN primary key (RECID);


spool off
