DROP TABLE IF EXISTS user_team;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS usr;
DROP TABLE IF EXISTS team;
DROP SEQUENCE IF EXISTS usr_id_seq;
DROP SEQUENCE IF EXISTS team_id_seq;
DROP SEQUENCE IF EXISTS status_id_seq;
DROP SEQUENCE IF EXISTS usr_team_seq;

CREATE SEQUENCE usr_id_seq;
CREATE SEQUENCE team_id_seq;
CREATE SEQUENCE status_id_seq;
CREATE SEQUENCE usr_team_seq;
create table usr
(
  id      integer default nextval('usr_id_seq' :: regclass) not null
    constraint usr_pkey
    primary key,
  name    varchar(30)                                       not null,
  captain boolean
);

alter table usr
  owner to postgres;

create table team
(
  id   integer default nextval('team_id_seq' :: regclass) not null
    constraint team_pkey
    primary key,
  name varchar(30)
);

alter table team
  owner to postgres;

create unique index team_name_uindex
  on team (name);

create table status
(
  id   integer default nextval('status_id_seq' :: regclass) not null
    constraint status_pkey
    primary key,
  name varchar(30)                                          not null
);

alter table status
  owner to postgres;

create unique index status_name_uindex
  on status (name);

create table user_team
(
  id        integer default nextval('usr_team_seq' :: regclass) not null
    constraint user_team_pkey
    primary key,
  user_id   integer                                             not null
    constraint user_team_user___fk
    references usr
    on delete cascade,
  team_id   integer
    constraint user_team_team___fk
    references team,
  status_id integer                                             not null
    constraint status___fk
    references status
);

alter table user_team
  owner to postgres;

