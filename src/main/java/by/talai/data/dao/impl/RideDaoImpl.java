package by.talai.data.dao.impl;

import by.talai.data.dao.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Ride;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RideDaoImpl implements RideDao {

    static final String CREATE_RIDE_SQL =
            "INSERT INTO motor_depot.ride (id, date, request_id, dispatcher_id, automobile_id, loading_place_id, " +
                    "loading_date, destination_id, term, status_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    static final String GET_RIDE_SQL = "SELECT * FROM motor_depot.ride WHERE id = ?;";
    static final String GET_ALL_RIDES_SQL = "SELECT * FROM motor_depot.ride; ";
    static final String UPDATE_RIDE_SQL =
            "UPDATE motor_depot.ride SET date = ?, request_id = ?, dispatcher_id = ?, automobile_id = ?, " +
                    "loading_place_id = ?, loading_date = ?, destination_id = ?, term = ?, status_id = ? WHERE (id = ?);";
    static final String DELETE_RIDE_SQL = "DELETE FROM motor_depot.ride WHERE (id = ?);";
    static final String GET_LAST_INSERT_ID = "SELECT last_insert_id();";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final RequestDao requestDao = new RequestDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final AutomobileDao automobileDao = new AutomobileDaoImpl();
    private final AddressDao addressDao = new AddressDaoImpl();
    private final StatusDao statusDao = new ExecutionStatusDaoImpl();
    private final CargoDao cargoDao = new CargoDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(RideDaoImpl.class);

    public RideDaoImpl() throws Exception {
    }

    @Override
    public Ride createRide(Ride ride) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_RIDE_SQL);
            int id = ride.getId();
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, ride.getDate());
            preparedStatement.setString(3, ride.getRequest().getId());
            preparedStatement.setInt(4, ride.getDispatcher().getId());
            preparedStatement.setString(5, ride.getAutomobile().getId());

            preparedStatement.setInt(6, ride.getLoadingPlace().getId());
            preparedStatement.setDate(7, ride.getLoadingDate());
            preparedStatement.setInt(8, ride.getDestination().getId());
            preparedStatement.setDate(9, ride.getTerm());
            preparedStatement.setInt(10, ride.getExecutionStatus().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                if (id == 0) {
                    try (PreparedStatement preparedStatementForId = connection.prepareStatement(GET_LAST_INSERT_ID);
                         ResultSet resultSet = preparedStatementForId.executeQuery()) {
                        if (resultSet.next()) {
                            id = resultSet.getInt(1);
                            ride.setId(id);
                        }
                    } catch (SQLException e) {
                        logger.error("Sql exception in createRide() method");
                        throw new DaoException("exception in createRide() method", e);
                    }
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createRide() method");
                throw new DaoException("exception in createRide() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createRide() method");
            throw new DaoException("exception in createRide() method", e);
        } catch (Exception e) {
            logger.error("Exception in createRide() method");
            throw new DaoException("exception in createRide() method", e);
        }
        return ride;
    }

    @Override
    public Ride getRide(int rideId) throws Exception {
        Ride ride = new Ride();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_RIDE_SQL);
            preparedStatement.setInt(1, rideId);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                ride.setId(rideId);

                if (resultSet.next()) {
                    ride.setDate(resultSet.getDate("date"));

                    String requestId = resultSet.getString("request_id");
                    ride.setRequest(requestDao.getRequest(requestId));

                    int dispatcherId = resultSet.getInt("dispatcher_id");
                    ride.setDispatcher(userDao.getUser(dispatcherId));

                    String automobileId = resultSet.getString("automobile_id");
                    ride.setAutomobile(automobileDao.getAutomobile(automobileId));

                    int loadingPlaceId = resultSet.getInt("loading_place_id");
                    ride.setLoadingPlace(addressDao.getAddress(loadingPlaceId));

                    ride.setLoadingDate(resultSet.getDate("loading_date"));

                    int destinationId = resultSet.getInt("destination_id");
                    ride.setDestination(addressDao.getAddress(destinationId));

                    ride.setTerm(resultSet.getDate("term"));

                    int executionStatusId = resultSet.getInt("status_id");
                    ride.setExecutionStatus(statusDao.findStatus(executionStatusId));

                    ride.setCargoList(cargoDao.getAllCargosOfRide(rideId));
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getRide() method");
                throw new DaoException("exception in getRide() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getRide() method");
            throw new DaoException("exception in getRide() method", e);
        } catch (Exception e) {
            logger.error("Exception in getRide() method");
            throw new DaoException("exception in getRide() method", e);
        }
        return ride;
    }

    @Override
    public List<Ride> getAllRides() throws Exception {
        List<Ride> rides = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_RIDES_SQL);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Ride ride = new Ride();

                    int rideId = resultSet.getInt("id");
                    ride.setId(rideId);
                    ride.setDate(resultSet.getDate("date"));

                    String requestId = resultSet.getString("request_id");
                    ride.setRequest(requestDao.getRequest(requestId));

                    int dispatcherId = resultSet.getInt("dispatcher_id");
                    ride.setDispatcher(userDao.getUser(dispatcherId));

                    String automobileId = resultSet.getString("automobile_id");
                    ride.setAutomobile(automobileDao.getAutomobile(automobileId));

                    int loadingPlaceId = resultSet.getInt("loading_place_id");
                    ride.setLoadingPlace(addressDao.getAddress(loadingPlaceId));

                    ride.setLoadingDate(resultSet.getDate("loading_date"));

                    int destinationId = resultSet.getInt("destination_id");
                    ride.setDestination(addressDao.getAddress(destinationId));

                    ride.setTerm(resultSet.getDate("term"));

                    int executionStatusId = resultSet.getInt("status_id");
                    ride.setExecutionStatus(statusDao.findStatus(executionStatusId));

                    ride.setCargoList(cargoDao.getAllCargosOfRide(rideId));

                    rides.add(ride);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllRides() method");
                throw new DaoException("exception in getAllRides() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllRides() method");
            throw new DaoException("exception in getAllRides() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllRides() method");
            throw new DaoException("exception in getAllRides() method", e);
        }
        return rides;
    }

    @Override
    public void updateRide(Ride ride) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_RIDE_SQL);


            preparedStatement.setDate(1, ride.getDate());
            preparedStatement.setString(2, ride.getRequest().getId());
            preparedStatement.setInt(3, ride.getDispatcher().getId());
            preparedStatement.setString(4, ride.getAutomobile().getId());
            preparedStatement.setInt(5, ride.getLoadingPlace().getId());

            preparedStatement.setDate(6, ride.getLoadingDate());
            preparedStatement.setInt(7, ride.getDestination().getId());
            preparedStatement.setDate(8, ride.getTerm());
            preparedStatement.setInt(9, ride.getExecutionStatus().getId());

            preparedStatement.setInt(10, ride.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateRide() method");
                throw new DaoException("exception in updateRide() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateRide() method");
            throw new DaoException("exception in updateRide() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateRide() method");
            throw new DaoException("exception in updateRide() method", e);
        }
    }

    @Override
    public void deleteRide(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_RIDE_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteRide() method");
                throw new DaoException("exception in deleteRide() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteRide() method");
            throw new DaoException("exception in deleteRide() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteRide() method");
            throw new DaoException("exception in deleteRide() method", e);
        }
    }
}
