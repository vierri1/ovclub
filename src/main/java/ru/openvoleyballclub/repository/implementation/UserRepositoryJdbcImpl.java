package ru.openvoleyballclub.repository.implementation;

import org.apache.log4j.Logger;
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
        String usrInsertQuery = "INSERT INTO usr VALUES (DEFAULT, ?, false, ?, ?) RETURNING id";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrInsert = connection.prepareStatement(usrInsertQuery)) {
            if (!user.isNew()) {
                return false;
            } else {
                usrInsert.setString(1, user.getName());
                usrInsert.setString(2, user.getLogin());
                usrInsert.setString(3, user.getPassword());
                try (ResultSet generatedKey = usrInsert.executeQuery()) {
                    if (generatedKey.next()) {
                        user.setId(generatedKey.getInt(1));
                    }
                }
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
        String usrUpdateQuery = "UPDATE usr SET name=?, captain=?, login=?, password=? WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrUpdate = connection.prepareStatement(usrUpdateQuery)) {
            usrUpdate.setString(1, user.getName());
            usrUpdate.setBoolean(2, user.isCaptain());
            usrUpdate.setInt(3, user.getId());
            usrUpdate.setString(4, user.getLogin());
            usrUpdate.setString(5, user.getPassword());
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
        String usrGetQuery = "SELECT u.id, u.name as userName, u.captain, u.login, u.password, t.name as teamName " +
                "FROM ((user_team AS ut LEFT JOIN usr AS u ON u.id=ut.user_id)" +
                "LEFT JOIN team AS t ON ut.team_id = t.id) WHERE u.id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrGet = connection.prepareStatement(usrGetQuery)) {
            usrGet.setInt(1, id);
            User user = new User();
            try (ResultSet resultSet = usrGet.executeQuery()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("userName"));
                    user.setCaptain(resultSet.getBoolean("captain"));
                    user.setTeamName(resultSet.getString("teamName"));
                    user.setLogin(resultSet.getString("login"));
                    return user;
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
        String usrGetAllQuery = "SELECT u.id, u.name as userName, u.captain, u.login, u.password, t.name as teamName " +
                "FROM ((user_team AS ut LEFT JOIN usr AS u ON u.id=ut.user_id)" +
                "LEFT JOIN team AS t ON ut.team_id = t.id)";
        try (Connection connection = connectionManager.getConnection();
             Statement usrGetAll = connection.createStatement()) {
            List<User> users = new ArrayList<>();
            try (ResultSet resultSet = usrGetAll.executeQuery(usrGetAllQuery)) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getInt("id"),
                            resultSet.getString("userName"),
                            resultSet.getString("teamName"),
                            resultSet.getBoolean("captain"),
                            resultSet.getString("login"));
                    users.add(user);
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
    public List<User> getAllByTeamIdAndStatus(Integer teamId, String status) {
        String usrGetAllQueryByTeamId = "SELECT u.id, u.name as userName, u.captain, u.login, u.password, t.name as teamName " +
                "FROM (((user_team AS ut LEFT JOIN usr AS u ON u.id=ut.user_id)" +
                "LEFT JOIN team AS t ON ut.team_id = t.id)" +
                "INNER JOIN status AS st ON ut.status_id=st.id) WHERE t.id=? AND st.name=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrGetAllByTeamId = connection.prepareStatement(usrGetAllQueryByTeamId)) {
            usrGetAllByTeamId.setInt(1, teamId);
            usrGetAllByTeamId.setString(2, status);
            try (ResultSet resultSet = usrGetAllByTeamId.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(new User(resultSet.getInt("id"),
                            resultSet.getString("userName"),
                            resultSet.getString("teamName"),
                            resultSet.getBoolean("captain"),
                            resultSet.getString("login"),
                            resultSet.getString("password")));
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
        String usrGetQuery = "SELECT u.id, u.name as userName, u.captain, u.login, u.password, t.name as teamName " +
                "FROM ((user_team AS ut LEFT JOIN usr AS u ON u.id=ut.user_id)" +
                "LEFT JOIN team AS t ON ut.team_id = t.id) WHERE u.login=? AND u.password=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement usrGet = connection.prepareStatement(usrGetQuery)) {
            usrGet.setString(1, login);
            usrGet.setString(2, password);
            User user = new User();
            try (ResultSet resultSet = usrGet.executeQuery()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("userName"));
                    user.setCaptain(resultSet.getBoolean("captain"));
                    user.setTeamName(resultSet.getString("teamName"));
                    user.setLogin(resultSet.getString("login"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

}
