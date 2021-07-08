package by.talai.data.dao.impl;

import by.talai.data.dao.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Cargo;
import by.talai.model.Delivery;
import by.talai.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDaoImpl implements DeliveryDao {

    static final String CREATE_DELIVERY_SQL =
            "INSERT INTO motor_depot.delivery (id, loading_place_id, loading_date, destination_id, term, " +
                    "request_id, status_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
    static final String GET_DELIVERY_SQL = "SELECT * FROM motor_depot.delivery WHERE id = ?;";
    static final String GET_ALL_DELIVERIES_SQL = "SELECT * FROM motor_depot.delivery; ";
    static final String UPDATE_DELIVERY_SQL =
            "UPDATE motor_depot.delivery SET loading_place_id = ?, loading_date = ?, destination_id = ?," +
                    " term = ?, request_id = ?, status_id = ? WHERE (id = ?);";
    static final String DELETE_DELIVERY_SQL = "DELETE FROM motor_depot.delivery WHERE (id = ?);";
    static final String GET_ALL_DELIVERIES_OF_REQUEST_SQL = "SELECT * FROM motor_depot.delivery WHERE request_id = ?; ";
    static final String ADD_OR_UPDATE_DELIVERY_SQL =
            "INSERT INTO  motor_depot.delivery (id, loading_place_id, loading_date, destination_id," +
                    " term, request_id, status_id) VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                    " loading_place_id = ?, loading_date = ?, destination_id = ?," +
                    " term = ?, request_id = ?, status_id = ?;";
    static final String GET_LAST_INSERT_ID = "SELECT last_insert_id();";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final CargoDao cargoDao = new CargoDaoImpl();
    private final AddressDao addressDao = new AddressDaoImpl();
    private final StatusDao statusDao = new ExecutionStatusDaoImpl();


    public static final Logger logger = LoggerFactory.getLogger(DeliveryDaoImpl.class);

    public DeliveryDaoImpl() throws Exception {
    }

    @Override
    public Delivery createDelivery(Delivery delivery) throws Exception {
        int id = delivery.getId();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_DELIVERY_SQL);
            preparedStatement.setInt(1, delivery.getId());
            preparedStatement.setInt(2, delivery.getLoadingPlace().getId());
            preparedStatement.setDate(3, delivery.getLoadingDate());
            preparedStatement.setInt(4, delivery.getDestination().getId());
            preparedStatement.setDate(5, delivery.getTerm());
            preparedStatement.setString(6, delivery.getRequest().getId());
            preparedStatement.setInt(7, delivery.getExecutionStatus().getId());

            try (connection; preparedStatement) {

                preparedStatement.executeUpdate();
                connection.commit();

                if (id == 0) {
                    try (PreparedStatement preparedStatementForId = connection.prepareStatement(GET_LAST_INSERT_ID);
                         ResultSet resultSet = preparedStatementForId.executeQuery()) {
                        if (resultSet.next()) {
                            id = resultSet.getInt(1);
                            delivery.setId(id);
                        }
                    } catch (SQLException e) {
                        logger.error("Sql exception in createAddress() method");
                        throw new DaoException("exception in createAddress() method", e);
                    }
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createDelivery() method");
                throw new DaoException("exception in createDelivery() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createDelivery() method");
            throw new DaoException("exception in createDelivery() method", e);
        } catch (Exception e) {
            logger.error("Exception in createDelivery() method");
            throw new DaoException("exception in createDelivery() method", e);
        }
        return delivery;
    }

    @Override
    public Delivery getDelivery(int deliveryId) throws Exception {
        Delivery delivery = new Delivery();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_DELIVERY_SQL);
            preparedStatement.setInt(1, deliveryId);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                delivery.setId(deliveryId);
                if (resultSet.next()) {

                    int loadingPlaceId = resultSet.getInt("loading_place_id");
                    delivery.setLoadingPlace(addressDao.getAddress(loadingPlaceId));

                    delivery.setLoadingDate(resultSet.getDate("loading_date"));

                    int destinationId = resultSet.getInt("destination_id");
                    delivery.setDestination(addressDao.getAddress(destinationId));

                    delivery.setTerm(resultSet.getDate("term"));

                    String requestId = resultSet.getString("request_id");
                    RequestDao requestDao = new RequestDaoImpl();
                    delivery.setRequest(requestDao.getRequest(requestId));

                    int executionStatusId = resultSet.getInt("status_id");
                    delivery.setExecutionStatus(statusDao.findStatus(executionStatusId));

                    delivery.setCargoList(cargoDao.getAllCargosOfDelivery(delivery));
                }

            } catch (SQLException e) {
                logger.error("Sql exception in getDelivery() method");
                throw new DaoException("exception in getDelivery() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getDelivery() method");
            throw new DaoException("exception in getDelivery() method", e);
        } catch (Exception e) {
            logger.error("Exception in getDelivery() method");
            throw new DaoException("exception in getDelivery() method", e);
        }
        return delivery;
    }

    @Override
    public List<Delivery> getAllDeliveries() throws Exception {
        List<Delivery> deliveries = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_DELIVERIES_SQL);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Delivery delivery = new Delivery();

                    int deliveryId = resultSet.getInt("id");
                    delivery.setId(deliveryId);

                    int loadingPlaceId = resultSet.getInt("loading_place_id");
                    delivery.setLoadingPlace(addressDao.getAddress(loadingPlaceId));

                    delivery.setLoadingDate(resultSet.getDate("loading_date"));

                    int destinationId = resultSet.getInt("destination_id");
                    delivery.setDestination(addressDao.getAddress(destinationId));

                    delivery.setTerm(resultSet.getDate("term"));

                    int executionStatusId = resultSet.getInt("status_id");
                    delivery.setExecutionStatus(statusDao.findStatus(executionStatusId));

                    String requestId = resultSet.getString("request_id");
                    RequestDao requestDao = new RequestDaoImpl();
                    delivery.setRequest(requestDao.getRequest(requestId));

                    delivery.setCargoList(cargoDao.getAllCargosOfDelivery(delivery));

                    deliveries.add(delivery);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllDeliveries() method");
                throw new DaoException("exception in getAllDeliveries() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllDeliveries() method");
            throw new DaoException("exception in getAllDeliveries() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllDeliveries() method");
            throw new DaoException("exception in getAllDeliveries() method", e);
        }
        return deliveries;
    }


    @Override
    public void updateDelivery(Delivery delivery) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_DELIVERY_SQL);

            preparedStatement.setInt(1, delivery.getLoadingPlace().getId());
            preparedStatement.setDate(2, delivery.getLoadingDate());
            preparedStatement.setInt(3, delivery.getDestination().getId());
            preparedStatement.setDate(4, delivery.getTerm());
            preparedStatement.setString(5, delivery.getRequest().getId());
            preparedStatement.setInt(6, delivery.getExecutionStatus().getId());

            preparedStatement.setInt(7, delivery.getId());

            try (connection; preparedStatement) {
                List<Cargo> cargoList = delivery.getCargoList();
                if (cargoList != null && !cargoList.isEmpty()) {
                    for (Cargo cargo : cargoList) {
                        cargoDao.addOrUpdateCargo(cargo);
                    }
                }

                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateDelivery() method");
                throw new DaoException("exception in updateDelivery() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateDelivery() method");
            throw new DaoException("exception in updateDelivery() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateDelivery() method");
            throw new DaoException("exception in updateDelivery() method", e);
        }
    }

    @Override
    public void deleteDelivery(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_DELIVERY_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteDelivery() method");
                throw new DaoException("exception in deleteDelivery() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteDelivery() method");
            throw new DaoException("exception in deleteDelivery() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteDelivery() method");
            throw new DaoException("exception in deleteDelivery() method", e);
        }
    }

    @Override
    public List<Delivery> getAllDeliveriesOfRequest(Request request) throws Exception {
        List<Delivery> deliveries = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_DELIVERIES_OF_REQUEST_SQL);
            preparedStatement.setString(1, request.getId());

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {
                connection.close();
                while (resultSet.next()) {

                    Delivery delivery = new Delivery();

                    int deliveryId = resultSet.getInt("id");
                    delivery.setId(deliveryId);

                    int loadingPlaceId = resultSet.getInt("loading_place_id");
                    delivery.setLoadingPlace(addressDao.getAddress(loadingPlaceId));

                    delivery.setLoadingDate(resultSet.getDate("loading_date"));

                    int destinationId = resultSet.getInt("destination_id");
                    delivery.setDestination(addressDao.getAddress(destinationId));

                    delivery.setTerm(resultSet.getDate("term"));

                    int executionStatusId = resultSet.getInt("status_id");
                    delivery.setExecutionStatus(statusDao.findStatus(executionStatusId));

                    delivery.setRequest(request);

                    delivery.setCargoList(cargoDao.getAllCargosOfDelivery(delivery));

                    deliveries.add(delivery);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllDeliveriesOfRequest() method");
                throw new DaoException("exception in getAllDeliveriesOfRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllDeliveriesOfRequest() method");
            throw new DaoException("exception in getAllDeliveriesOfRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllDeliveriesOfRequest() method");
            throw new DaoException("exception in getAllDeliveriesOfRequest() method", e);
        }
        return deliveries;
    }

    @Override
    public void addOrUpdateDelivery(Delivery delivery, boolean cargoListIsSaved) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(ADD_OR_UPDATE_DELIVERY_SQL);

            preparedStatement.setInt(1, delivery.getId());

            preparedStatement.setInt(2, delivery.getLoadingPlace().getId());
            preparedStatement.setInt(8, delivery.getLoadingPlace().getId());

            preparedStatement.setDate(3, delivery.getLoadingDate());
            preparedStatement.setDate(9, delivery.getLoadingDate());

            preparedStatement.setInt(4, delivery.getDestination().getId());
            preparedStatement.setInt(10, delivery.getDestination().getId());

            preparedStatement.setDate(5, delivery.getTerm());
            preparedStatement.setDate(11, delivery.getTerm());

            preparedStatement.setString(6, delivery.getRequest().getId());
            preparedStatement.setString(12, delivery.getRequest().getId());

            preparedStatement.setInt(7, delivery.getExecutionStatus().getId());
            preparedStatement.setInt(13, delivery.getExecutionStatus().getId());


            try (connection; preparedStatement) {

                if (!cargoListIsSaved) {
                    List<Cargo> cargoList = delivery.getCargoList();
                    if (cargoList != null && !cargoList.isEmpty()) {
                        for (Cargo cargo : cargoList) {
                            cargoDao.addOrUpdateCargo(cargo);
                        }
                    }
                }
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in addOrUpdateDelivery() method");
                throw new DaoException("exception in addOrUpdateDelivery() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addOrUpdateDelivery() method");
            throw new DaoException("exception in addOrUpdateDelivery() method", e);
        } catch (Exception e) {
            logger.error("Exception in addOrUpdateDelivery() method");
            throw new DaoException("exception in addOrUpdateDelivery() method", e);
        }
    }

}
