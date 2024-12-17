create user 'ebookland'@'%' identified by 'ebookland';
create database ebooklandDB;
grant all privileges on ebooklandDB.* to 'ebookland'@'%';

select user,host from mysql.user;
show grants for 'ebookland'@'%';