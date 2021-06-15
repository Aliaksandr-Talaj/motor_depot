package by.talai.data.dao.impl;

import by.talai.data.dao.AutomobileTypeDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.exception.ConnectionPoolException;
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

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(AutomobileTypeDaoImpl.class);

    public AutomobileTypeDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createAutomobileType(AutomobileType automobileType) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO `motor_depot`.`automobile_type` (`id`, `type`) " +
                            "VALUES (?, ?);");

            preparedStatement.setInt(1, automobileType.getId());
            preparedStatement.setString(2, automobileType.getType());

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in createAutomobileType() method");
            throw new SQLException("exception in createAutomobileType() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createAutomobileType() method");
            throw new ConnectionPoolException("exception in createAutomobileType() method", e);
        } catch (Exception e) {
            logger.error("Exception in createAutomobileType() method");
            throw new Exception("exception in createAutomobileType() method", e);
        }
    }

    @Override
    public AutomobileType findAutomobileType(int id) throws Exception {
        AutomobileType automobileType = new AutomobileType();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.automobile_type where id=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            automobileType.setId(id);
            automobileType.setType(resultSet.getString("type"));

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in findAutomobileType() method");
            throw new SQLException("exception in findAutomobileType() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAutomobileType() method");
            throw new ConnectionPoolException("exception in findAutomobileType() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAutomobileType() method");
            throw new Exception("exception in findAutomobileType() method", e);
        }
        return automobileType;
    }

    @Override
    public Set<AutomobileType> findAllAutomobileTypes() throws Exception {
        Set<AutomobileType> automobileTypeSet = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.automobile_type; ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AutomobileType automobileType = new AutomobileType();

                automobileType.setId(resultSet.getInt("id"));
                automobileType.setType(resultSet.getString("type"));

                automobileTypeSet.add(automobileType);

            }
            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in findAllAutomobileTypes() method");
            throw new SQLException("exception in findAllAutomobileTypes() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAllAutomobileTypes() method");
            throw new ConnectionPoolException("exception in findAllAutomobileTypes() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAllAutomobileTypes() method");
            throw new Exception("exception in findAllAutomobileTypes() method", e);
        }
        return automobileTypeSet;
    }

    @Override
    public void updateAutomobileType(AutomobileType automobileType) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `motor_depot`.`automobile_type` " +
                            "SET `type` = ? WHERE (`id` = ?);");


            preparedStatement.setString(1, automobileType.getType());
            preparedStatement.setInt(2, automobileType.getId());

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in updateAutomobileType() method");
            throw new SQLException("exception in updateAutomobileType() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateAutomobileType() method");
            throw new ConnectionPoolException("exception in updateAutomobileType() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateAutomobileType() method");
            throw new Exception("exception in updateAutomobileType() method", e);
        }

    }

    @Override
    public void deleteAutomobileType(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `motor_depot`.`automobile_type` WHERE (`id` = ?);");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in deleteAutomobileType() method");
            throw new SQLException("exception in deleteAutomobileType() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteAutomobileType() method");
            throw new ConnectionPoolException("exception in deleteAutomobileType() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteAutomobileType() method");
            throw new Exception("exception in deleteAutomobileType() method", e);
        }
    }
}