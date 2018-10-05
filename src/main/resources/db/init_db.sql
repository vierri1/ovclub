DROP TABLE IF EXISTS user_team;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS captain;
DROP TABLE IF EXISTS role;
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


create table if not exists team
(
  id            integer default nextval('team_id_seq' :: regclass) not null
    constraint team_pkey
    primary key,
  name          varchar(30),
  creation_time timestamp
);

alter table team
  owner to postgres;

create unique index if not exists team_name_uindex
  on team (name);

create table if not exists status
(
  id   integer default nextval('status_id_seq' :: regclass) not null
    constraint status_pkey
    primary key,
  name varchar(30)                                          not null
);

alter table status
  owner to postgres;

create unique index if not exists status_name_uindex
  on status (name);

create table if not exists role
(
  id   serial not null
    constraint role_pkey
    primary key,
  name varchar(30)
);

alter table role
  owner to postgres;

create table if not exists usr
(
  id                integer default nextval('usr_id_seq' :: regclass) not null
    constraint usr_pkey
    primary key,
  name              varchar(30)                                       not null,
  captain           boolean,
  password          varchar(50)                                       not null,
  login             varchar(40)                                       not null,
  surname           varchar(30),
  registration_time timestamp,
  birthday          timestamp,
  role_id           integer
    constraint usr_role_id_fk
    references role
);

alter table usr
  owner to postgres;

create unique index if not exists usr_login_uindex
  on usr (login);

create table if not exists user_team
(
  user_id   integer
    constraint user_team_user___fk
    references usr
    on delete cascade,
  team_id   integer
    constraint user_team_team___fk
    references team,
  status_id integer not null
    constraint status___fk
    references status,
  constraint user_team_pk
  unique (user_id, team_id)
);

alter table user_team
  owner to postgres;

