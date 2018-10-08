package ru.openvoleyballclub.service.implementation;

import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.model.User;
import ru.openvoleyballclub.repository.implementation.UserRepositoryJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.UserRepository;
import ru.openvoleyballclub.service.intervaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryJdbcImpl();
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(String id) {
        if (id != null) {
            return userRepository.get(Integer.parseInt(id));
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public boolean create(User user) {
        return userRepository.add(user);
    }

    @Override
    public boolean update(User user) {
        if (user != null) {
            return userRepository.update(user);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return userRepository.delete(id);
    }

    @Override
    public User getAuthUser(String login, String password) {
        User user;
        if (login != null && password != null) {
            //TODO Сделать хеширование пароля
            user = userRepository.getAuthUser(login, password);
            if (user != null) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getCaptain(Team team) {
        if (team != null) {
            return team.getUsers().stream()
                    .filter(User::isCaptain)
                    .findFirst().orElse(null);
        }
        return null;
    }
}
