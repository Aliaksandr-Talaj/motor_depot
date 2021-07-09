package by.talai.service;

import by.talai.model.Role;

/**
 * The interface Role service.
 */
public interface RoleService {

    /**
     * Gets role.
     *
     * @param roleId the role id
     * @return the role
     * @throws Exception the exception
     */
    Role getRole(int roleId) throws Exception;
}
