package by.talai.service.dto;

import by.talai.model.Role;
import by.talai.model.Status;
import by.talai.model.personnel.User;

import java.util.List;

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
}
