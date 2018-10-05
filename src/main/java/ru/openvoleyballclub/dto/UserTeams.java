package ru.openvoleyballclub.dto;

import ru.openvoleyballclub.model.Status;
import ru.openvoleyballclub.model.Team;
import ru.openvoleyballclub.model.User;

import java.util.List;

public class UserTeams {
    private List<Team> teams;
    private Status status;
    private User user;

    public UserTeams(List<Team> teams, Status status, User user) {
        this.teams = teams;
        this.status = status;
        this.user = user;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserTeams{" +
                "teams=" + teams +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
