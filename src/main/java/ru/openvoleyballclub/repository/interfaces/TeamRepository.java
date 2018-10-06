package ru.openvoleyballclub.repository.interfaces;

import ru.openvoleyballclub.model.Status;
import ru.openvoleyballclub.model.Team;

import java.util.List;

public interface TeamRepository {
    boolean add(Team team);

    boolean delete(Integer id);

    boolean update(Team team);

    Team get(Integer id);

    List<Team> getAll();

    List<Team> getAllByUserIdAndStatusId(Integer userId, Status status);

    boolean setUserTeamStatus(Integer userId, Integer teamId, Status status);

    boolean updateUserTeamStatus(Integer userId, Integer teamId, Status status);
}
