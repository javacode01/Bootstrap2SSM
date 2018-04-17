-----------------------------------------------------
-- Export file for user SDGS12315                  --
-- Created by Administrator on 2018/4/17, 17:47:45 --
-----------------------------------------------------

spool demo_order.log

prompt
prompt Creating table DEMO_ORDER
prompt =========================
prompt
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
  is '订单（示例表）';
comment on column DEMO_ORDER.RECID
  is '订单id';
comment on column DEMO_ORDER.ORDER_NO
  is '订单编号';
comment on column DEMO_ORDER.DESCRIPTION
  is '订单简介';
comment on column DEMO_ORDER.CREATER
  is '创建人';
comment on column DEMO_ORDER.CREATE_TIME
  is '创建时间';
comment on column DEMO_ORDER.UPDATER
  is '更新人';
comment on column DEMO_ORDER.UPDATE_TIME
  is '更新时间';
alter table DEMO_ORDER
  add constraint PK_DEMO_ORDER primary key (RECID);


spool off
