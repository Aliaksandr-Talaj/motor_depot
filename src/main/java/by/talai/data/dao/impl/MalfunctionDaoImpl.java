package by.talai.data.dao.impl;

import by.talai.data.dao.AutomobileDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.MalfunctionDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.stock.Malfunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MalfunctionDaoImpl implements MalfunctionDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final AutomobileDao automobileDao = new AutomobileDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(MalfunctionDaoImpl.class);

    public MalfunctionDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createMalfunction(Malfunction malfunction) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.malfunction (id, problem, detection_time, fix_time, automobile_id)" +
                            " VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, malfunction.getId());
            preparedStatement.setString(2, malfunction.getProblem());
            preparedStatement.setDate(3, malfunction.getDetectionTime());
            preparedStatement.setDate(4, malfunction.getFixTime());
            preparedStatement.setString(5, malfunction.getAutomobile().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);
            } catch (SQLException e) {
                logger.error("Sql exception in createMalfunction() method");
                throw new DaoException("exception in createMalfunction() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createMalfunction() method");
            throw new DaoException("exception in createMalfunction() method", e);
        } catch (Exception e) {
            logger.error("Exception in createMalfunction() method");
            throw new DaoException("exception in createMalfunction() method", e);
        }
    }

    @Override
    public Malfunction getMalfunction(int id) throws Exception {
        Malfunction malfunction = new Malfunction();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.malfunction WHERE id=?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                malfunction.setId(id);
                malfunction.setProblem(resultSet.getString("problem"));

                malfunction.setDetectionTime(resultSet.getDate("detection_time"));
                malfunction.setFixTime(resultSet.getDate("fix_time"));

                String automobileId = resultSet.getString("automobile_id");
                malfunction.setAutomobile(automobileDao.getAutomobile(automobileId));

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getMalfunction() method");
                throw new DaoException("exception in getMalfunction() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getMalfunction() method");
            throw new DaoException("exception in getMalfunction() method", e);
        } catch (Exception e) {
            logger.error("Exception in getMalfunction() method");
            throw new DaoException("exception in getMalfunction() method", e);
        }
        return malfunction;
    }

    @Override
    public List<Malfunction> getAllMalfunctions() throws Exception {
        List<Malfunction> malfunctions = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.malfunction; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Malfunction malfunction = new Malfunction();

                    malfunction.setId(resultSet.getInt("id"));
                    malfunction.setProblem(resultSet.getString("problem"));

                    malfunction.setDetectionTime(resultSet.getDate("detection_time"));
                    malfunction.setFixTime(resultSet.getDate("fix_time"));

                    String automobileId = resultSet.getString("automobile_id");
                    malfunction.setAutomobile(automobileDao.getAutomobile(automobileId));

                    malfunctions.add(malfunction);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getAllMalfunctions() method");
                throw new DaoException("exception in getAllMalfunctions() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllMalfunctions() method");
            throw new DaoException("exception in getAllMalfunctions() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllMalfunctions() method");
            throw new DaoException("exception in getAllMalfunctions() method", e);
        }
        return malfunctions;
    }

    @Override
    public void updateMalfunction(Malfunction malfunction) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.malfunction " +
                            "SET problem = ?, detection_time = ?, fix_time = ?, automobile_id = ?" +
                            "WHERE id = ?;");

            preparedStatement.setString(1, malfunction.getProblem());
            preparedStatement.setDate(2, malfunction.getDetectionTime());
            preparedStatement.setDate(3, malfunction.getFixTime());
            preparedStatement.setString(4, malfunction.getAutomobile().getId());

            preparedStatement.setInt(5, malfunction.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in updateMalfunction() method");
                throw new DaoException("exception in updateMalfunction() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateMalfunction() method");
            throw new DaoException("exception in updateMalfunction() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateMalfunction() method");
            throw new DaoException("exception in updateMalfunction() method", e);
        }
    }

    @Override
    public void addOrUpdateMalfunction(Malfunction malfunction) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.malfunction (id, problem, detection_time, fix_time, automobile_id)" +
                            " VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                            "problem = ?, detection_time = ?, fix_time = ?, automobile_id = ?;");
            preparedStatement.setInt(1, malfunction.getId());

            preparedStatement.setString(2, malfunction.getProblem());
            preparedStatement.setString(6, malfunction.getProblem());

            preparedStatement.setDate(3, malfunction.getDetectionTime());
            preparedStatement.setDate(7, malfunction.getDetectionTime());

            preparedStatement.setDate(4, malfunction.getFixTime());
            preparedStatement.setDate(8, malfunction.getFixTime());

            preparedStatement.setString(5, malfunction.getAutomobile().getId());
            preparedStatement.setString(9, malfunction.getAutomobile().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in addOrUpdateMalfunction() method");
                throw new DaoException("exception in addOrUpdateMalfunction() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addOrUpdateMalfunction() method");
            throw new DaoException("exception in addOrUpdateMalfunction() method", e);
        } catch (Exception e) {
            logger.error("Exception in addOrUpdateMalfunction() method");
            throw new DaoException("exception in addOrUpdateMalfunction() method", e);
        }
    }

    @Override
    public void deleteMalfunction(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.malfunction WHERE (id = ?);");

            preparedStatement.setInt(1, id);
            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in deleteMalfunction() method");
                throw new DaoException("exception in deleteMalfunction() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteMalfunction() method");
            throw new DaoException("exception in deleteMalfunction() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteMalfunction() method");
            throw new DaoException("exception in deleteMalfunction() method", e);
        }
    }

}
