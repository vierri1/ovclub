package ru.openvoleyballclub.model;

import java.util.List;
import java.util.Objects;

public class Team extends BaseEntity {

    private List<User> users;

    public Team() {
    }

    public Team(Integer id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(name, team.name) &&
                Objects.equals(users, team.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, users);
    }
}
