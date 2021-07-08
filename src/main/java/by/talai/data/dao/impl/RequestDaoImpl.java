package by.talai.data.dao.impl;

import by.talai.data.dao.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Delivery;
import by.talai.model.Request;
import by.talai.model.stock.Equipment;
import by.talai.service.dto.RequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequestDaoImpl implements RequestDao {

    static final String CREATE_REQUEST_SQL =
            "INSERT INTO motor_depot.request (id, filling_date, charterer_id, required_automobile_type_id," +
                    " required_loading_type_id, status_id)  VALUES (?, ?, ?, ?, ?, ?);";
    static final String CREATE_REQUEST_LT_REQ_SQL = "INSERT INTO motor_depot.request" +
            " (id, filling_date, charterer_id, required_loading_type_id, status_id) VALUES (?, ?, ?, ?, ?);";
    static final String CREATE_REQUEST_AT_REQ_SQL = "INSERT INTO motor_depot.request" +
            " (id, filling_date, charterer_id, required_automobile_type_id, status_id) VALUES (?, ?, ?, ?, ?);";
    static final String CREATE_REQUEST_NO_REQ_SQL = "INSERT INTO motor_depot.request" +
            " (id, filling_date, charterer_id,  status_id) VALUES (?, ?, ?, ?);";
    static final String GET_REQUEST_SQL = "SELECT * FROM motor_depot.request WHERE id=?;";
    static final String GET_ALL_REQUESTS_SQL = "SELECT * FROM motor_depot.request; ";
    static final String UPDATE_REQUEST_SQL =
            "UPDATE motor_depot.request SET filling_date = ?, charterer_id = ?, required_automobile_type_id = ?," +
                    " required_loading_type_id = ?, status_id = ? WHERE (id = ?);";

    static final String DELETE_REQUEST = "DELETE FROM motor_depot.request WHERE (id = ?);";

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
        if (request.getRequiredAutomobileType() == null
                && request.getRequiredLoadingType() == null) {
            createRequestWithNoRequirements(request);
            return;
        }
        if (request.getRequiredAutomobileType() == null) {
            createRequestAutomobileTypeRequired(request);
            return;
        }
        if (request.getRequiredLoadingType() == null) {
            createRequestLoadingTypeRequired(request);
            return;
        }

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_REQUEST_SQL);

            preparedStatement.setString(1, request.getId());
            preparedStatement.setDate(2, request.getFillingDate());
            preparedStatement.setInt(3, request.getCharterer().getId());
            preparedStatement.setInt(4, request.getRequiredAutomobileType().getId());
            preparedStatement.setInt(5, request.getRequiredLoadingType().getId());
            preparedStatement.setInt(6, request.getExecutionStatus().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();
                if (request.getEquipmentSet() != null && !request.getEquipmentSet().isEmpty()) {
                    EquipmentDao equipmentDao = new EquipmentDaoImpl();
                    for (Equipment equipment : request.getEquipmentSet()) {
                        equipmentDao.addEquipmentToRequest(equipment.getId(), request.getId());
                    }
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createRequest() method");
                throw new DaoException("exception in createRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createRequest() method");
            throw new DaoException("exception in createRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in createRequest() method");
            throw new DaoException("exception in createRequest() method", e);
        }
    }

    private void createRequestLoadingTypeRequired(Request request) throws DaoException {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_REQUEST_LT_REQ_SQL);

            preparedStatement.setString(1, request.getId());
            preparedStatement.setDate(2, request.getFillingDate());
            preparedStatement.setInt(3, request.getCharterer().getId());
            preparedStatement.setInt(4, request.getRequiredLoadingType().getId());
            preparedStatement.setInt(5, request.getExecutionStatus().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();
                if (request.getEquipmentSet() != null && !request.getEquipmentSet().isEmpty()) {
                    EquipmentDao equipmentDao = new EquipmentDaoImpl();
                    for (Equipment equipment : request.getEquipmentSet()) {
                        equipmentDao.addEquipmentToRequest(equipment.getId(), request.getId());
                    }
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createRequest() method");
                throw new DaoException("exception in createRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createRequest() method");
            throw new DaoException("exception in createRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in createRequest() method");
            throw new DaoException("exception in createRequest() method", e);
        }
    }

    private void createRequestAutomobileTypeRequired(Request request) throws DaoException {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_REQUEST_AT_REQ_SQL);

            preparedStatement.setString(1, request.getId());
            preparedStatement.setDate(2, request.getFillingDate());
            preparedStatement.setInt(3, request.getCharterer().getId());
            preparedStatement.setInt(4, request.getRequiredAutomobileType().getId());
            preparedStatement.setInt(5, request.getExecutionStatus().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();
                if (request.getEquipmentSet() != null && !request.getEquipmentSet().isEmpty()) {
                    EquipmentDao equipmentDao = new EquipmentDaoImpl();
                    for (Equipment equipment : request.getEquipmentSet()) {
                        equipmentDao.addEquipmentToRequest(equipment.getId(), request.getId());
                    }
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createRequest() method");
                throw new DaoException("exception in createRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createRequest() method");
            throw new DaoException("exception in createRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in createRequest() method");
            throw new DaoException("exception in createRequest() method", e);
        }
    }

    private void createRequestWithNoRequirements(Request request) throws DaoException {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_REQUEST_NO_REQ_SQL);

            preparedStatement.setString(1, request.getId());
            preparedStatement.setDate(2, request.getFillingDate());
            preparedStatement.setInt(3, request.getCharterer().getId());
            preparedStatement.setInt(4, request.getExecutionStatus().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();
                if (request.getEquipmentSet() != null && !request.getEquipmentSet().isEmpty()) {
                    EquipmentDao equipmentDao = new EquipmentDaoImpl();
                    for (Equipment equipment : request.getEquipmentSet()) {
                        equipmentDao.addEquipmentToRequest(equipment.getId(), request.getId());
                    }
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createRequest() method");
                throw new DaoException("exception in createRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createRequest() method");
            throw new DaoException("exception in createRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in createRequest() method");
            throw new DaoException("exception in createRequest() method", e);
        }
    }


    @Override
    public Request getRequest(String id) throws Exception {
        Request request = new Request();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_REQUEST_SQL);
            preparedStatement.setString(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                request.setId(id);

                if (resultSet.next()) {
                    request.setFillingDate(resultSet.getDate("filling_date"));

                    int automobileTypeId = resultSet.getInt("required_automobile_type_id");
                    request.setRequiredAutomobileType(automobileTypeDao.findAutomobileType(automobileTypeId));

                    EquipmentDao equipmentDao = new EquipmentDaoImpl();
                    Set<Equipment> equipmentSet = equipmentDao.getAllEquipmentOfRequest(id);
                    request.setEquipmentSet(equipmentSet);

                    int loadingTypeId = resultSet.getInt("required_loading_type_id");
                    request.setRequiredLoadingType(loadingTypeDao.getLoadingType(loadingTypeId));

                    int chartererId = resultSet.getInt("charterer_id");
                    request.setCharterer(chartererDao.getCharterer(chartererId));

                    int statusId = resultSet.getInt("status_id");
                    request.setExecutionStatus(statusDao.findStatus(statusId));

                    List<Delivery> deliveryList = deliveryDao.getAllDeliveriesOfRequest(request);
                    request.setDeliveryList(deliveryList);

                }


            } catch (SQLException e) {
                logger.error("Sql exception in getRequest() method");
                throw new DaoException("exception in getRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getRequest() method");
            throw new DaoException("exception in getRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in getRequest() method");
            throw new DaoException("exception in getRequest() method", e);
        }
        return request;
    }

    @Override
    public List<Request> getAllRequests() throws Exception {
        List<Request> requestList = new ArrayList<>();

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_REQUESTS_SQL);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Request request = new Request();
                    String id = resultSet.getString("id");
                    request.setId(id);
                    request.setFillingDate(resultSet.getDate("filling_date"));

                    int automobileTypeId = resultSet.getInt("required_automobile_type_id");
                    request.setRequiredAutomobileType(automobileTypeDao.findAutomobileType(automobileTypeId));

                    EquipmentDao equipmentDao = new EquipmentDaoImpl();
                    Set<Equipment> equipmentSet = equipmentDao.getAllEquipmentOfRequest(id);
                    request.setEquipmentSet(equipmentSet);

                    int loadingTypeId = resultSet.getInt("required_loading_type_id");
                    request.setRequiredLoadingType(loadingTypeDao.getLoadingType(loadingTypeId));

                    int chartererId = resultSet.getInt("charterer_id");
                    request.setCharterer(chartererDao.getCharterer(chartererId));

                    int statusId = resultSet.getInt("status_id");
                    request.setExecutionStatus(statusDao.findStatus(statusId));

                    List<Delivery> deliveryList = deliveryDao.getAllDeliveriesOfRequest(request);
                    request.setDeliveryList(deliveryList);


                    requestList.add(request);

                }

            } catch (SQLException e) {
                logger.error("Sql exception in getAllRequests() method");
                throw new DaoException("exception in getAllRequests() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllRequests() method");
            throw new DaoException("exception in getAllRequests() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllRequests() method");
            throw new DaoException("exception in getAllRequests() method", e);
        }
        return requestList;
    }

    @Override
    public void updateRequest(Request request) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_REQUEST_SQL);

            preparedStatement.setDate(1, request.getFillingDate());
            preparedStatement.setInt(2, request.getCharterer().getId());
            preparedStatement.setInt(3, request.getRequiredAutomobileType().getId());
            preparedStatement.setInt(4, request.getRequiredLoadingType().getId());
            preparedStatement.setInt(5, request.getExecutionStatus().getId());

            preparedStatement.setString(6, request.getId());


            try (connection; preparedStatement) {
                List<Delivery> deliveryList = request.getDeliveryList();
                if (deliveryList != null && !deliveryList.isEmpty()) {
                    for (Delivery delivery : deliveryList) {
                        deliveryDao.addOrUpdateDelivery(delivery, false);
                    }
                }

                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateRequest() method");
                throw new DaoException("exception in updateRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateRequest() method");
            throw new DaoException("exception in updateRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateRequest() method");
            throw new DaoException("exception in updateRequest() method", e);
        }
    }

    @Override
    public void deleteRequest(String id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_REQUEST);

            preparedStatement.setString(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteRequest() method");
                throw new DaoException("exception in deleteRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteRequest() method");
            throw new DaoException("exception in deleteRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteRequest() method");
            throw new DaoException("exception in deleteRequest() method", e);
        }
    }

    @Override
    public List<RequestDto> getAllRequestsDto() throws DaoException {
        List<RequestDto> requestDtoList = new ArrayList<>();

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_REQUESTS_SQL);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    RequestDto requestDto = new RequestDto();
                    String id = resultSet.getString("id");
                    requestDto.setRequestId(id);
                    requestDto.setFillingDate(resultSet.getDate("filling_date"));

                    int automobileTypeId = resultSet.getInt("required_automobile_type_id");
                    requestDto.setRequiredAutomobileType(automobileTypeDao.findAutomobileType(automobileTypeId));

                    int loadingTypeId = resultSet.getInt("required_loading_type_id");
                    requestDto.setRequiredLoadingType(loadingTypeDao.getLoadingType(loadingTypeId));

                    int chartererId = resultSet.getInt("charterer_id");
                    requestDto.setCharterer(chartererDao.getCharterer(chartererId));

                    int statusId = resultSet.getInt("status_id");
                    requestDto.setExecutionStatus(statusDao.findStatus(statusId));

                    requestDtoList.add(requestDto);

                }

            } catch (SQLException e) {
                logger.error("Sql exception in getAllRequestsDto() method");
                throw new DaoException("exception in getAllRequestsDto() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllRequestsDto() method");
            throw new DaoException("exception in getAllRequestsDto() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllRequestsDto() method");
            throw new DaoException("exception in getAllRequestsDto() method", e);
        }
        return requestDtoList;
    }
}
