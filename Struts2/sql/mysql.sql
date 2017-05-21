create database ojsystem;
use ojsystem;

create table _category (id int(4) primary key ,
name char(50),
context char(200),
testIn char(50),
testOut char(50));

create table _user (userid char(15) not null primary key,password char(20)
 not null,email char(20) not null, sex char(10) not null,sum int(4));
 
create table _status(rankid int  primary key auto_increment,
userid char(15),
id char(10),
name char(50),
time char(50),
date char(50),
result char(50));