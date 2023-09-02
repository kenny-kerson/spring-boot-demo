drop table if exists member cascade;
drop table if exists member_history cascade;
drop sequence if exists sq_member_history_01;

create table member (
    id int8 not null,
    name varchar(255),
    primary key (id)
);