package by.talai.data.dao.impl;

import by.talai.data.dao.*;
import by.talai.data.exception.ConnectionPoolException;
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

public class RequestDaoImpl implements RequestDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final ChartererDao chartererDao = new ChartererDaoImpl();
    private final AutomobileTypeDao automobileTypeDao = new AutomobileTypeDaoImpl();
    private final LoadingTypeDao loadingTypeDao = new LoadingTypeDaoImpl();
    private final StatusDao statusDao = new ExecutionStatusDaoImpl();
    private final DeliveryDao deliveryDao = new DeliveryDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(RequestDaoImpl.class);

    public RequestDaoImpl() throws Exception {
    }

    @Override
    public void createRequest(Request request) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            " motor_depot.request (id, filling_date, charterer, required_automobile_type_id," +
                            " required_loading_type_id, status_id)" +
                            " VALUES (?, ?, ?, ?, ?, ?);");

            preparedStatement.setInt(1, request.getId());
            preparedStatement.setDate(2, request.getFillingDate());

            preparedStatement.setInt(3, request.getCharterer().getId());
            preparedStatement.setInt(4, request.getRequiredAutomobileType().getId());
            preparedStatement.setInt(5, request.getRequiredLoadingType().getId());
            preparedStatement.setInt(6, request.getExecutionStatus().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in createRequest() method");
                throw new SQLException("exception in createRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createRequest() method");
            throw new ConnectionPoolException("exception in createRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in createRequest() method");
            throw new Exception("exception in createRequest() method", e);
        }
    }

    @Override
    public Request getRequest(int id) throws Exception {
        Request request = new Request();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.request WHERE id=?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                request.setId(id);
                request.setFillingDate(resultSet.getDate("filling_date"));

                int chartererId = resultSet.getInt("charterer_id");
                request.setCharterer(chartererDao.getCharterer(chartererId));

                int automobileTypeId = resultSet.getInt("required_automobile_type_id");
                request.setRequiredAutomobileType(automobileTypeDao.findAutomobileType(automobileTypeId));

                int loadingTypeId = resultSet.getInt("required_loading_type_id");
                request.setRequiredLoadingType(loadingTypeDao.getLoadingType(loadingTypeId));

                int statusId = resultSet.getInt("status_id");
                request.setExecutionStatus(statusDao.findStatus(statusId));


                List<Delivery> deliveryList = deliveryDao.getAllDeliveriesOfRequest(request);
                request.setDeliveryList(deliveryList);

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getRequest() method");
                throw new SQLException("exception in getRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getRequest() method");
            throw new ConnectionPoolException("exception in getRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in getRequest() method");
            throw new Exception("exception in getRequest() method", e);
        }
        return request;
    }

    @Override
    public List<Request> getAllRequests() throws Exception {
        List<Request> requestList = new ArrayList<>();

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.request; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Request request = new Request();

                    String id = resultSet.getString("id");
                    request.setFillingDate(resultSet.getDate("filling_date"));

                    int chartererId = resultSet.getInt("charterer_id");
                    request.setCharterer(chartererDao.getCharterer(chartererId));

                    int automobileTypeId = resultSet.getInt("required_automobile_type_id");
                    request.setRequiredAutomobileType(automobileTypeDao.findAutomobileType(automobileTypeId));

                    int loadingTypeId = resultSet.getInt("required_loading_type_id");
                    request.setRequiredLoadingType(loadingTypeDao.getLoadingType(loadingTypeId));

                    int statusId = resultSet.getInt("status_id");
                    request.setExecutionStatus(statusDao.findStatus(statusId));


                    List<Delivery> deliveryList = deliveryDao.getAllDeliveriesOfRequest(request);
                    request.setDeliveryList(deliveryList);

                    requestList.add(request);

                }
                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in getAllRequests() method");
                throw new SQLException("exception in getAllRequests() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllRequests() method");
            throw new ConnectionPoolException("exception in getAllRequests() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllRequests() method");
            throw new Exception("exception in getAllRequests() method", e);
        }
        return requestList;
    }

    @Override
    public void updateRequest(Request request) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.request " +
                            " SET filling_date = ?, charterer_id = ?, required_automobile_type_id = ?," +
                            " required_loading_type_id = ?, status_id = ?" +
                            " WHERE (id = ?);");

            preparedStatement.setDate(1, request.getFillingDate());
            preparedStatement.setInt(2, request.getCharterer().getId());
            preparedStatement.setInt(3, request.getRequiredAutomobileType().getId());
            preparedStatement.setInt(4, request.getRequiredLoadingType().getId());
            preparedStatement.setInt(5, request.getExecutionStatus().getId());

            preparedStatement.setInt(6, request.getId());


            try (connection; preparedStatement) {
                List<Delivery> deliveryList = request.getDeliveryList();
                if (deliveryList != null && !deliveryList.isEmpty()) {
                    for (Delivery delivery : deliveryList) {
                        deliveryDao.addOrUpdateDelivery(delivery);
                    }
                }

                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in updateRequest() method");
                throw new SQLException("exception in updateRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateRequest() method");
            throw new ConnectionPoolException("exception in updateRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateRequest() method");
            throw new Exception("exception in updateRequest() method", e);
        }
    }

    @Override
    public void deleteRequest(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.request WHERE (id = ?);");

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in deleteRequest() method");
                throw new SQLException("exception in deleteRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteRequest() method");
            throw new ConnectionPoolException("exception in deleteRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteRequest() method");
            throw new Exception("exception in deleteRequest() method", e);
        }
    }
}
