package by.talai.data.dao.impl;

import by.talai.data.dao.AutomobileTypeDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.AutomobileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AutomobileTypeDaoImpl implements AutomobileTypeDao {

    static final String CREATE_AUTOMOBILE_TYPE_SQL =
            "INSERT INTO motor_depot.automobile_type (id, type) VALUES (?, ?);";
    static final String FIND_AUTOMOBILE_TYPE_SQL = "SELECT * FROM motor_depot.automobile_type WHERE id=?;";
    static final String FIND_ALL_AUTOMOBILE_TYPES_SQL = "SELECT * FROM motor_depot.automobile_type; ";
    static final String UPDATE_AUTOMOBILE_TYPE_SQL =
            "UPDATE motor_depot.automobile_type SET type = ? WHERE (id = ?);";
    static final String DELETE_AUTOMOBILE_TYPE_SQL = "DELETE FROM motor_depot.automobile_type WHERE (id = ?);";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(AutomobileTypeDaoImpl.class);

    public AutomobileTypeDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createAutomobileType(AutomobileType automobileType) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO motor_depot.automobile_type (id, type) " +
                            "VALUES (?, ?);");

            preparedStatement.setInt(1, automobileType.getId());
            preparedStatement.setString(2, automobileType.getType());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in createAutomobileType() method");
                throw new DaoException("exception in createAutomobileType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createAutomobileType() method");
            throw new DaoException("exception in createAutomobileType() method", e);
        } catch (Exception e) {
            logger.error("Exception in createAutomobileType() method");
            throw new DaoException("exception in createAutomobileType() method", e);
        }
    }

    @Override
    public AutomobileType findAutomobileType(int id) throws Exception {
        AutomobileType automobileType = new AutomobileType();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(FIND_AUTOMOBILE_TYPE_SQL);
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                automobileType.setId(id);
                resultSet.next();
                automobileType.setType(resultSet.getString("type"));


            } catch (SQLException e) {
                logger.error("Sql exception in findAutomobileType() method");
                throw new DaoException("exception in findAutomobileType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAutomobileType() method");
            throw new DaoException("exception in findAutomobileType() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAutomobileType() method");
            throw new DaoException("exception in findAutomobileType() method", e);
        }
        return automobileType;
    }

    @Override
    public Set<AutomobileType> findAllAutomobileTypes() throws Exception {
        Set<AutomobileType> automobileTypeSet = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(FIND_ALL_AUTOMOBILE_TYPES_SQL);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    AutomobileType automobileType = new AutomobileType();

                    automobileType.setId(resultSet.getInt("id"));
                    automobileType.setType(resultSet.getString("type"));

                    automobileTypeSet.add(automobileType);

                }

            } catch (SQLException e) {
                logger.error("Sql exception in findAllAutomobileTypes() method");
                throw new DaoException("exception in findAllAutomobileTypes() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAllAutomobileTypes() method");
            throw new DaoException("exception in findAllAutomobileTypes() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAllAutomobileTypes() method");
            throw new DaoException("exception in findAllAutomobileTypes() method", e);
        }
        return automobileTypeSet;
    }

    @Override
    public void updateAutomobileType(AutomobileType automobileType) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_AUTOMOBILE_TYPE_SQL);

            preparedStatement.setString(1, automobileType.getType());
            preparedStatement.setInt(2, automobileType.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateAutomobileType() method");
                throw new DaoException("exception in updateAutomobileType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateAutomobileType() method");
            throw new DaoException("exception in updateAutomobileType() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateAutomobileType() method");
            throw new DaoException("exception in updateAutomobileType() method", e);
        }
    }

    @Override
    public void deleteAutomobileType(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_AUTOMOBILE_TYPE_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteAutomobileType() method");
                throw new DaoException("exception in deleteAutomobileType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteAutomobileType() method");
            throw new DaoException("exception in deleteAutomobileType() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteAutomobileType() method");
            throw new DaoException("exception in deleteAutomobileType() method", e);
        }
    }
}
