package ru.openvoleyballclub.repository.implementation;

import ru.openvoleyballclub.model.Status;
import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.repository.connection_manager.ConnectionManager;
import ru.openvoleyballclub.repository.connection_manager.ConnectionManagerJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.TeamRepository;
import ru.openvoleyballclub.repository.interfaces.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamRepositoryJdbcImpl implements TeamRepository {

    private ConnectionManager connectionManager;
    private UserRepository userRepository;

    public TeamRepositoryJdbcImpl() {
        connectionManager = ConnectionManagerJdbcImpl.getInstance();
        userRepository = new UserRepositoryJdbcImpl();
    }

    public TeamRepositoryJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean add(Team team) {
        String teamInsertQuery = "INSERT INTO team VALUES (default, ?, default)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement teamInsert = connection.prepareStatement(teamInsertQuery)) {
            if (!team.isNew()) {
                return false;
            } else {
                teamInsert.setString(1, team.getName());
                teamInsert.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String teamDeleteQuery = "DELETE FROM team WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement teamDelete = connection.prepareStatement(teamDeleteQuery)) {
            teamDelete.setInt(1, id);
            teamDelete.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Team team) {
        String teamUpdateQuery = "UPDATE team SET name=? WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement teamUpdate = connection.prepareStatement(teamUpdateQuery)) {
            teamUpdate.setString(1, team.getName());
            teamUpdate.setInt(2, team.getId());
            teamUpdate.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Team get(Integer id) {
        String teamGetQuery = "SELECT * FROM team WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement teamGet = connection.prepareStatement(teamGetQuery)) {
            teamGet.setInt(1, id);
            try (ResultSet resultSet = teamGet.executeQuery()) {
                if (resultSet.next()) {
                    return new Team(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("creation_time").toLocalDateTime(),
                            userRepository.getAllByTeamIdAndStatusId(id, Status.IN_TEAM));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Team> getAll() {
        String getAllQuery = "SELECT * FROM team";
        try (Connection connection = connectionManager.getConnection();
             Statement getAll = connection.createStatement()) {
            List<Team> teams = new ArrayList<>();
            try (ResultSet resultSet = getAll.executeQuery(getAllQuery)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    teams.add(new Team(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("creation_time").toLocalDateTime(),
                            userRepository.getAllByTeamIdAndStatusId(id, Status.IN_TEAM)));
                }
            }
            return teams;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Team> getAllByUserIdAndStatusId(Integer userId, Status status) {
        String getAllQuery = "select * from team\n" +
                "inner join user_team ut on team.id = ut.team_id and user_id=? and status_id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(getAllQuery)) {
            statement.setInt(1, userId);
            statement.setInt(2, status.ordinal() + 1);
            List<Team> teams = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    teams.add(new Team(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("creation_time").toLocalDateTime(),
                            userRepository.getAllByTeamIdAndStatusId(id, Status.IN_TEAM)));
                }
            }
            return teams;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean setUserTeamStatus(Integer userId, Integer teamId, Status status) {
        String insertUserTeamStatus = "insert into user_team (user_id, team_id, status_id) " +
                "VALUES (?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertQuery = connection.prepareStatement(insertUserTeamStatus)) {
            insertQuery.setInt(1, userId);
            insertQuery.setInt(2, teamId);
            insertQuery.setInt(3, status.ordinal() + 1);
            insertQuery.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserTeamStatus(Integer userId, Integer teamId, Status status) {
        String insertUserTeamStatus = "update user_team set status_id=? " +
                "where user_id=? and team_id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertQuery = connection.prepareStatement(insertUserTeamStatus)) {
            insertQuery.setInt(1, status.ordinal() + 1);
            insertQuery.setInt(2, userId);
            insertQuery.setInt(3, teamId);
            insertQuery.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
