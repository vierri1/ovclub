package ru.openvoleyballclub.service.intervaces;

import ru.openvoleyballclub.model.User;

import java.util.List;

public interface UserService {
    User getById(String id);

    List<User> getAll();

    boolean create(User user);

    boolean update(User user);

    boolean delete(Integer id);

    User getAuthUser(String login, String password);
}
