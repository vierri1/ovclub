package ru.openvoleyballclub.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class User extends BaseEntity {
    private String login;
    private String password;
    private String surname;
    private LocalDate birthDay;
    private boolean isCaptain;
    private String team;
    private Role role;

    public User() {

    }

    public User(String name, String surname, String login, String password, LocalDate birthDay, Role role) {
        this(null, name, surname, null, login, password, birthDay, role, false, null);
    }

    public User(Integer id, String name, String surname, LocalDateTime creationTime, String login,
                LocalDate birthDay, Role role, boolean isCaptain, String team) {
        this(id, name, surname, creationTime, login, null, birthDay, role, isCaptain, team);
    }

    public User(Integer id, String name, String surname, LocalDateTime creationTime, String login, String password,
                LocalDate birthDay, Role role, boolean isCaptain, String team) {
        this.id = id;
        this.creationTime = creationTime;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.role = role;
        this.isCaptain = isCaptain;
        this.team = team;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isCaptain() {
        return isCaptain;
    }

    public void setCaptain(boolean captain) {
        isCaptain = captain;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDay=" + birthDay +
                ", isCaptain=" + isCaptain +
                ", team=" + team +
                ", role=" + role +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
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
                Objects.equals(surname, user.surname) &&
                Objects.equals(birthDay, user.birthDay) &&
                Objects.equals(team, user.team) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, surname, birthDay, isCaptain, team, role);
    }
}