package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.RoleDao;
import by.talai.data.dao.UserDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.personnel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final RoleDao roleDao = new RoleDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createUser(User user) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.user (id, name, surname, login, password, role_id)" +
                            " VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());

            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getRole().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in createUser() method");
                throw new DaoException("exception in createUser() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createUser() method");
            throw new DaoException("exception in createUser() method", e);
        } catch (Exception e) {
            logger.error("Exception in createUser() method");
            throw new DaoException("exception in createUser() method", e);
        }
    }

    @Override
    public User getUser(int id) throws Exception {
        User user = new User();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.user WHERE id = ?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                user.setId(id);
                user.setName(resultSet.getString("name"));
                user.setName(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));

                int roleId = resultSet.getInt("role_id");
                user.setRole(roleDao.getRole(roleId));

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getUser() method");
                throw new DaoException("exception in getUser() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getUser() method");
            throw new DaoException("exception in getUser() method", e);
        } catch (Exception e) {
            logger.error("Exception in getUser() method");
            throw new DaoException("exception in getUser() method", e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.user; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    User user = new User();

                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setName(resultSet.getString("surname"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));

                    int roleId = resultSet.getInt("role_id");
                    user.setRole(roleDao.getRole(roleId));

                    users.add(user);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getAllUsers() method");
                throw new DaoException("exception in getAllUsers() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllUsers() method");
            throw new DaoException("exception in getAllUsers() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllUsers() method");
            throw new DaoException("exception in getAllUsers() method", e);
        }
        return users;
    }

    @Override
    public List<User> getAllUsersWithRole(int roleId) throws Exception {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.user " +
                            "WHERE role_id = ?; ");
            preparedStatement.setInt(1, roleId);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    User user = new User();

                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setName(resultSet.getString("surname"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));

                    user.setRole(roleDao.getRole(roleId));

                    users.add(user);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getAllUsersWithRole() method");
                throw new DaoException("exception in getAllUsersWithRole() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllUsersWithRole() method");
            throw new DaoException("exception in getAllUsersWithRole() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllUsersWithRole() method");
            throw new DaoException("exception in getAllUsersWithRole() method", e);
        }
        return users;
    }

    @Override
    public void updateUser(User user) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.user " +
                            "SET name = ?, surname = ?, login = ?, password = ?, role_id = ? " +
                            "WHERE (id = ?);");


            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRole().getId());

            preparedStatement.setInt(6, user.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in updateUser() method");
                throw new DaoException("exception in updateUser() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateUser() method");
            throw new DaoException("exception in updateUser() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateUser() method");
            throw new DaoException("exception in updateUser() method", e);
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.user WHERE (id = ?);");

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in deleteUser() method");
                throw new DaoException("exception in deleteUser() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteUser() method");
            throw new DaoException("exception in deleteUser() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteUser() method");
            throw new DaoException("exception in deleteUser() method", e);
        }
    }
}
