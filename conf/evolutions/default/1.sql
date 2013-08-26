# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
  email                     varchar(255) not null,
  prename                   varchar(255),
  lastname                  varchar(255),
  constraint pk_person primary key (email))
;

create table supervisor (
  email                     varchar(255) not null,
  prename                   varchar(255),
  lastname                  varchar(255),
  password                  varchar(255),
  constraint pk_supervisor primary key (email))
;

create table task (
  thesis_id                 bigint not null)
;

create table thesis (
  id                        bigint not null,
  type                      varchar(255),
  start_date                timestamp,
  topic                     varchar(255),
  constraint pk_thesis primary key (id))
;

create sequence person_seq;

create sequence supervisor_seq;

create sequence thesis_seq;

alter table task add constraint fk_task_thesis_1 foreign key (thesis_id) references thesis (id) on delete restrict on update restrict;
create index ix_task_thesis_1 on task (thesis_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists person;

drop table if exists supervisor;

drop table if exists task;

drop table if exists thesis;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists person_seq;

drop sequence if exists supervisor_seq;

drop sequence if exists thesis_seq;

