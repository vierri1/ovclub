package ru.openvoleyballclub.model;

import java.util.Objects;

public class User extends BaseEntity {
    private String login;
    private String password;
    private boolean isCaptain;
    private String teamName;

    public User() {
    }

    public User(Integer id, String name, String teamName, boolean isCaptain, String login) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.isCaptain = isCaptain;
        this.login = login;
    }

    public User(Integer id, String name, String teamName, boolean isCaptain, String login, String password) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.isCaptain = isCaptain;
        this.login = login;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", isCaptain=" + isCaptain +
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
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(teamName, user.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, isCaptain, teamName);
    }
}