--创建临时表空间
create temporary tablespace BOOTSTRAP2SSM_TEMP 
tempfile 'D:\app\lenovo\oradata\orcl\bootstrap2ssm_temp.dbf' 
size 50m 
autoextend on 
next 50m maxsize 2048m 
extent management local; 

--创建数据表空间 
create tablespace BOOTSTRAP2SSM_DATA 
logging 
datafile 'D:\app\lenovo\oradata\orcl\bootstrap2ssm_data.dbf' 
size 50m 
autoextend on 
next 50m maxsize 2048m 
extent management local; 

--创建用户并指定表空间 
create user BOOTSTRAP2SSM identified by BOOTSTRAP2SSM 
default tablespace BOOTSTRAP2SSM_DATA
temporary tablespace BOOTSTRAP2SSM_TEMP; 

--给用户授予权限 
grant connect,resource,dba to BOOTSTRAP2SSM; 