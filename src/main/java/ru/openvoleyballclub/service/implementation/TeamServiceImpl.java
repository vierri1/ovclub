package ru.openvoleyballclub.service.implementation;

import ru.openvoleyballclub.model.Status;
import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.repository.implementation.TeamRepositoryJdbcImpl;
import ru.openvoleyballclub.repository.implementation.UserRepositoryJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.TeamRepository;
import ru.openvoleyballclub.repository.interfaces.UserRepository;
import ru.openvoleyballclub.service.intervaces.TeamService;

import java.util.Arrays;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private UserRepository userRepository;

    public TeamServiceImpl() {
        teamRepository = new TeamRepositoryJdbcImpl();
        userRepository = new UserRepositoryJdbcImpl();
    }

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team getById(String id) {
        if (id != null) {
            return teamRepository.get(Integer.parseInt(id));
        }
        return null;
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.getAll();
    }

    @Override
    public int create(Team team) {
        return teamRepository.add(team);
    }

    @Override
    public boolean update(Team team) {
        return teamRepository.update(team);
    }

    @Override
    public boolean delete(Integer id) {
        return teamRepository.delete(id);
    }

    @Override
    public String joinTeam(String userId, String teamId) {
        if (checkUserIdTeamId(userId, teamId)) {
            return "Ошибка получения данных";
        }
        User user = getUser(userId);
        Team team = getTeam(teamId);
        if (user == null || team == null) {
            return "Ошибка получения данных";
        }
        if (user.getTeam() != null) {
            return "Вы уже состоите в команде + " + user.getTeam() + "!";
        }
        List<Team> allUserTeams = teamRepository.getAllByUserId(user.getId());
        if (allUserTeams.contains(team)) {
            if (teamRepository.updateUserTeamStatus(user.getId(), team.getId(), Status.IN_TEAM)) {
                return "Вы успешно перешли в команду " + team.getName() + "!";
            }
        } else {
            if (teamRepository.setUserTeamStatus(user.getId(), team.getId(), Status.IN_TEAM)) {
                return "Вы успешно перешли в команду " + team.getName() + "!";
            }
        }
        return "Ошибка вступления в команду";
    }

    @Override
    public String leaveTeam(String userId, String teamId) {
        if (checkUserIdTeamId(userId, teamId)) {
            return "Ошибка получения данных";
        }
        Integer id = Integer.parseInt(userId);
        Integer tId = Integer.parseInt(teamId);
        User user = userRepository.get(id);
        if (user == null) {
            return "Ошибка получения данных";
        }
        if (user.isCaptain()) {
            return "Капитан не может покинуть команду!";
        }
        List<Team> teams = teamRepository.getAllByUserIdAndStatusId(id, Status.IN_TEAM);
        Team team;
        if (teams.isEmpty()) {
            return "Пользователь " + user.getName() + " не состоит в команде!";
        } else {
            team = teams.get(0);
            if (!team.getId().equals(tId)) {
                return "Ошибка выхода из команды " + team.getName() + "!";
            }
        }
        if (teamRepository.updateUserTeamStatus(id, tId, Status.LEAVE_TEAM)) {
            return "Пользователь " + user.getName() + " успешно покинул команду " + team.getName() + "!";
        }
        return "Ошибка выхода из команды " + team.getName() + "!";
    }

    @Override
    public String createTeam(String userId, String teamName) {
        if (userId == null || teamName == null) {
            return "Введите название команды!";
        }
        Integer id = Integer.parseInt(userId);
        Team team = getCurrentUserTeam(id);
        if (team != null) {
            return "Невозможно создать команду, так как Вы уже состоите в команде " + team.getName() + "!";
        }
        User creator = userRepository.get(id);
        Team createdTeam = new Team(teamName, Arrays.asList(creator));
        int newTeamId = teamRepository.add(createdTeam);
        if (newTeamId == -1) {
            return "Команда с именем " + teamName + " уже существует!";
        }
        creator.setCaptain(true);
        userRepository.update(creator);
        teamRepository.setUserTeamStatus(id, newTeamId, Status.IN_TEAM);
        return "Команда " + teamName + " успешно создана!";
    }

    @Override
    public String sendRequestToUser(String userId, String teamId) {
        if (userId == null || teamId == null) {
            return "Ошибка получения данных";
        }
        Integer id = Integer.parseInt(userId);
        Integer tId = Integer.parseInt(teamId);
        User user = userRepository.get(id);
        Team team = teamRepository.get(tId);
        if (user == null || team == null) {
            return "Ошибка получения данных";
        }
        Team userTeam = getCurrentUserTeam(id);
        if (userTeam != null) {
            return "Невозможно пригласить пользователя в команду, так как он уже состоит в команде " + team.getName() + "!";
        }
        List<Team> allUserTeams = teamRepository.getAllByUserId(user.getId());
        if (allUserTeams.contains(team)) {
            if (teamRepository.updateUserTeamStatus(user.getId(), team.getId(), Status.RECEIVE_REQUEST)) {
                return "Приглашение пользователю " + user.getName() + " в команду " + team.getName() + " успешно отправлено!";
            }
        } else {
            if (teamRepository.setUserTeamStatus(user.getId(), team.getId(), Status.RECEIVE_REQUEST)) {
                return "Приглашение пользователю " + user.getName() + " в команду " + team.getName() + " успешно отправлено!";
            }
        }
        return "Ошибка отправки приглашения";
    }

    @Override
    public String sendRequestToTeam(String userId, String teamId) {
        if (userId == null || teamId == null) {
            return "Ошибка получения данных";
        }
        Integer id = Integer.parseInt(userId);
        Integer tId = Integer.parseInt(teamId);
        User user = userRepository.get(id);
        Team team = teamRepository.get(tId);
        if (user == null || team == null) {
            return "Ошибка получения данных";
        }
        Team userTeam = getCurrentUserTeam(id);
        if (userTeam != null) {
            return "Невозможно отправить запрос на вступление в команду, вы уже в " + team.getName() + "!";
        }
        List<Team> allUserTeams = teamRepository.getAllByUserId(user.getId());
        if (allUserTeams.contains(team)) {
            if (teamRepository.updateUserTeamStatus(user.getId(), team.getId(), Status.SEND_REQUEST)) {
                return "Заявка на вступление в команду " + team.getName() + " успешно отправлена!";
            }
        } else {
            if (teamRepository.setUserTeamStatus(user.getId(), team.getId(), Status.SEND_REQUEST)) {
                return "Заявка на вступление в команду " + team.getName() + " успешно отправлена!";
            }
        }
        return "Ошибка отправки заявки";
    }

    private boolean checkUserIdTeamId(String userId, String teamId) {
        return (userId == null || teamId == null);
    }

    private User getUser(String userId) {
        return userRepository.get(Integer.parseInt(userId));
    }

    private Team getTeam(String teamId) {
        return teamRepository.get(Integer.parseInt(teamId));
    }

    private Team getCurrentUserTeam(Integer id) {
        List<Team> teams = teamRepository.getAllByUserIdAndStatusId(id, Status.IN_TEAM);
        if (!teams.isEmpty()) {
            return teams.get(0);
        }
        return null;
    }
}