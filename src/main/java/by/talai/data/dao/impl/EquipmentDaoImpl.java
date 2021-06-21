package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.EquipmentDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Equipment;
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

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    public static final Logger logger = LoggerFactory.getLogger(EquipmentDaoImpl.class);

    public EquipmentDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createEquipment(Equipment equipment) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.equipment (id, name, description)" +
                            " VALUES (?, ?, ?);");
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
                    .prepareStatement("SELECT * FROM motor_depot.equipment WHERE id = ?;");
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
                    .prepareStatement("SELECT * SELECT motor_depot.charterer; ");
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
                    .prepareStatement("UPDATE motor_depot.equipment " +
                            "SET name = ?, description = ? " +
                            "WHERE (id = ?);");

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
                    .prepareStatement("DELETE FROM motor_depot.equipment WHERE (id = ?);");

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
    public void addEquipmentToRequest(int equipmentId, int requestId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.request_has_required_equipment (equipment_id, request_id)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.setInt(2, requestId);

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
    public Set<Equipment> getAllEquipmentOfRequest(int requestId) throws Exception {
        Set<Equipment> equipmentList = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.equipment AS EQ," +
                            " motor_depot.request_has_required_equipment AS REQ" +
                            " where (REQ.request_id = ?)" +
                            " and (REQ.equipment_id = EQ.id);");
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
    public void removeEquipmentFromRequest(int equipmentId, int requestId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.request_has_required_equipment " +
                            "WHERE (equipment_id = ?) and (request_id = ?)");
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.setInt(2, requestId);

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
