package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.TechnicalStatusDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.stock.TechnicalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TechnicalStatusDaoImpl implements TechnicalStatusDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(TechnicalStatusDaoImpl.class);

    public TechnicalStatusDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createTechnicalStatus(TechnicalStatus technicalStatus) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "`motor_depot`.`technical_status` (`id`, `status`)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, technicalStatus.getId());
            preparedStatement.setString(2, technicalStatus.getStatus());

            preparedStatement.executeUpdate();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in createTechnicalStatus() method");
            throw new SQLException("exception in createTechnicalStatus() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createTechnicalStatus() method");
            throw new ConnectionPoolException("exception in createTechnicalStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in createTechnicalStatus() method");
            throw new Exception("exception in createTechnicalStatus() method", e);
        }
    }

    @Override
    public TechnicalStatus findTechnicalStatus(int id) throws Exception {
        TechnicalStatus technicalStatus = new TechnicalStatus();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.technical_status where id=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            technicalStatus.setId(id);
            technicalStatus.setStatus(resultSet.getString("status"));

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in findTechnicalStatus() method");
            throw new SQLException("exception in findTechnicalStatus() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findTechnicalStatus() method");
            throw new ConnectionPoolException("exception in findTechnicalStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in findTechnicalStatus() method");
            throw new Exception("exception in findTechnicalStatus() method", e);
        }
        return technicalStatus;
    }

    @Override
    public Set<TechnicalStatus> findAllTechnicalStatuses() throws Exception {
        Set<TechnicalStatus> technicalStatusSet = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.technical_status; ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TechnicalStatus technicalStatus = new TechnicalStatus();

                technicalStatus.setId(resultSet.getInt("id"));
                technicalStatus.setStatus(resultSet.getString("status"));

                technicalStatusSet.add(technicalStatus);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in findAllTechnicalStatuses() method");
            throw new SQLException("Exception in findAllTechnicalStatuses() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAllTechnicalStatuses() method");
            throw new ConnectionPoolException("Exception in findAllTechnicalStatuses() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAllTechnicalStatuses() method");
            throw new Exception("Exception in findAllTechnicalStatuses() method", e);
        }
        return technicalStatusSet;
    }

    @Override
    public void updateTechnicalStatus(TechnicalStatus technicalStatus) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `motor_depot`.`technical_status` " +
                            "SET `status` = ? WHERE (`id` = ?);");


            preparedStatement.setString(1, technicalStatus.getStatus());
            preparedStatement.setInt(2, technicalStatus.getId());

            preparedStatement.executeUpdate();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in updateTechnicalStatus() method");
            throw new SQLException("exception in updateTechnicalStatus() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateTechnicalStatus() method");
            throw new ConnectionPoolException("exception in updateTechnicalStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateTechnicalStatus() method");
            throw new Exception("exception in updateTechnicalStatus() method", e);
        }

    }

    @Override
    public void deleteTechnicalStatus(int id) throws Exception {

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `motor_depot`.`technical_status` WHERE (`id` = ?);");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in deleteTechnicalStatus() method");
            throw new SQLException("exception in deleteTechnicalStatus() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteTechnicalStatus() method");
            throw new ConnectionPoolException("exception in deleteTechnicalStatus() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteTechnicalStatus() method");
            throw new Exception("exception in deleteTechnicalStatus() method", e);
        }
    }
}
