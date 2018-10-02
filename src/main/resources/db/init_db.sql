DROP TABLE IF EXISTS user_team;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS captain;
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

CREATE TABLE usr
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('usr_id_seq'),
  name varchar(30) NOT NULL
);

CREATE TABLE team
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('team_id_seq'),
  name varchar(30)
);
CREATE UNIQUE INDEX team_name_uindex
  ON team (name);

CREATE TABLE status
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('status_id_seq'),
  name varchar(30) NOT NULL
);
CREATE UNIQUE INDEX status_name_uindex
  ON status (name);

CREATE TABLE user_team
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('usr_team_seq'),
  user_id   int NOT NULL,
  team_id   int NULL,
  status_id int NOT NULL,
  CONSTRAINT user_team_user___fk FOREIGN KEY (user_id) REFERENCES usr (id) ON DELETE CASCADE,
  CONSTRAINT user_team_team___fk FOREIGN KEY (team_id) REFERENCES team (id),
  CONSTRAINT status___fk FOREIGN KEY (status_id) REFERENCES status (id)
);

CREATE TABLE captain
(
  user_id int NOT NULL,
  team_id int NOT NULL,
  CONSTRAINT captain_pk PRIMARY KEY (user_id, team_id),
  CONSTRAINT user___fk FOREIGN KEY (user_id) REFERENCES usr (id),
  CONSTRAINT team___fk FOREIGN KEY (team_id) REFERENCES team (id)
);

