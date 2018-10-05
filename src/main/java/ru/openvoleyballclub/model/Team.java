package ru.openvoleyballclub.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Team extends BaseEntity {

    private List<User> users;
    private User creator;

    public Team() {
    }

    public Team(Integer id, String name, LocalDateTime creationTime, List<User> users, User creator) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.creationTime = creationTime;
        this.creator = creator;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Team{" +
                "users=" + users +
                ", creator=" + creator +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(users, team.users) &&
                Objects.equals(creator, team.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, creator);
    }
}
