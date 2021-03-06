package ru.openvoleyballclub.service.intervaces;

import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.model.User;

import java.util.List;

public interface TeamService {
    Team getById(String id);

    List<Team> getAll();

    int create(Team team);

    boolean update(Team team);

    boolean delete(Integer id);

    String joinTeam(String userId, String teamId);

    String leaveTeam(User user, String teamId);

    String createTeam(User user, String teamName);

    String sendRequestToUser(String userId, String teamId);

    String sendRequestToTeam(String userId, String teamId);

    Team getCurrentUserTeam(Integer userId);
}
