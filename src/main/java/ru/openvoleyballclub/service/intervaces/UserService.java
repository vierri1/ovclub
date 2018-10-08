package ru.openvoleyballclub.service.intervaces;

import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.model.User;

import java.util.List;

public interface UserService {
    User getById(String id);

    List<User> getAll();

    boolean create(String name, String surname, String login, String password, String birthDay);

    boolean update(User user);

    boolean delete(Integer id);

    User getAuthUser(String login, String password);

    User getCaptain(Team team);
}
