package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.StatusDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ExecutionStatusDaoImpl implements StatusDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(ExecutionStatusDaoImpl.class);


    public ExecutionStatusDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createStatus(Status executionStatus) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.execution_status (id, status)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, executionStatus.getId());
            preparedStatement.setString(2, executionStatus.getStatus());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in createExecutionStatus() method");
                throw new SQLException("exception in createExecutionStatus() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createExecutionStatus() method");
            throw new ConnectionPoolException("exception in createExecutionStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in createExecutionStatus() method");
            throw new Exception("exception in createExecutionStatus() method", e);
        }
    }

    @Override
    public Status findStatus(int id) throws Exception {
        Status executionStatus = new Status();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.execution_status WHERE id = ?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                executionStatus.setId(id);
                executionStatus.setStatus(resultSet.getString("status"));

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in findExecutionStatus() method");
                throw new SQLException("exception in findExecutionStatus() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findExecutionStatus() method");
            throw new ConnectionPoolException("exception in findExecutionStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in findExecutionStatus() method");
            throw new Exception("exception in findExecutionStatus() method", e);
        }
        return executionStatus;
    }

    @Override
    public Set<Status> findAllStatuses() throws Exception {
        Set<Status> executionStatusSet = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.execution_status; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Status executionStatus = new Status();

                    executionStatus.setId(resultSet.getInt("id"));
                    executionStatus.setStatus(resultSet.getString("status"));

                    executionStatusSet.add(executionStatus);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in findAllExecutionStatuses() method");
                throw new SQLException("Exception in findAllExecutionStatuses() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAllExecutionStatuses() method");
            throw new ConnectionPoolException("Exception in findAllExecutionStatuses() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAllExecutionStatuses() method");
            throw new Exception("Exception in findAllExecutionStatuses() method", e);
        }
        return executionStatusSet;
    }

    @Override
    public void updateStatus(Status executionStatus) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.execution_status " +
                            "SET status = ? WHERE (id = ?);");


            preparedStatement.setString(1, executionStatus.getStatus());
            preparedStatement.setInt(2, executionStatus.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in updateExecutionStatus() method");
                throw new SQLException("exception in updateExecutionStatus() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateExecutionStatus() method");
            throw new ConnectionPoolException("exception in updateExecutionStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateExecutionStatus() method");
            throw new Exception("exception in updateExecutionStatus() method", e);
        }

    }

    @Override
    public void deleteStatus(int id) throws Exception {

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.execution_status WHERE (id = ?);");

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in deleteExecutionStatus() method");
                throw new SQLException("exception in deleteExecutionStatus() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteExecutionStatus() method");
            throw new ConnectionPoolException("exception in deleteExecutionStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteExecutionStatus() method");
            throw new Exception("exception in deleteExecutionStatus() method", e);
        }
    }

}
