package by.talai.data.dao;

import by.talai.model.Address;
import by.talai.model.Role;

import java.util.List;

/**
 * The interface Role dao.
 */
public interface RoleDao {

    /**
     * Create role.
     *
     * @param role the role
     * @throws Exception the exception
     */
// create role
    void createRole(Role role) throws Exception;

    /**
     * Gets role.
     *
     * @param id the id
     * @return the role
     * @throws Exception the exception
     */
// get role by id
    Role getRole(int id) throws Exception;

    /**
     * Gets all roles.
     *
     * @return the all roles
     * @throws Exception the exception
     */
// get all roles
    List<Role> getAllRoles() throws Exception;

    /**
     * Update role.
     *
     * @param role the role
     * @throws Exception the exception
     */
//update role
    void updateRole(Role role) throws Exception;

    /**
     * Delete role.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete role
    void deleteRole(int id) throws Exception;

}
