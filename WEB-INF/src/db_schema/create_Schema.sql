create database if not exists db1;
use db1;
drop table if exists login;
drop table if exists staff_details;
drop table if exists student_details;


create table if not exists login(_id int auto_increment not null,
                                        email varchar(20) not null,
                                        password varchar(30) not null,
                                        primary key(_id),
                                        index using btree (email)
                                        );

create table if not exists subjects(_id int auto_increment not null,
                                    subject_name varchar(20),
                                    );     

create table if not exists subject_user_map_table(s_id int not null,
                                             u_id int not null,
                                             foreign key(s_id) references subjects(_id) on delete cascade,
                                             foreign key(u_id) references login(_id) on delete cascade,
                                             );       


create table if not exists staff_details(_id int,
                                         name varchar(30),
                                         staff_id int,
                                         dob date,
                                         phone_no varchar(15),
                                         department varchar(20),
                                         major varchar(20),
                                         foreign key(_id) references login(_id) on delete cascade,
                                         foreign key(e_id) references education_qualification(_id) on delete restrict,
                                         primary key(_id)
                                        );

create table if not exists student_details(_id int,
                                         name varchar(30),
                                         student_id int,
                                         dob date,
                                         phone_no varchar(15),
                                         department varchar(20),
                                         address varchar(100),
                                         foreign key(_id) references login(_id) on delete cascade,
                                         primary key(_id)
                                        );




