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


spool off
