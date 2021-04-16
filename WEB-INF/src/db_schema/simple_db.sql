create database if not exists db1;
use db1;
set foreign_key_checks=0;
drop table if exists login_table;
drop table if exists staff_details;
drop table if exists student_details;
drop table if exists dept_staff_map;
drop table if exists department;
drop table if exists dept_stud_map;
set foreign_key_checks=1;



create table if not exists login_table(_id int auto_increment not null,
                                        email varchar(20) unique not null,
                                        password varchar(30) not null,
                                        primary key(_id),
                                        index(email),
                                        type int
                                        );

create table if not exists department(_id int auto_increment not null,
                                      dept varchar(20) unique,
                                      primary key(_id),
                                      index(dept)
                                      );

create table if not exists staff_details(_id int,
                                         name varchar(30) not null,
                                         staff_id int unique,
                                         dob varchar(15),
                                         phone_no varchar(15),
                                         education_qualification varchar(20),
                                         foreign key(_id) references login_table(_id) on delete cascade,
                                         primary key(_id)
                                        );

create table if not exists dept_staff_map(d_id int,
                                         staff_id int,
                                         foreign key(d_id) references department(_id),
                                         foreign key(staff_id) references staff_details(staff_id) on delete cascade
                                        );

create table if not exists student_details(_id int,
                                         name varchar(30) not null,
                                         student_id int unique,
                                         dob varchar(15),
                                         phone_no varchar(15),
                                         department varchar(20),
                                         address varchar(100),
                                         foreign key(_id) references login_table(_id) on delete cascade,
                                         primary key(_id)
                                        );

create table if not exists dept_student_map(d_id int,
                                         student_id int,
                                         foreign key(d_id) references department(_id) on delete cascade,
                                         foreign key(student_id) references student_details(student_id) on delete cascade
                                        );


create table  if not exists session_table(_id int,
                                        session_key varchar(30),
                                        type int
                                        );




-- generate the dummy
-- insert into department(dept) values("IT"),("CSE");
-- insert into staff_details(_id,name,staff_id, values(1,"chella",2002,
