package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.RoleDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createRole(Role role) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.role (id, name)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, role.getId());
            preparedStatement.setString(2, role.getName());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in createRole() method");
                throw new DaoException("exception in createRole() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createRole() method");
            throw new DaoException("exception in createRole() method", e);
        } catch (Exception e) {
            logger.error("Exception in createRole() method");
            throw new DaoException("exception in createRole() method", e);
        }
    }

    @Override
    public Role getRole(int id) throws Exception {
        Role role = new Role();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.role WHERE id=?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                role.setId(id);

                resultSet.next();
                role.setName(resultSet.getString("name"));


            } catch (SQLException e) {
                logger.error("Sql exception in getRole() method");
                throw new DaoException("", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getRole() method");
            throw new DaoException("", e);
        } catch (Exception e) {
            logger.error("Exception in getRole() method");
            throw new DaoException("exception in getRole() method", e);
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() throws Exception {
        List<Role> roles = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.role; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Role role = new Role();

                    role.setId(resultSet.getInt("id"));
                    role.setName(resultSet.getString("name"));

                    roles.add(role);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllRoles() method");
                throw new DaoException("exception in getAllRoles() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllRoles() method");
            throw new DaoException("exception in getAllRoles() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllRoles() method");
            throw new DaoException("exception in getAllRoles() method", e);
        }
        return roles;
    }

    @Override
    public void updateRole(Role role) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.role " +
                            "SET name = ? WHERE (id = ?);");


            preparedStatement.setString(1, role.getName());
            preparedStatement.setInt(2, role.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateRole() method");
                throw new DaoException("exception in updateRole() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateRole() method");
            throw new DaoException("exception in updateRole() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateRole() method");
            throw new DaoException("exception in updateRole() method", e);
        }

    }

    @Override
    public void deleteRole(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.role WHERE (id = ?);");

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteRole() method");
                throw new DaoException("exception in deleteRole() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteRole() method");
            throw new DaoException("exception in deleteRole() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteRole() method");
            throw new DaoException("exception in deleteRole() method", e);
        }
    }
}
