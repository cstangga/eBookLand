create user 'anywhere'@'%' identified by 'anywhere';
show databases;
use mysql;
create schema anywheredb;
grant all privileges on anywheredb.* to 'anywhere'@'%';
show grants for 'anywhere'@'%';