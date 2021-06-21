package by.talai.model.personnel;

import by.talai.model.Role;
import by.talai.model.Status;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    protected int id;
    protected String name;
    protected String surname;
    protected String login;
    protected String password;
    protected Role role;
    protected Status status;

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

    public boolean validateUser(String password) {
        if("".equals(password)|| password == null){
            return false;
        }
        return password.equals(this.password);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
                ",\n status=" + status +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getName(), user.getName())
                && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getLogin(), user.getLogin())
                && Objects.equals(password, user.password) && Objects.equals(getRole(), user.getRole())
                && Objects.equals(getStatus(), user.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getLogin(), password, getRole(), getStatus());
    }
}
