package ru.openvoleyballclub.service.intervaces;

import ru.openvoleyballclub.model.Team;

import java.util.List;

public interface TeamService {
    Team getById(String id);

    List<Team> getAll();

    boolean create(Team team);

    boolean update(Team team);

    boolean delete(Integer id);
}
