package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.StatusDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TechnicalStatusDaoImpl implements StatusDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(TechnicalStatusDaoImpl.class);

    public TechnicalStatusDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createStatus(Status technicalStatus) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.technical_status (id, status)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, technicalStatus.getId());
            preparedStatement.setString(2, technicalStatus.getStatus());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in createTechnicalStatus() method");
                throw new DaoException("exception in createTechnicalStatus() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createTechnicalStatus() method");
            throw new DaoException("exception in createTechnicalStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in createTechnicalStatus() method");
            throw new DaoException("exception in createTechnicalStatus() method", e);
        }
    }

    @Override
    public Status findStatus(int id) throws Exception {
        Status technicalStatus = new Status();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.technical_status WHERE id = ?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                technicalStatus.setId(id);

                if (resultSet.next()) {
                    technicalStatus.setStatus(resultSet.getString("status"));
                }


            } catch (SQLException e) {
                logger.error("Sql exception in findTechnicalStatus() method");
                throw new DaoException("exception in findTechnicalStatus() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findTechnicalStatus() method");
            throw new DaoException("exception in findTechnicalStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in findTechnicalStatus() method");
            throw new DaoException("exception in findTechnicalStatus() method", e);
        }
        return technicalStatus;
    }

    @Override
    public Set<Status> findAllStatuses() throws Exception {
        Set<Status> technicalStatusSet = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.technical_status; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Status technicalStatus = new Status();

                    technicalStatus.setId(resultSet.getInt("id"));
                    technicalStatus.setStatus(resultSet.getString("status"));

                    technicalStatusSet.add(technicalStatus);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in findAllTechnicalStatuses() method");
                throw new DaoException("Exception in findAllTechnicalStatuses() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAllTechnicalStatuses() method");
            throw new DaoException("Exception in findAllTechnicalStatuses() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAllTechnicalStatuses() method");
            throw new DaoException("Exception in findAllTechnicalStatuses() method", e);
        }
        return technicalStatusSet;
    }

    @Override
    public void updateStatus(Status technicalStatus) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.technical_status " +
                            "SET status = ? WHERE (id = ?);");


            preparedStatement.setString(1, technicalStatus.getStatus());
            preparedStatement.setInt(2, technicalStatus.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateTechnicalStatus() method");
                throw new DaoException("exception in updateTechnicalStatus() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateTechnicalStatus() method");
            throw new DaoException("exception in updateTechnicalStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateTechnicalStatus() method");
            throw new DaoException("exception in updateTechnicalStatus() method", e);
        }

    }

    @Override
    public void deleteStatus(int id) throws Exception {

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.technical_status WHERE (id = ?);");

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteTechnicalStatus() method");
                throw new DaoException("exception in deleteTechnicalStatus() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteTechnicalStatus() method");
            throw new DaoException("exception in deleteTechnicalStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteTechnicalStatus() method");
            throw new DaoException("exception in deleteTechnicalStatus() method", e);
        }
    }
}
