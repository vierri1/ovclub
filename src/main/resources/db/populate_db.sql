DELETE
FROM user_team;
DELETE
FROM usr;
DELETE
FROM role;
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
ALTER SEQUENCE role_id_seq
  RESTART WITH 1;

INSERT INTO role (name)
VALUES ('player'),
       ('admin');

INSERT INTO usr (name, captain, password, login, surname, registration_time, birthday, role_id)
VALUES ('Mr.Jenkins', true, '1', 'jenkins', 'Ivanov', now(), '1999-01-08', 1),
       ('Mr.Travis', false, '2', 'travis', 'Sharikov', now(), '1999-01-08', 1),
       ('Mrs.Spring', true, '3', 'spring', 'Malinova', now(), '1999-01-08', 1),
       ('Mr.Hibernate', false, '4', 'hibernate', 'Koshkin', now(), '1999-01-08', 1),
       ('Mr.Lombok', false, '5', 'lombok', 'Malov', now(), '1999-01-08', 1),
       ('Mr.Git', false, '6', 'git', 'Habov', now(), '1999-01-08', 1),
       ('Mr.Pattern', false, '7', 'pattern', 'Singletonov', now(), '1999-01-08', 1),
       ('Mr.Random', false, '8', 'random', 'Sluchainov', now(), '1999-01-08', 1),
       ('User', false, '9', 'user', 'Userov', now(), '1999-01-08', 1);


INSERT INTO team (name, creation_time)
VALUES ('Destroyers', now()),
       ('Geeks', now());


INSERT INTO status (name)
VALUES ('without team'),
       ('in team'),
       ('receive request'),
       ('send request'),
       ('leave team');

INSERT INTO user_team (user_id, team_id, status_id)
VALUES (1, 1, 2),
       (2, 1, 2),
       (3, 2, 2),
       (4, 2, 2),
       (5, 1, 2),
       (6, 2, 2),
       (7, 2, 2),
       (8, 1, 2);




