package ru.openvoleyballclub.service.implementation;

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
    public User getById(Integer id) {
        return userRepository.get(id);
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
        return userRepository.update(user);
    }

    @Override
    public boolean delete(Integer id) {
        return userRepository.delete(id);
    }
}
