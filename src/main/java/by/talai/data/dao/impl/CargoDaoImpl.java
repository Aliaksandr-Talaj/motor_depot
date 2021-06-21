package by.talai.data.dao.impl;

import by.talai.data.dao.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
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

    static final String CREATE_CARGO_SQL =
            "INSERT INTO motor_depot.cargo (id, name, unit_id, delivery_id, ride_id, quantity)" +
                    " VALUES (?, ?, ?, ?, ?, ?);";
    static final String GET_CARGO_SQL = "SELECT * FROM motor_depot.cargo WHERE id = ?;";
    static final String GET_ALL_CARGOS_SQL = "SELECT * FROM motor_depot.cargo; ";
    static final String UPDATE_CARGO_SQL = "UPDATE motor_depot.cargo " +
            "SET name = ?, unit_id = ?, delivery_id = ?, ride_id = ?, quantity = ? WHERE (id = ?);";
    static final String DELETE_CARGO_SQL = "DELETE FROM motor_depot.cargo WHERE (id = ?);";
    static final String GET_ALL_CARGOS_OF_RIDE_SQL = "SELECT * FROM motor_depot.cargo WHERE ride_id = ?; ";
    static final String GET_ALL_CARGOS_OF_DELIVERY_SQL = "SELECT * FROM motor_depot.cargo WHERE delivery_id = ?; ";
    static final String ADD_OR_UPDATE_CARGO_SQL =
            "INSERT INTO  motor_depot.cargo (id, name, unit_id, delivery_id, ride_id, quantity" +
                    " VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                    " name = ?, unit_id = ?, delivery_id = ?, ride_id = ?, quantity = ?;";


    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final UnitDao unitDao = new UnitDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(CargoDaoImpl.class);

    public CargoDaoImpl() throws Exception {
    }

    @Override
    public void createCargo(Cargo cargo) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_CARGO_SQL);
            preparedStatement.setInt(1, cargo.getId());
            preparedStatement.setString(2, cargo.getName());
            preparedStatement.setInt(3, cargo.getUnit().getId());
            preparedStatement.setInt(4, cargo.getDelivery().getId());
            preparedStatement.setInt(5, cargo.getRide().getId());
            preparedStatement.setDouble(6, cargo.getQuantity());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in createCargo() method");
                throw new DaoException("exception in createCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createCargo() method");
            throw new DaoException("exception in createCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in createCargo() method");
            throw new DaoException("exception in createCargo() method", e);
        }
    }

    @Override
    public Cargo getCargo(int id) throws Exception {
        Cargo cargo = new Cargo();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_CARGO_SQL);
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                cargo.setId(id);
                resultSet.next();
                cargo.setName(resultSet.getString("name"));

                int unitId = resultSet.getInt("unit_id");
                cargo.setUnit(unitDao.getUnit(unitId));

                int deliveryId = resultSet.getInt("delivery_id");
                DeliveryDao deliveryDao = new DeliveryDaoImpl();
                cargo.setDelivery(deliveryDao.getDelivery(deliveryId));

                int rideId = resultSet.getInt("ride_id");
                RideDao rideDao = new RideDaoImpl();
                cargo.setRide(rideDao.getRide(rideId));

                cargo.setQuantity(resultSet.getDouble("quantity"));


            } catch (SQLException e) {
                logger.error("Sql exception in getCargo() method");
                throw new DaoException("exception in getCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getCargo() method");
            throw new DaoException("exception in getCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in getCargo() method");
            throw new DaoException("exception in getCargo() method", e);
        }
        return cargo;
    }

    @Override
    public List<Cargo> getAllCargos() throws Exception {
        List<Cargo> cargos = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_CARGOS_SQL);
            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Cargo cargo = new Cargo();

                    cargo.setId(resultSet.getInt("id"));
                    cargo.setName(resultSet.getString("name"));

                    int unitId = resultSet.getInt("unit_id");
                    cargo.setUnit(unitDao.getUnit(unitId));

                    int deliveryId = resultSet.getInt("delivery_id");
                    DeliveryDao deliveryDao = new DeliveryDaoImpl();
                    cargo.setDelivery(deliveryDao.getDelivery(deliveryId));

                    int rideId = resultSet.getInt("ride_id");
                    RideDao rideDao = new RideDaoImpl();
                    cargo.setRide(rideDao.getRide(rideId));

                    cargo.setQuantity(resultSet.getDouble("quantity"));

                    cargos.add(cargo);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllCargos() method");
                throw new DaoException("exception in getAllCargos() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllCargos() method");
            throw new DaoException("exception in getAllCargos() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllCargos() method");
            throw new DaoException("exception in getAllCargos() method", e);
        }
        return cargos;
    }

    @Override
    public void updateCargo(Cargo cargo) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_CARGO_SQL);

            preparedStatement.setString(1, cargo.getName());
            preparedStatement.setInt(2, cargo.getUnit().getId());
            preparedStatement.setInt(3, cargo.getDelivery().getId());
            preparedStatement.setInt(4, cargo.getRide().getId());
            preparedStatement.setDouble(5, cargo.getQuantity());

            preparedStatement.setInt(6, cargo.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateCargo() method");
                throw new DaoException("exception in updateCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateCargo() method");
            throw new DaoException("exception in updateCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateCargo() method");
            throw new DaoException("exception in updateCargo() method", e);
        }
    }

    @Override
    public void deleteCargo(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_CARGO_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteCargo() method");
                throw new DaoException("exception in deleteCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteCargo() method");
            throw new DaoException("exception in deleteCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteCargo() method");
            throw new DaoException("exception in deleteCargo() method", e);
        }

    }

    @Override
    public List<Cargo> getAllCargosOfRide(int rideId) throws Exception {
        List<Cargo> cargos = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_CARGOS_OF_RIDE_SQL);
            preparedStatement.setInt(1, rideId);
            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Cargo cargo = new Cargo();

                    cargo.setId(resultSet.getInt("id"));
                    cargo.setName(resultSet.getString("name"));

                    int unitId = resultSet.getInt("unit_id");
                    cargo.setUnit(unitDao.getUnit(unitId));

                    int deliveryId = resultSet.getInt("delivery_id");
                    DeliveryDao deliveryDao = new DeliveryDaoImpl();
                    cargo.setDelivery(deliveryDao.getDelivery(deliveryId));
                    RideDao rideDao = new RideDaoImpl();
                    cargo.setRide(rideDao.getRide(rideId));

                    cargo.setQuantity(resultSet.getDouble("quantity"));

                    cargos.add(cargo);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllCargosOfRide() method");
                throw new DaoException("exception in getAllCargosOfRide() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllCargosOfRide() method");
            throw new DaoException("exception in getAllCargosOfRide() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllCargosOfRide() method");
            throw new DaoException("exception in getAllCargosOfRide() method", e);
        }
        return cargos;
    }

    @Override
    public List<Cargo> getAllCargosOfDelivery(int deliveryId) throws Exception {
        List<Cargo> cargos = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_CARGOS_OF_DELIVERY_SQL);
            preparedStatement.setInt(1, deliveryId);
            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Cargo cargo = new Cargo();

                    cargo.setId(resultSet.getInt("id"));
                    cargo.setName(resultSet.getString("name"));

                    int unitId = resultSet.getInt("unit_id");
                    cargo.setUnit(unitDao.getUnit(unitId));
                    DeliveryDao deliveryDao = new DeliveryDaoImpl();
                    cargo.setDelivery(deliveryDao.getDelivery(deliveryId));

                    int rideId = resultSet.getInt("ride_id");
                    RideDao rideDao = new RideDaoImpl();
                    cargo.setRide(rideDao.getRide(rideId));

                    cargo.setQuantity(resultSet.getDouble("quantity"));

                    cargos.add(cargo);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllCargosOfDelivery() method");
                throw new DaoException("exception in getAllCargosOfDelivery() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllCargosOfDelivery() method");
            throw new DaoException("exception in getAllCargosOfDelivery() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllCargosOfDelivery() method");
            throw new DaoException("exception in getAllCargosOfDelivery() method", e);
        }
        return cargos;
    }

    @Override
    public void addOrUpdateCargo(Cargo cargo) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(ADD_OR_UPDATE_CARGO_SQL);

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


            } catch (SQLException e) {
                logger.error("Sql exception in addOrUpdateCargo() method");
                throw new DaoException("exception in addOrUpdateCargo() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addOrUpdateCargo() method");
            throw new DaoException("exception in addOrUpdateCargo() method", e);
        } catch (Exception e) {
            logger.error("Exception in addOrUpdateCargo() method");
            throw new DaoException("exception in addOrUpdateCargo() method", e);
        }
    }

}
