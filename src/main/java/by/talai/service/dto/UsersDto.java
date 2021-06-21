package by.talai.service.dto;

import by.talai.model.Role;
import by.talai.model.Status;
import by.talai.model.personnel.User;

import java.util.List;
import java.util.Objects;

public class UsersDto {

    private List<User> users;
    private Integer amount;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UsersDto{" +
                "users=" + users +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDto)) return false;
        UsersDto usersDto = (UsersDto) o;
        return getUsers().equals(usersDto.getUsers()) && Objects.equals(getAmount(), usersDto.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsers(), getAmount());
    }
}
