package by.talai.model.personnel;

import by.talai.model.Role;

import java.io.Serializable;

public class User implements Serializable {
    protected int id;
    protected String name;
    protected String surname;
    protected String login;
    protected String password;
    protected Role role;

    public User() {

    }

    public User(int id, String name, String surname, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
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
                "id=" + id +
                ",\n name='" + name + '\'' +
                ",\n surname='" + surname + '\'' +
                ",\n login='" + login + '\'' +
                ",\n password='" + password + '\'' +
                ",\n role=" + role +
                '}';
    }
}
