package by.talai.data.dao;

import by.talai.model.Address;
import by.talai.model.Role;

import java.util.List;

public interface RoleDao {

    // create role
    void createRole(Role role) throws Exception;

    // get role by id
    Role getRole(int id) throws Exception;

    // get all roles
    List<Role> getAllRoles() throws Exception;

    //update role
    void updateRole(Role role) throws Exception;

    // delete role
    void deleteRole(int id) throws Exception;

}
