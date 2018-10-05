package ru.openvoleyballclub.repository.interfaces;

import ru.openvoleyballclub.model.Status;
import ru.openvoleyballclub.model.User;

import java.util.List;

public interface UserRepository {
    boolean add(User user);

    boolean delete(Integer id);

    boolean update(User user);

    User get(Integer id);

    List<User> getAll();

    List<User> getAllByTeamIdAndStatusId(Integer teamId, Status status);

    User getAuthUser(String login, String password);
}
