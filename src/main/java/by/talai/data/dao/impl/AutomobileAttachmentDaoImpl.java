package by.talai.data.dao.impl;

import by.talai.data.dao.AutomobileAttachmentDao;
import by.talai.data.dao.AutomobileDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.UserDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.AutomobileAttachment;
import by.talai.model.personnel.Driver;
import by.talai.model.personnel.User;
import by.talai.model.stock.Automobile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutomobileAttachmentDaoImpl implements AutomobileAttachmentDao {

    static final String CREATE_ATTACHMENT_SQL = "INSERT INTO motor_depot.automobile_attachment " +
            "(automobile_id, driver_id, date_of_attachment, date_of_detachment) VALUES (?, ?, ?, ?);";
    static final String FIND_ALL_AUTOMOBILE_ATTACHMENTS_SQL = "SELECT * FROM motor_depot.automobile_attachment; ";
    static final String FIND_AUTOMOBILE_ATTACHMENTS_OF_DRIVER_SQL =
            "SELECT * FROM motor_depot.automobile_attachment WHERE (driver_id = ?); ";
    static final String FIND_AUTOMOBILE_ATTACHMENTS_OF_AUTOMOBILE_SQL =
            "SELECT * FROM motor_depot.automobile_attachment WHERE (automobile_id = ?); ";
    static final String UPDATE_AUTOMOBILE_ATTACHMENT_SQL =
            "UPDATE motor_depot.automobile_attachment SET automobile_id = ?, driver_id = ?," +
                    " date_of_attachment = ?, date_of_detachment = ? WHERE (id = ?);";
    static final String DELETE_AUTOMOBILE_ATTACHMENT_SQL =
            "DELETE FROM motor_depot.automobile_attachment WHERE (id = ?);";
    static final String GET_LAST_INSERT_ID = "SELECT last_insert_id();";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(AutomobileAttachmentDaoImpl.class);

    public AutomobileAttachmentDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public int createAttachment(AutomobileAttachment automobileAttachment) throws Exception {
        int id = 0;
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_ATTACHMENT_SQL);

            preparedStatement.setString(1, automobileAttachment.getAutomobile().getId());
            preparedStatement.setInt(2, automobileAttachment.getDriver().getId());
            preparedStatement.setDate(3, automobileAttachment.getDateOfAttachment());
            preparedStatement.setDate(4, automobileAttachment.getDateOfDetachment());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();
                try (PreparedStatement preparedStatementForId = connection.prepareStatement(GET_LAST_INSERT_ID);
                     ResultSet resultSet = preparedStatementForId.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    logger.error("Sql exception in createAttachment() method");
                    throw new DaoException("exception in createAttachment() method", e);
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createAttachment() method");
                throw new DaoException("exception in createAttachment() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createAttachment() method");
            throw new DaoException("exception in createAttachment() method", e);
        } catch (Exception e) {
            logger.error("Exception in createAttachment() method");
            throw new DaoException("exception in createAttachment() method", e);
        }
        return id;
    }

    @Override
    public List<AutomobileAttachment> findAllAutomobileAttachments() throws Exception {
        List<AutomobileAttachment> automobileAttachments = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(FIND_ALL_AUTOMOBILE_ATTACHMENTS_SQL);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    AutomobileAttachment automobileAttachment = new AutomobileAttachment();

                    automobileAttachment.setId(resultSet.getInt("id"));

                    String automobileId = resultSet.getString("automobile_id");
                    AutomobileDao automobileDao = new AutomobileDaoImpl();
                    automobileAttachment.setAutomobile(automobileDao.getAutomobile(automobileId));

                    int driverId = resultSet.getInt("driver_id");
                    UserDao userDao = new UserDaoImpl();
                    Driver driver = new Driver();
                    User user = userDao.getUser(driverId);
                    driver.setId(user.getId());
                    driver.setName(user.getName());
                    driver.setSurname(user.getSurname());

                    driver.setAutomobileAttachments(automobileAttachments);
                    automobileAttachment.setDriver(driver);

                    automobileAttachment.setDateOfAttachment(resultSet.getDate("date_of_attachment"));
                    automobileAttachment.setDateOfDetachment(resultSet.getDate("date_of_detachment"));

                    automobileAttachments.add(automobileAttachment);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in findAllAutomobileAttachments() method");
                throw new DaoException("exception in findAllAutomobileAttachments() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAllAutomobileAttachments() method");
            throw new DaoException("exception in findAllAutomobileAttachments() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAllAutomobileAttachments() method");
            throw new DaoException("exception in findAllAutomobileAttachments() method", e);
        }
        return automobileAttachments;
    }


    @Override
    public void updateAutomobileAttachment(AutomobileAttachment automobileAttachment) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_AUTOMOBILE_ATTACHMENT_SQL);

            preparedStatement.setString(1, automobileAttachment.getAutomobile().getId());
            preparedStatement.setInt(2, automobileAttachment.getDriver().getId());
            preparedStatement.setDate(3, automobileAttachment.getDateOfAttachment());
            preparedStatement.setDate(4, automobileAttachment.getDateOfDetachment());

            preparedStatement.setInt(5, automobileAttachment.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateAutomobileAttachment() method");
                throw new DaoException("exception in updateAutomobileAttachment() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateAutomobileAttachment() method");
            throw new DaoException("exception in updateAutomobileAttachment() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateAutomobileAttachment() method");
            throw new DaoException("exception in updateAutomobileAttachment() method", e);
        }
    }

    @Override
    public void deleteAutomobileAttachment(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_AUTOMOBILE_ATTACHMENT_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteAutomobileAttachment() method");
                throw new DaoException("exception in deleteAutomobileAttachment() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteAutomobileAttachment() method");
            throw new DaoException("exception in deleteAutomobileAttachment() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteAutomobileAttachment() method");
            throw new DaoException("exception in deleteAutomobileAttachment() method", e);
        }
    }

    @Override
    public List<AutomobileAttachment> findAttachmentsOfDriver(User user) throws DaoException {
        List<AutomobileAttachment> automobileAttachments = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(FIND_AUTOMOBILE_ATTACHMENTS_OF_DRIVER_SQL);
            preparedStatement.setInt(1, user.getId());

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    AutomobileAttachment automobileAttachment = new AutomobileAttachment();

                    automobileAttachment.setId(resultSet.getInt("id"));

                    String automobileId = resultSet.getString("automobile_id");
                    AutomobileDao automobileDao = new AutomobileDaoImpl();
                    automobileAttachment.setAutomobile(automobileDao.getAutomobile(automobileId));
                    int driverId = resultSet.getInt("driver_id");
                    UserDao userDao = new UserDaoImpl();
                    User usr = userDao.getUser(driverId);
                    Driver driver = new Driver();
                    driver.setId(usr.getId());
                    driver.setName(usr.getName());
                    driver.setSurname(usr.getSurname());
                    driver.setAutomobileAttachments(automobileAttachments);
                    automobileAttachment.setDriver(driver);

                    automobileAttachment.setDateOfAttachment(resultSet.getDate("date_of_attachment"));
                    automobileAttachment.setDateOfDetachment(resultSet.getDate("date_of_detachment"));

                    automobileAttachments.add(automobileAttachment);
                }

            } catch (SQLException e) {
                logger.error("Sql exception in findAttachmentsOfDriver() method");
                throw new DaoException("exception in findAttachmentsOfDriver() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAttachmentsOfDriver() method");
            throw new DaoException("exception in findAttachmentsOfDriver() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAttachmentsOfDriver() method");
            throw new DaoException("exception in findAttachmentsOfDriver() method", e);
        }
        return automobileAttachments;
    }

    @Override
    public List<AutomobileAttachment> findAttachmentsOfAutomobile(Automobile automobile) throws DaoException {
        List<AutomobileAttachment> automobileAttachments = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(FIND_AUTOMOBILE_ATTACHMENTS_OF_AUTOMOBILE_SQL);
            preparedStatement.setString(1, automobile.getId());

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    AutomobileAttachment automobileAttachment = new AutomobileAttachment();

                    automobileAttachment.setId(resultSet.getInt("id"));

                    String automobileId = resultSet.getString("automobile_id");
                    AutomobileDao automobileDao = new AutomobileDaoImpl();
                    automobileAttachment.setAutomobile(automobileDao.getAutomobile(automobileId));
                    int driverId = resultSet.getInt("driver_id");
                    UserDao userDao = new UserDaoImpl();
                    User usr = userDao.getUser(driverId);
                    Driver driver = new Driver();
                    driver.setId(usr.getId());
                    driver.setName(usr.getName());
                    driver.setSurname(usr.getSurname());
                    driver.setAutomobileAttachments(automobileAttachments);
                    automobileAttachment.setDriver(driver);

                    automobileAttachment.setDateOfAttachment(resultSet.getDate("date_of_attachment"));
                    automobileAttachment.setDateOfDetachment(resultSet.getDate("date_of_detachment"));

                    automobileAttachments.add(automobileAttachment);
                }

            } catch (SQLException e) {
                logger.error("Sql exception in findAttachmentsOfAutomobile() method");
                throw new DaoException("exception in findAttachmentsOfAutomobile() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAttachmentsOfAutomobile() method");
            throw new DaoException("exception in findAttachmentsOfAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAttachmentsOfAutomobile() method");
            throw new DaoException("exception in findAttachmentsOfAutomobile() method", e);
        }
        return automobileAttachments;
    }

}
