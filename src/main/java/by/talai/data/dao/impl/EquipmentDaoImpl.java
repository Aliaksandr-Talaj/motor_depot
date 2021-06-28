package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.EquipmentDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.stock.Equipment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EquipmentDaoImpl implements EquipmentDao {

    static final String CREATE_EQUIPMENT_SQL =
            "INSERT INTO motor_depot.equipment (id, name, description) VALUES (?, ?, ?);";
    static final String GET_EQUIPMENT_SQL = "SELECT * FROM motor_depot.equipment WHERE id = ?;";
    static final String GET_ALL_EQUIPMENT_SQL = "SELECT * FROM motor_depot.charterer; ";
    static final String UPDATE_EQUIPMENT_SQL =
            "UPDATE motor_depot.equipment SET name = ?, description = ? WHERE (id = ?);";
    static final String DELETE_EQUIPMENT_SQL = "DELETE FROM motor_depot.equipment WHERE (id = ?);";
    static final String ADD_EQUIPMENT_TO_REQUEST_SQL =
            "INSERT INTO motor_depot.request_has_required_equipment (equipment_id, request_id) VALUES (?, ?);";
    static final String GET_ALL_EQUIPMENT_OF_REQUEST_SQL =
            "SELECT * FROM motor_depot.equipment AS EQ, motor_depot.request_has_required_equipment AS REQ" +
                    " WHERE (REQ.request_id = ?) AND (REQ.equipment_id = EQ.id);";
    static final String REMOVE_EQUIPMENT_FROM_REQUEST_SQL =
            "DELETE FROM motor_depot.request_has_required_equipment WHERE (equipment_id = ?) AND (request_id = ?)";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    public static final Logger logger = LoggerFactory.getLogger(EquipmentDaoImpl.class);

    public EquipmentDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createEquipment(Equipment equipment) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_EQUIPMENT_SQL);
            preparedStatement.setInt(1, equipment.getId());
            preparedStatement.setString(2, equipment.getName());
            preparedStatement.setString(3, equipment.getDescription());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in createEquipment() method");
                throw new DaoException("exception in createEquipment() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createEquipment() method");
            throw new DaoException("exception in createEquipment() method", e);
        } catch (Exception e) {
            logger.error("Exception in createEquipment() method");
            throw new DaoException("exception in createEquipment() method", e);
        }
    }

    @Override
    public Equipment getEquipment(int id) throws Exception {
        Equipment equipment = new Equipment();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_EQUIPMENT_SQL);
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                equipment.setId(id);

                if (resultSet.next()) {
                    equipment.setName(resultSet.getString("name"));
                    equipment.setDescription(resultSet.getString("description"));
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getEquipment() method");
                throw new DaoException("exception in getEquipment() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getEquipment() method");
            throw new DaoException("exception in getEquipment() method", e);
        } catch (Exception e) {
            logger.error("Exception in getEquipment() method");
            throw new DaoException("exception in getEquipment() method", e);
        }
        return equipment;
    }


    @Override
    public List<Equipment> getAllEquipment() throws Exception {
        List<Equipment> equipmentList = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_EQUIPMENT_SQL);
            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Equipment equipment = new Equipment();

                    equipment.setId(resultSet.getInt("id"));
                    equipment.setName(resultSet.getString("name"));
                    equipment.setDescription(resultSet.getString("description"));

                    equipmentList.add(equipment);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllEquipment() method");
                throw new DaoException("exception in getAllEquipment() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllEquipment() method");
            throw new DaoException("exception in getAllEquipment() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllEquipment() method");
            throw new DaoException("exception in getAllEquipment() method", e);
        }
        return equipmentList;
    }

    @Override
    public void updateEquipment(Equipment equipment) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_EQUIPMENT_SQL);

            preparedStatement.setString(1, equipment.getName());
            preparedStatement.setString(2, equipment.getDescription());

            preparedStatement.setInt(3, equipment.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateEquipment() method");
                throw new DaoException("exception in updateEquipment() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateEquipment() method");
            throw new DaoException("exception in updateEquipment() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateEquipment() method");
            throw new DaoException("exception in updateEquipment() method", e);
        }
    }

    @Override
    public void deleteEquipment(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_EQUIPMENT_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteEquipment() method");
                throw new DaoException("exception in deleteEquipment() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteEquipment() method");
            throw new DaoException("exception in deleteEquipment() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteEquipment() method");
            throw new DaoException("exception in deleteEquipment() method", e);
        }
    }


    @Override
    public void addEquipmentToRequest(int equipmentId, String requestId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(ADD_EQUIPMENT_TO_REQUEST_SQL);
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.setString(2, requestId);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in addEquipmentToRequest() method");
                throw new DaoException("exception in addEquipmentToRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addEquipmentToRequest() method");
            throw new DaoException("exception in addEquipmentToRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in addEquipmentToRequest() method");
            throw new DaoException("exception in addEquipmentToRequest() method", e);
        }
    }

    @Override
    public Set<Equipment> getAllEquipmentOfRequest(String requestId) throws Exception {
        Set<Equipment> equipmentList = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_EQUIPMENT_OF_REQUEST_SQL);
            preparedStatement.setString(1, requestId);
            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Equipment equipment = new Equipment();

                    equipment.setId(resultSet.getInt("id"));
                    equipment.setName(resultSet.getString("name"));
                    equipment.setDescription(resultSet.getString("description"));

                    equipmentList.add(equipment);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllEquipmentOfRequest() method");
                throw new DaoException("exception in getAllEquipmentOfRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllEquipmentOfRequest() method");
            throw new DaoException("exception in getAllEquipmenOfRequestt() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllEquipmentOfRequest() method");
            throw new DaoException("exception in getAllEquipmentOfRequest() method", e);
        }
        return equipmentList;
    }

    @Override
    public void removeEquipmentFromRequest(int equipmentId, String requestId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(REMOVE_EQUIPMENT_FROM_REQUEST_SQL);
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.setString(2, requestId);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in removeEquipmentFromRequest() method");
                throw new DaoException("exception in removeEquipmentFromRequest() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in removeEquipmentFromRequest() method");
            throw new DaoException("exception in removeEquipmentFromRequest() method", e);
        } catch (Exception e) {
            logger.error("Exception in removeEquipmentFromRequest() method");
            throw new DaoException("exception in removeEquipmentFromRequest() method", e);
        }
    }
}
