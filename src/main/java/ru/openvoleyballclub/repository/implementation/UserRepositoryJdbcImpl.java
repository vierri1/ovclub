package ru.openvoleyballclub.repository.implementation;

import org.apache.log4j.Logger;
import ru.openvoleyballclub.model.Role;
import ru.openvoleyballclub.model.Status;
import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.repository.connection_manager.ConnectionManager;
import ru.openvoleyballclub.repository.connection_manager.ConnectionManagerJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {

    private Logger LOGGER = Logger.getLogger(UserRepository.class);

    private ConnectionManager connectionManager;

    public UserRepositoryJdbcImpl() {
        connectionManager = ConnectionManagerJdbcImpl.getInstance();
    }

    public UserRepositoryJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean add(User user) {
        String usrInsertQuery = "INSERT INTO usr " +
                "(id, name, captain, password, login, surname, registration_time, birthday, role_id) " +
                "VALUES (DEFAULT, ?, false, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrInsert = connection.prepareStatement(usrInsertQuery)) {
            if (!user.isNew()) {
                return false;
            } else {
                usrInsert.setString(1, user.getName());
                usrInsert.setString(2, user.getPassword());
                usrInsert.setString(3, user.getLogin());
                usrInsert.setString(4, user.getSurname());
                usrInsert.setTimestamp(5, Timestamp.valueOf(user.getCreationTime()));
                usrInsert.setTimestamp(6, Timestamp.valueOf(user.getBirthDay().atStartOfDay()));
                usrInsert.setInt(7, user.getRole().ordinal() + 1);
                usrInsert.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String usrDeleteQuery = "DELETE FROM usr WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrDelete = connection.prepareStatement(usrDeleteQuery)) {
            usrDelete.setInt(1, id);
            usrDelete.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        String usrUpdateQuery = "UPDATE usr SET name=?, captain=?, login=?, surname=?, birthday=?, role_id=? WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrUpdate = connection.prepareStatement(usrUpdateQuery)) {
            usrUpdate.setString(1, user.getName());
            usrUpdate.setBoolean(2, user.isCaptain());
            usrUpdate.setString(3, user.getLogin());
            usrUpdate.setString(4, user.getSurname());
            usrUpdate.setTimestamp(5, Timestamp.valueOf(user.getBirthDay().atStartOfDay()));
            usrUpdate.setInt(6, user.getRole().ordinal() + 1);
            usrUpdate.setInt(7, user.getId());
            usrUpdate.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    @Override
    public User get(Integer id) {
        String usrGetQuery = "select usr.*, r.name as role, ut.team_id, t.name as team_name,\n" +
                "       t.creation_time as team_creation_time from usr\n" +
                "left join user_team ut on usr.id = ut.user_id and ut.status_id = 2\n" +
                "left join team t on ut.team_id = t.id\n" +
                "left join role r on usr.role_id = r.id\n" +
                "where usr.id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrGet = connection.prepareStatement(usrGetQuery)) {
            usrGet.setInt(1, id);
            try (ResultSet resultSet = usrGet.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getTimestamp("registration_time").toLocalDateTime(),
                            resultSet.getString("login"),
                            resultSet.getDate("birthday").toLocalDate(),
                            Role.values()[resultSet.getInt("role_id") - 1],
                            resultSet.getBoolean("captain"),
                            resultSet.getString("team_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        String usrGetAllQuery = "select usr.*, r.name as role, ut.team_id, t.name as team_name,\n" +
                "       t.creation_time as team_creation_time from usr\n" +
                "left join user_team ut on usr.id = ut.user_id\n" +
                "left join team t on ut.team_id = t.id\n" +
                "left join role r on usr.role_id = r.id";
        try (Connection connection = connectionManager.getConnection();
             Statement usrGetAll = connection.createStatement()) {
            List<User> users = new ArrayList<>();
            try (ResultSet resultSet = usrGetAll.executeQuery(usrGetAllQuery)) {
                while (resultSet.next()) {
                    users.add(new User(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getTimestamp("registration_time").toLocalDateTime(),
                            resultSet.getString("login"),
                            resultSet.getDate("birthday").toLocalDate(),
                            Role.values()[resultSet.getInt("role_id") - 1],
                            resultSet.getBoolean("captain"),
                            resultSet.getString("team_name")));
                }
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> getAllByTeamIdAndStatusId(Integer teamId, Status status) {
        String usrGetAllQueryByTeamId = "select usr.*, r.name as role, ut.team_id, t.name as team_name,\n" +
                "       t.creation_time as team_creation_time from usr\n" +
                "left join user_team ut on usr.id = ut.user_id and ut.status_id=?\n" +
                "left join team t on ut.team_id = t.id\n" +
                "left join role r on usr.role_id = r.id\n" +
                "where t.id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrGetAllByTeamId = connection.prepareStatement(usrGetAllQueryByTeamId)) {
            usrGetAllByTeamId.setInt(1, status.ordinal() + 1);
            usrGetAllByTeamId.setInt(2, teamId);
            try (ResultSet resultSet = usrGetAllByTeamId.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(new User(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getTimestamp("registration_time").toLocalDateTime(),
                            resultSet.getString("login"),
                            resultSet.getDate("birthday").toLocalDate(),
                            Role.values()[resultSet.getInt("role_id") - 1],
                            resultSet.getBoolean("captain"),
                            resultSet.getString("team_name")));
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public User getAuthUser(String login, String password) {
        String usrGetQuery = "select usr.*, r.name as role, t.name as team_name from usr\n" +
                "left join user_team ut on usr.id = ut.user_id and ut.status_id = 2\n" +
                "left join team t on ut.team_id = t.id\n" +
                "left join role r on usr.role_id = r.id\n" +
                "where usr.login=? and usr.password=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrGet = connection.prepareStatement(usrGetQuery)) {
            usrGet.setString(1, login);
            usrGet.setString(2, password);
            try (ResultSet resultSet = usrGet.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getTimestamp("registration_time").toLocalDateTime(),
                            resultSet.getString("login"),
                            resultSet.getDate("birthday").toLocalDate(),
                            Role.values()[resultSet.getInt("role_id") - 1],
                            resultSet.getBoolean("captain"),
                            resultSet.getString("team_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

}
