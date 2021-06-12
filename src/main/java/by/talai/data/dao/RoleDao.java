package by.talai.data.dao;

import by.talai.model.Address;
import by.talai.model.Role;

import java.util.List;

public interface RoleDao {

    // create role
    int createRole(Role role);

    // get role by id
    Role getRole(int id);

    // get all roles
    List<Role> getAllRoles();

    //update role
    void updateRole(Role role);

    // delete role
    boolean deleteRole(int id);

}
