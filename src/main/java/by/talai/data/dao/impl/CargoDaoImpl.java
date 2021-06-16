package by.talai.data.dao.impl;

import by.talai.data.dao.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Cargo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDaoImpl implements CargoDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final UnitDao unitDao = new UnitDaoImpl();
    private final DeliveryDao deliveryDao = new DeliveryDaoImpl();
    private final RideDao rideDao = new RideDaoImpl();


    public static final Logger logger = LoggerFactory.getLogger(CargoDaoImpl.class);

    public CargoDaoImpl() throws Exception {
    }

    @Override
    public void createCargo(Cargo cargo) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.cargo (id, name, unit_id, delivery_id, ride_id, quantity)" +
                            " VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, cargo.getId());
            preparedStatement.setString(2, cargo.getName());
            preparedStatement.setInt(3, cargo.getUnit().getId());
            preparedStatement.setInt(4, cargo.getDelivery().getId());
            preparedStatement.setInt(5, cargo.getRide().getId());
            preparedStatement.setDouble(6, cargo.getQuantity());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in createCargo() method");
                throw new SQLException("exception in createCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createCargo() method");
            throw new ConnectionPoolException("exception in createCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in createCargo() method");
            throw new Exception("exception in createCargo() method", e);
        }
    }

    @Override
    public Cargo getCargo(int id) throws Exception {
        Cargo cargo = new Cargo();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.cargo WHERE id = ?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                cargo.setId(id);
                cargo.setName(resultSet.getString("name"));

                int unitId = resultSet.getInt("unit_id");
                cargo.setUnit(unitDao.getUnit(unitId));

                int deliveryId = resultSet.getInt("delivery_id");
                cargo.setDelivery(deliveryDao.getDelivery(deliveryId));

                int rideId = resultSet.getInt("ride_id");
                cargo.setRide(rideDao.getRide(rideId));

                cargo.setQuantity(resultSet.getDouble("quantity"));

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getCargo() method");
                throw new SQLException("exception in getCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getCargo() method");
            throw new ConnectionPoolException("exception in getCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in getCargo() method");
            throw new Exception("exception in getCargo() method", e);
        }
        return cargo;
    }

    @Override
    public List<Cargo> getAllCargos() throws Exception {
        List<Cargo> cargos = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.cargo; ");
            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Cargo cargo = new Cargo();

                    cargo.setId(resultSet.getInt("id"));
                    cargo.setName(resultSet.getString("name"));

                    int unitId = resultSet.getInt("unit_id");
                    cargo.setUnit(unitDao.getUnit(unitId));

                    int deliveryId = resultSet.getInt("delivery_id");
                    cargo.setDelivery(deliveryDao.getDelivery(deliveryId));

                    int rideId = resultSet.getInt("ride_id");
                    cargo.setRide(rideDao.getRide(rideId));

                    cargo.setQuantity(resultSet.getDouble("quantity"));

                    cargos.add(cargo);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getAllCargos() method");
                throw new SQLException("exception in getAllCargos() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllCargos() method");
            throw new ConnectionPoolException("exception in getAllCargos() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllCargos() method");
            throw new Exception("exception in getAllCargos() method", e);
        }
        return cargos;
    }

    @Override
    public void updateCargo(Cargo cargo) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.cargo " +
                            "SET name = ?, unit_id = ?, delivery_id = ?, ride_id = ?, quantity = ? " +
                            "WHERE (id = ?);");

            preparedStatement.setString(1, cargo.getName());
            preparedStatement.setInt(2, cargo.getUnit().getId());
            preparedStatement.setInt(3, cargo.getDelivery().getId());
            preparedStatement.setInt(4, cargo.getRide().getId());
            preparedStatement.setDouble(5, cargo.getQuantity());

            preparedStatement.setInt(6, cargo.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in updateCargo() method");
                throw new SQLException("exception in updateCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateCargo() method");
            throw new ConnectionPoolException("exception in updateCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateCargo() method");
            throw new Exception("exception in updateCargo() method", e);
        }
    }

    @Override
    public void deleteCargo(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.cargo WHERE (id = ?);");

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in deleteCargo() method");
                throw new SQLException("exception in deleteCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteCargo() method");
            throw new ConnectionPoolException("exception in deleteCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteCargo() method");
            throw new Exception("exception in deleteCargo() method", e);
        }

    }

    @Override
    public List<Cargo> getAllCargosOfRide(int rideId) throws Exception {
        List<Cargo> cargos = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.cargo " +
                            "WHERE ride_id = ?; ");
            preparedStatement.setInt(1, rideId);
            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Cargo cargo = new Cargo();

                    cargo.setId(resultSet.getInt("id"));
                    cargo.setName(resultSet.getString("name"));

                    int unitId = resultSet.getInt("unit_id");
                    cargo.setUnit(unitDao.getUnit(unitId));

                    int deliveryId = resultSet.getInt("delivery_id");
                    cargo.setDelivery(deliveryDao.getDelivery(deliveryId));

                    cargo.setRide(rideDao.getRide(rideId));

                    cargo.setQuantity(resultSet.getDouble("quantity"));

                    cargos.add(cargo);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getAllCargosOfRide() method");
                throw new SQLException("exception in getAllCargosOfRide() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllCargosOfRide() method");
            throw new ConnectionPoolException("exception in getAllCargosOfRide() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllCargosOfRide() method");
            throw new Exception("exception in getAllCargosOfRide() method", e);
        }
        return cargos;
    }

    @Override
    public List<Cargo> getAllCargosOfDelivery(int deliveryId) throws Exception {
        List<Cargo> cargos = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.cargo " +
                            "WHERE delivery_id = ?; ");
            preparedStatement.setInt(1, deliveryId);
            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Cargo cargo = new Cargo();

                    cargo.setId(resultSet.getInt("id"));
                    cargo.setName(resultSet.getString("name"));

                    int unitId = resultSet.getInt("unit_id");
                    cargo.setUnit(unitDao.getUnit(unitId));

                    cargo.setDelivery(deliveryDao.getDelivery(deliveryId));

                    int rideId = resultSet.getInt("ride_id");
                    cargo.setRide(rideDao.getRide(rideId));

                    cargo.setQuantity(resultSet.getDouble("quantity"));

                    cargos.add(cargo);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getAllCargosOfDelivery() method");
                throw new SQLException("exception in getAllCargosOfDelivery() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllCargosOfDelivery() method");
            throw new ConnectionPoolException("exception in getAllCargosOfDelivery() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllCargosOfDelivery() method");
            throw new Exception("exception in getAllCargosOfDelivery() method", e);
        }
        return cargos;
    }

    @Override
    public void addOrUpdateCargo(Cargo cargo) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            " motor_depot.cargo (id, name, unit_id, delivery_id, ride_id, quantity" +
                            " VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                            " name = ?, unit_id = ?, delivery_id = ?, ride_id = ?, quantity = ?;");

            preparedStatement.setInt(1, cargo.getId());

            preparedStatement.setString(2, cargo.getName());
            preparedStatement.setString(7, cargo.getName());

            preparedStatement.setInt(3, cargo.getUnit().getId());
            preparedStatement.setInt(8, cargo.getUnit().getId());

            preparedStatement.setInt(4, cargo.getDelivery().getId());
            preparedStatement.setInt(9, cargo.getDelivery().getId());

            preparedStatement.setInt(5, cargo.getRide().getId());
            preparedStatement.setInt(10, cargo.getRide().getId());

            preparedStatement.setDouble(6, cargo.getQuantity());
            preparedStatement.setDouble(11, cargo.getQuantity());

            try (connection; preparedStatement) {

                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in addOrUpdateCargo() method");
                throw new SQLException("exception in addOrUpdateCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addOrUpdateCargo() method");
            throw new ConnectionPoolException("exception in addOrUpdateCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in addOrUpdateCargo() method");
            throw new Exception("exception in addOrUpdateCargo() method", e);
        }
    }

}
