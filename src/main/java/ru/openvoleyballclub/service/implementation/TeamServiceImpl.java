package ru.openvoleyballclub.service.implementation;

import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.repository.implementation.TeamRepositoryJdbcImpl;
import ru.openvoleyballclub.repository.interfaces.TeamRepository;
import ru.openvoleyballclub.service.intervaces.TeamService;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    public TeamServiceImpl() {
        teamRepository = new TeamRepositoryJdbcImpl();
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
    public boolean create(Team team) {
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
}