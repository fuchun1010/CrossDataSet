create database demo;

create user "tank" identified by "123456";

GRANT ALL PRIVILEGES ON demo.* TO 'tank'@'%' WITH GRANT OPTION;



