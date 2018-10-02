package ru.openvoleyballclub.model;

import java.util.Objects;

public class User extends BaseEntity {
    private boolean isCaptain;
    private String teamName;

    public User() {
    }

    public User(Integer id, String name, String teamName, boolean isCaptain) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.isCaptain = isCaptain;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public boolean isCaptain() {
        return isCaptain;
    }

    public void setCaptain(boolean captain) {
        isCaptain = captain;
    }

    @Override
    public String toString() {
        return "User{" +
                "isCaptain=" + isCaptain +
                ", teamName='" + teamName + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isCaptain == user.isCaptain &&
                Objects.equals(teamName, user.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCaptain, teamName);
    }
}
