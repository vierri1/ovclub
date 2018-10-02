package ru.openvoleyballclub.repository.implementation;

import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.repository.connection_manager.ConnectionManager;
import ru.openvoleyballclub.repository.connection_manager.ConnectionManagerJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.TeamRepository;
import ru.openvoleyballclub.repository.interfaces.UserRepository;

import java.sql.*;
import java.util.ArrayList;
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
        String teamInsertQuery = "INSERT INTO team VALUES (default, ?)";
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
                Team team = new Team();
                if (resultSet.next()) {
                    team.setId(resultSet.getInt("id"));
                    team.setName(resultSet.getString("name"));
                    team.setUsers(userRepository.getAllByTeamIdAndStatus(id, "in team"));
                    return team;
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
                            id,
                            resultSet.getString("name"),
                            userRepository.getAllByTeamIdAndStatus(id, "in team")));
                }
            }
            return teams;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
