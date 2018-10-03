DELETE
FROM user_team;
--DELETE FROM captain;
DELETE
FROM usr;
DELETE
FROM team;
DELETE
FROM status;

ALTER SEQUENCE status_id_seq
  RESTART WITH 1;
ALTER SEQUENCE team_id_seq
  RESTART WITH 1;
ALTER SEQUENCE usr_id_seq
  RESTART WITH 1;
ALTER SEQUENCE usr_team_seq
  RESTART WITH 1;

INSERT INTO usr (name, captain)
VALUES ('Mr.Jenkins', true),
       ('Mr.Travis', false),
       ('Mrs.Spring', true),
       ('Mr.Hibernate', false);

INSERT INTO team (name)
VALUES ('Destroyers'),
       ('Geeks');

INSERT INTO status (name)
VALUES ('without team'),
       ('send request'),
       ('receive request'),
       ('in team'),
       ('leave team');

INSERT INTO user_team (user_id, team_id, status_id)
VALUES (1, 1, 4),
       (2, 1, 4),
       (3, 2, 4),
       (4, 2, 4);

-- INSERT INTO captain (user_id, team_id) VALUES
--   (1, 1),
--   (3, 2);


