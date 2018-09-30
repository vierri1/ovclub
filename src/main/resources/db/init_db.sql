CREATE TABLE public.usr
(
  id   serial PRIMARY KEY NOT NULL,
  name varchar(30)        NOT NULL
);

CREATE TABLE public.team
(
  id   serial PRIMARY KEY NOT NULL,
  name varchar(30)        NOT NULL
);
CREATE UNIQUE INDEX team_name_uindex
  ON public.team (name);

CREATE TABLE public.status
(
  id   serial PRIMARY KEY NOT NULL,
  name varchar(30)        NOT NULL
);
CREATE UNIQUE INDEX status_name_uindex
  ON public.status (name);

CREATE TABLE public.user_team
(
  user_id   int NOT NULL,
  team_id   int NOT NULL,
  status_id int NOT NULL,
  CONSTRAINT user_team_pk PRIMARY KEY (user_id, team_id),
  CONSTRAINT user_team_user___fk FOREIGN KEY (user_id) REFERENCES public.usr (id),
  CONSTRAINT user_team_team___fk FOREIGN KEY (team_id) REFERENCES public.team (id),
  CONSTRAINT status___fk FOREIGN KEY (status_id) REFERENCES public.status (id)
);

CREATE TABLE public.captain
(
  user_id int NOT NULL,
  team_id int NOT NULL,
  CONSTRAINT captain_pk PRIMARY KEY (user_id, team_id),
  CONSTRAINT user___fk FOREIGN KEY (user_id) REFERENCES public.usr (id),
  CONSTRAINT team___fk FOREIGN KEY (team_id) REFERENCES public.team (id)
);

