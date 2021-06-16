package by.talai.data.dao.impl;

import by.talai.data.dao.AutomobileDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.MaintenanceDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.stock.Maintenance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDaoImpl implements MaintenanceDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final AutomobileDao automobileDao = new AutomobileDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(MaintenanceDaoImpl.class);

    public MaintenanceDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createMaintenance(Maintenance maintenance) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.maintenance (id, type, start_time, finish_time, automobile_id)" +
                            " VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, maintenance.getId());
            preparedStatement.setString(2, maintenance.getType());
            preparedStatement.setDate(3, maintenance.getStartTime());
            preparedStatement.setDate(4, maintenance.getFinishTime());
            preparedStatement.setString(5, maintenance.getAutomobile().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);
            } catch (SQLException e) {
                logger.error("Sql exception in createMaintenance() method");
                throw new SQLException("exception in createMaintenance() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createMaintenance() method");
            throw new ConnectionPoolException("exception in createMaintenance() method", e);
        } catch (Exception e) {
            logger.error("Exception in createMaintenance() method");
            throw new Exception("exception in createMaintenance() method", e);
        }
    }

    @Override
    public Maintenance getMaintenance(int id) throws Exception {
        Maintenance maintenance = new Maintenance();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.maintenance WHERE id = ?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                maintenance.setId(id);
                maintenance.setType(resultSet.getString("type"));

                maintenance.setStartTime(resultSet.getDate("start_time"));
                maintenance.setFinishTime(resultSet.getDate("finish_time"));

                String automobileId = resultSet.getString("automobile_id");
                maintenance.setAutomobile(automobileDao.getAutomobile(automobileId));

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getMaintenance() method");
                throw new SQLException("exception in getMaintenance() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getMaintenance() method");
            throw new ConnectionPoolException("exception in getMaintenance() method", e);
        } catch (Exception e) {
            logger.error("Exception in getMaintenance() method");
            throw new Exception("exception in getMaintenance() method", e);
        }
        return maintenance;
    }

    @Override
    public List<Maintenance> getAllMaintenance() throws Exception {
        List<Maintenance> maintenanceList = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.maintenance; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Maintenance maintenance = new Maintenance();

                    maintenance.setId(resultSet.getInt("id"));
                    maintenance.setType(resultSet.getString("type"));

                    maintenance.setStartTime(resultSet.getDate("start_time"));
                    maintenance.setFinishTime(resultSet.getDate("finish_time"));

                    String automobileId = resultSet.getString("automobile_id");
                    maintenance.setAutomobile(automobileDao.getAutomobile(automobileId));

                    maintenanceList.add(maintenance);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getAllMaintenance() method");
                throw new SQLException("exception in getAllMaintenance() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllMaintenance() method");
            throw new ConnectionPoolException("exception in getAllMaintenance() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllMaintenance() method");
            throw new Exception("exception in getAllMaintenance() method", e);
        }
        return maintenanceList;
    }

    @Override
    public void updateMaintenance(Maintenance maintenance) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.maintenance " +
                            "SET type = ?, start_time = ?, finish_time = ?, automobile_id = ?" +
                            "WHERE id = ?;");

            preparedStatement.setString(1, maintenance.getType());
            preparedStatement.setDate(2, maintenance.getStartTime());
            preparedStatement.setDate(3, maintenance.getFinishTime());
            preparedStatement.setString(4, maintenance.getAutomobile().getId());

            preparedStatement.setInt(5, maintenance.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in updateMaintenance() method");
                throw new SQLException("exception in updateMaintenance() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateMaintenance() method");
            throw new ConnectionPoolException("exception in updateMaintenance() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateMaintenance() method");
            throw new Exception("exception in updateMaintenance() method", e);
        }
    }

    @Override
    public void addOrUpdateMaintenance(Maintenance maintenance) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.maintenance (id, type, start_time, finish_time, automobile_id)" +
                            " VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                            "type = ?, start_time = ?, finish_time = ?, automobile_id = ?;");
            preparedStatement.setInt(1, maintenance.getId());

            preparedStatement.setString(2, maintenance.getType());
            preparedStatement.setString(6, maintenance.getType());

            preparedStatement.setDate(3, maintenance.getStartTime());
            preparedStatement.setDate(7, maintenance.getStartTime());

            preparedStatement.setDate(4, maintenance.getFinishTime());
            preparedStatement.setDate(8, maintenance.getFinishTime());

            preparedStatement.setString(5, maintenance.getAutomobile().getId());
            preparedStatement.setString(9, maintenance.getAutomobile().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in addOrUpdateMaintenance() method");
                throw new SQLException("exception in addOrUpdateMaintenance() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addOrUpdateMaintenance() method");
            throw new ConnectionPoolException("exception in addOrUpdateMaintenance() method", e);
        } catch (Exception e) {
            logger.error("Exception in addOrUpdateMaintenance() method");
            throw new Exception("exception in addOrUpdateMaintenance() method", e);
        }
    }

    @Override
    public void deleteMaintenance(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.maintenance WHERE (id = ?);");

            preparedStatement.setInt(1, id);
            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in deleteMaintenance() method");
                throw new SQLException("exception in deleteMaintenance() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteMaintenance() method");
            throw new ConnectionPoolException("exception in deleteMaintenance() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteMaintenance() method");
            throw new Exception("exception in deleteMaintenance() method", e);
        }
    }


}
