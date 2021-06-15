package by.talai.data.dao.impl;

import by.talai.data.dao.AutomobileAttachmentDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.AutomobileAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutomobileAttachmentDaoImpl implements AutomobileAttachmentDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(AutomobileAttachmentDaoImpl.class);

    public AutomobileAttachmentDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createAttachment(AutomobileAttachment automobileAttachment) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO motor_depot.automobile_attachment " +
                            "(id, automobile_id, driver_id, date_of_attachment, date_of_detachment) " +
                            "VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1, automobileAttachment.getId());
            preparedStatement.setString(2, automobileAttachment.getAutomobileId());
            preparedStatement.setInt(3, automobileAttachment.getDriverId());
            preparedStatement.setDate(4, automobileAttachment.getDateOfAttachment());
            preparedStatement.setDate(5, automobileAttachment.getDateOfDetachment());

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in createAttachment() method");
            throw new SQLException("exception in createAttachment() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createAttachment() method");
            throw new ConnectionPoolException("exception in createAttachment() method", e);
        } catch (Exception e) {
            logger.error("Exception in createAttachment() method");
            throw new Exception("exception in createAttachment() method", e);
        }
    }

    @Override
    public List<AutomobileAttachment> findAllAutomobileAttachments() throws Exception {
        List<AutomobileAttachment> automobileAttachments = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.automobile_attachment; ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AutomobileAttachment automobileAttachment = new AutomobileAttachment();

                automobileAttachment.setId(resultSet.getInt("id"));
                automobileAttachment.setAutomobileId(resultSet.getString("automobile_id"));
                automobileAttachment.setDriverId(resultSet.getInt("driver_id"));
                automobileAttachment.setDateOfAttachment(resultSet.getDate("date_of_attachment"));
                automobileAttachment.setDateOfDetachment(resultSet.getDate("date_of_detachment"));

                automobileAttachments.add(automobileAttachment);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in findAllAutomobileAttachments() method");
            throw new SQLException("exception in findAllAutomobileAttachments() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAllAutomobileAttachments() method");
            throw new ConnectionPoolException("exception in findAllAutomobileAttachments() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAllAutomobileAttachments() method");
            throw new Exception("exception in findAllAutomobileAttachments() method", e);
        }
        return automobileAttachments;
    }



    @Override
    public void updateAutomobileAttachment(AutomobileAttachment automobileAttachment) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `motor_depot`.`automobile_attachment` " +
                            "SET automobile_id = ?, driver_id = ?, " +
                            "date_of_attachment = ?, date_of_detachment = ? " +
                            "WHERE (id = ?);");

            preparedStatement.setString(1, automobileAttachment.getAutomobileId());
            preparedStatement.setInt(2, automobileAttachment.getDriverId());
            preparedStatement.setDate(3, automobileAttachment.getDateOfAttachment());
            preparedStatement.setDate(4, automobileAttachment.getDateOfDetachment());

            preparedStatement.setInt(5, automobileAttachment.getId());

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in updateAutomobileAttachment() method");
            throw new SQLException("exception in updateAutomobileAttachment() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateAutomobileAttachment() method");
            throw new ConnectionPoolException("exception in updateAutomobileAttachment() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateAutomobileAttachment() method");
            throw new Exception("exception in updateAutomobileAttachment() method", e);
        }
    }

    @Override
    public void deleteAutomobileAttachment(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `motor_depot`.`automobile_attachment` WHERE (`id` = ?);");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in deleteAutomobileAttachment() method");
            throw new SQLException("exception in deleteAutomobileAttachment() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteAutomobileAttachment() method");
            throw new ConnectionPoolException("exception in deleteAutomobileAttachment() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteAutomobileAttachment() method");
            throw new Exception("exception in deleteAutomobileAttachment() method", e);
        }
    }
}