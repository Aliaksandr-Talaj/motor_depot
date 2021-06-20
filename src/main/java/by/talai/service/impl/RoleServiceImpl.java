package by.talai.service.impl;

import by.talai.data.dao.RoleDao;
import by.talai.data.dao.impl.RoleDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Role;
import by.talai.service.RoleService;
import by.talai.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleServiceImpl implements RoleService {


    private final RoleDao roleDao = new RoleDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

        public RoleServiceImpl() throws ConnectionPoolException {
    }

    @Override
    public Role getRole(int roleId) throws Exception {
        return roleDao.getRole(roleId);
    }

}
