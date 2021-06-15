package by.talai.data.dao.impl;

import by.talai.data.dao.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.AutomobileAttachment;
import by.talai.model.Equipment;
import by.talai.model.LoadingType;
import by.talai.model.stock.Automobile;
import by.talai.model.stock.Maintenance;
import by.talai.model.stock.Malfunction;
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


public class AutomobileDaoImpl implements AutomobileDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    private final MalfunctionDao malfunctionDao = new MalfunctionDaoImpl();
    private final MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
    private final StatusDao technicalStatusDao = new TechnicalStatusDaoImpl();
    private final FuelTypeDao fuelTypeDao = new FuelTypeDaoImpl();
    private final AutomobileTypeDao automobileTypeDao = new AutomobileTypeDaoImpl();


    public static final Logger logger = LoggerFactory.getLogger(AutomobileDaoImpl.class);

    public AutomobileDaoImpl() throws ConnectionPoolException {
    }


    @Override
    public void createAutomobile(Automobile automobile) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO `motor_depot`.`automobile` (`id`, `brand`, `model`, `type_id`," +
                            " `fuel_type_id`, `carrying`, `platform_length`, `platform_width`, `cargo_height_limit`," +
                            " `cargo_volume_limit`, `technical_status_id`) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            String automobileId = automobile.getId();
            preparedStatement.setString(1, automobileId);
            preparedStatement.setString(2, automobile.getBrand());
            preparedStatement.setString(3, automobile.getModel());

            preparedStatement.setInt(4, automobile.getType().getId());

            preparedStatement.setInt(5, automobile.getFuelType().getId());

            preparedStatement.setInt(6, automobile.getCarrying());
            preparedStatement.setInt(7, automobile.getPlatformLength());
            preparedStatement.setInt(8, automobile.getPlatformWidth());
            preparedStatement.setDouble(9, automobile.getCargoHeightLimit());
            preparedStatement.setDouble(10, automobile.getCargoVolumeLimit());

            preparedStatement.setInt(11, automobile.getTechnicalStatus().getId());


            Set<Equipment> equipmentSet = automobile.getEquipmentSet();
            if (equipmentSet != null && !equipmentSet.isEmpty()) {
                for (Equipment equipment : equipmentSet) {
                    int equipmentId = equipment.getId();
                    addEquipmentToAutomobile(equipmentId, automobileId);
                }
            }
            Set<LoadingType> loadingTypes = automobile.getLoadingTypes();
            if (loadingTypes != null && !loadingTypes.isEmpty()) {
                for (LoadingType loadingType : loadingTypes) {
                    int loadingTypeId = loadingType.getId();
                    addLoadingTypeToAutomobile(loadingTypeId, automobileId);
                }
            }

            List<Malfunction> malfunctionList = automobile.getMalfunctions();
            if (malfunctionList != null && !malfunctionList.isEmpty()) {
                for (Malfunction malfunction : malfunctionList) {
                    malfunctionDao.createMalfunction(malfunction);
                }
            }

            List<Maintenance> maintenanceList = automobile.getMaintenanceList();
            if (maintenanceList != null && !maintenanceList.isEmpty()) {
                for (Maintenance maintenance : maintenanceList) {
                    maintenanceDao.createMaintenance(maintenance);
                }
            }

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in createAutomobile() method");
            throw new SQLException("exception in createAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createAutomobile() method");
            throw new ConnectionPoolException("exception in createAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in createAutomobile() method");
            throw new Exception("exception in createAutomobile() method", e);
        }
    }

    @Override
    public Automobile getAutomobile(String id) throws Exception {
        Automobile automobile = new Automobile();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.automobile where id=?;");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            automobile.setId(id);
            automobile.setBrand(resultSet.getString("brand"));
            automobile.setModel(resultSet.getString("model"));

            int fuelTypeId = resultSet.getInt("fuel");
            automobile.setFuelType(fuelTypeDao.findFuelType(fuelTypeId));

            automobile.setCarrying(resultSet.getInt("carrying"));

            int typeId = resultSet.getInt("type");
            automobile.setType(automobileTypeDao.findAutomobileType(typeId));

            automobile.setPlatformLength(resultSet.getInt("platform_length"));
            automobile.setPlatformWidth(resultSet.getInt("platform_width"));
            automobile.setCargoHeightLimit(resultSet.getDouble("cargo_height_limit"));
            automobile.setCargoVolumeLimit(resultSet.getDouble("cargo_volume_limit"));

            Set<Equipment> equipmentSet = getAllEquipmentOnAutomobile(id);
            automobile.setEquipmentSet(equipmentSet);

            List<Maintenance> maintenanceList = getMaintenanceOfAutomobile(id);
            automobile.setMaintenanceList(maintenanceList);

            List<Malfunction> malfunctions = getMalfunctionsOfAutomobile(id);
            automobile.setMalfunctions(malfunctions);

            int technicalStatusId = resultSet.getInt("technical_status");
            automobile.setTechnicalStatus(technicalStatusDao.findStatus(technicalStatusId));

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getAutomobile() method");
            throw new SQLException("exception in getAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAutomobile() method");
            throw new ConnectionPoolException("exception in getAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAutomobile() method");
            throw new Exception("exception in getAutomobile() method", e);
        }
        return automobile;
    }

    @Override
    public List<Automobile> getAllAutomobiles() throws Exception {
        List<Automobile> automobiles = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.automobile; ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Automobile automobile = new Automobile();
                String id = resultSet.getString("id");
                automobile.setId(id);
                automobile.setBrand(resultSet.getString("brand"));
                automobile.setModel(resultSet.getString("model"));

                int fuelTypeId = resultSet.getInt("fuel");
                automobile.setFuelType(fuelTypeDao.findFuelType(fuelTypeId));

                automobile.setCarrying(resultSet.getInt("carrying"));

                int typeId = resultSet.getInt("type");
                automobile.setType(automobileTypeDao.findAutomobileType(typeId));

                automobile.setPlatformLength(resultSet.getInt("platform_length"));
                automobile.setPlatformWidth(resultSet.getInt("platform_width"));
                automobile.setCargoHeightLimit(resultSet.getDouble("cargo_height_limit"));
                automobile.setCargoVolumeLimit(resultSet.getDouble("cargo_volume_limit"));

                Set<Equipment> equipmentSet = getAllEquipmentOnAutomobile(id);
                automobile.setEquipmentSet(equipmentSet);

                List<Maintenance> maintenanceList = getMaintenanceOfAutomobile(id);
                automobile.setMaintenanceList(maintenanceList);

                List<Malfunction> malfunctions = getMalfunctionsOfAutomobile(id);
                automobile.setMalfunctions(malfunctions);

                int technicalStatusId = resultSet.getInt("technical_status");
                automobile.setTechnicalStatus(technicalStatusDao.findStatus(technicalStatusId));

                automobiles.add(automobile);

            }
            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getAllAutomobiles() method");
            throw new SQLException("exception in getAllAutomobiles() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllAutomobiles() method");
            throw new ConnectionPoolException("exception in getAllAutomobiles() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllAutomobiles() method");
            throw new Exception("exception in getAllAutomobiles() method", e);
        }
        return automobiles;
    }

    @Override
    public void updateAutomobile(Automobile automobile) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `motor_depot`.`automobile` " +
                            "SET `brand` = ?, `model` = ?, `type_id` = ?, `fuel_type_id` = ?," +
                            " `carrying` = ?, `platform_length` = ?, `platform_width` = ?," +
                            " `cargo_height_limit` = ?, `cargo_volume_limit` = ?, `technical_status_id` = ?" +
                            " WHERE (`id` = ?);");

            preparedStatement.setString(1, automobile.getBrand());
            preparedStatement.setString(2, automobile.getModel());
            preparedStatement.setInt(3, automobile.getType().getId());
            preparedStatement.setInt(4, automobile.getFuelType().getId());
            preparedStatement.setInt(5, automobile.getCarrying());
            preparedStatement.setInt(6, automobile.getPlatformLength());
            preparedStatement.setInt(7, automobile.getPlatformWidth());
            preparedStatement.setDouble(8, automobile.getCargoHeightLimit());
            preparedStatement.setDouble(9, automobile.getCargoVolumeLimit());
            preparedStatement.setInt(10, automobile.getTechnicalStatus().getId());

            String automobileId = automobile.getId();
            preparedStatement.setString(11, automobileId);

            Set<Equipment> equipmentSet = automobile.getEquipmentSet();
            if (equipmentSet != null && !equipmentSet.isEmpty()) {
                for (Equipment equipment : equipmentSet) {
                    int equipmentId = equipment.getId();
                    addEquipmentToAutomobile(equipmentId, automobileId);
                }
            }
            Set<LoadingType> loadingTypes = automobile.getLoadingTypes();
            if (loadingTypes != null && !loadingTypes.isEmpty()) {
                for (LoadingType loadingType : loadingTypes) {
                    int loadingTypeId = loadingType.getId();
                    addLoadingTypeToAutomobile(loadingTypeId, automobileId);
                }
            }

            List<Malfunction> malfunctionList = automobile.getMalfunctions();
            if (malfunctionList != null && !malfunctionList.isEmpty()) {
                for (Malfunction malfunction : malfunctionList) {
                    malfunctionDao.createMalfunction(malfunction);
                }
            }

            List<Maintenance> maintenanceList = automobile.getMaintenanceList();
            if (maintenanceList != null && !maintenanceList.isEmpty()) {
                for (Maintenance maintenance : maintenanceList) {
                    maintenanceDao.createMaintenance(maintenance);
                }
            }

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in updateAutomobile() method");
            throw new SQLException("exception in updateAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateAutomobile() method");
            throw new ConnectionPoolException("exception in updateAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateAutomobile() method");
            throw new Exception("exception in updateAutomobile() method", e);
        }
    }


    @Override
    public void deleteAutomobile(String id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `motor_depot`.`automobile` WHERE (`id` = ?);");

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in deleteAutomobile() method");
            throw new SQLException("exception in deleteAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteAutomobile() method");
            throw new ConnectionPoolException("exception in deleteAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteAutomobile() method");
            throw new Exception("exception in deleteAutomobile() method", e);
        }
    }


    //Interactions with EQUIPMENT
    @Override
    public void addEquipmentToAutomobile(int equipmentId, String automobileId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "`motor_depot`.`automobile_has_equipment` (equipment_id, automobile_id)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.setString(2, automobileId);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in addEquipmentToAutomobile() method");
            throw new SQLException("exception in addEquipmentToAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addEquipmentToAutomobile() method");
            throw new ConnectionPoolException("exception in addEquipmentToAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in addEquipmentToAutomobile() method");
            throw new Exception("exception in addEquipmentToAutomobile() method", e);
        }
    }

    @Override
    public Set<Equipment> getAllEquipmentOnAutomobile(String automobileId) throws Exception {

        Set<Equipment> equipmentSet = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.automobile_has_equipment, " +
                            "motor_depot.equipment " +
                            "where (automobile_has_equipment.automobile_id = ?) " +
                            "and (automobile_has_equipment.equipment_id = equipment.id);");
            preparedStatement.setString(1, automobileId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Equipment equipment = new Equipment();

                equipment.setId(resultSet.getInt("id"));
                equipment.setName(resultSet.getString("name"));
                equipment.setDescription(resultSet.getString("description"));

                equipmentSet.add(equipment);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getAllEquipmentOnAutomobile() method");
            throw new SQLException("exception in getAllEquipmentOnAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllEquipmentOnAutomobile() method");
            throw new ConnectionPoolException("exception in getAllEquipmentOnAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllEquipmentOnAutomobile() method");
            throw new Exception("exception in getAllEquipmentOnAutomobile() method", e);
        }
        return equipmentSet;

    }

    @Override
    public void removeEquipmentFromAutomobile(int equipmentId, String automobileId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.automobile_has_equipment " +
                            "WHERE (equipment_id = ?) and (automobile_id = ?)");
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.setString(2, automobileId);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in removeEquipmentFromAutomobile() method");
            throw new SQLException("exception in removeEquipmentFromAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in removeEquipmentFromAutomobile() method");
            throw new ConnectionPoolException("exception in removeEquipmentFromAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in removeEquipmentFromAutomobile() method");
            throw new Exception("exception in removeEquipmentFromAutomobile() method", e);
        }
    }

    //Interactions with LOADING TYPES
    @Override
    public void addLoadingTypeToAutomobile(int loadingTypeId, String automobileId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "`motor_depot`.`automobile_has_loading_type` (loading_type_id, automobile_id)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, loadingTypeId);
            preparedStatement.setString(2, automobileId);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in addLoadingTypeToAutomobile() method");
            throw new SQLException("exception in addLoadingTypeToAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addLoadingTypeToAutomobile() method");
            throw new ConnectionPoolException("exception in addLoadingTypeToAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in addLoadingTypeToAutomobile() method");
            throw new Exception("exception in addLoadingTypeToAutomobile() method", e);
        }
    }

    @Override
    public Set<LoadingType> getAllLoadingTypesOfAutomobile(String automobileId) throws Exception {
        Set<LoadingType> loadingTypeSet = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.automobile_has_loading_type, " +
                            "motor_depot.loading_type " +
                            "where (automobile_has_loading_type.automobile_id = ?) " +
                            "and (automobile_has_loading_type.loading_type_id = loading_type.id);");
            preparedStatement.setString(1, automobileId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LoadingType loadingType = new LoadingType();

                loadingType.setId(resultSet.getInt("id"));
                loadingType.setType(resultSet.getString("loading_type"));

                loadingTypeSet.add(loadingType);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getAllLoadingTypesOfAutomobile() method");
            throw new SQLException("exception in getAllLoadingTypesOfAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllLoadingTypesOfAutomobile() method");
            throw new ConnectionPoolException("exception in getAllLoadingTypesOfAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllLoadingTypesOfAutomobile() method");
            throw new Exception("exception in getAllLoadingTypesOfAutomobile() method", e);
        }
        return loadingTypeSet;
    }

    @Override
    public void removeLoadingTypeFromAutomobile(int loadingTypeId, String automobileId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.automobile_has_loading_type " +
                            "WHERE (loading_type_id = ?) and (automobile_id = ?)");
            preparedStatement.setInt(1, loadingTypeId);
            preparedStatement.setString(2, automobileId);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in removeLoadingTypeFromAutomobile() method");
            throw new SQLException("exception in removeLoadingTypeFromAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in removeLoadingTypeFromAutomobile() method");
            throw new ConnectionPoolException("exception in removeLoadingTypeFromAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in removeLoadingTypeFromAutomobile() method");
            throw new Exception("exception in removeLoadingTypeFromAutomobile() method", e);
        }
    }


    //Interaction with MALFUNCTIONS

    @Override
    public List<Malfunction> getMalfunctionsOfAutomobile(String automobileId) throws Exception {
        List<Malfunction> malfunctions = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.malfunction " +
                            "WHERE (automobile_id = ?); ");
            preparedStatement.setString(1, automobileId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Malfunction malfunction = new Malfunction();

                malfunction.setId(resultSet.getInt("id"));
                malfunction.setProblem(resultSet.getString("problem"));
                malfunction.setDetectionTime(resultSet.getDate("detection_time"));
                malfunction.setFixTime(resultSet.getDate("fix_time"));
                malfunction.setAutomobileId(automobileId);

                malfunctions.add(malfunction);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getMalfunctionsOfAutomobile() method");
            throw new SQLException("exception in getMalfunctionsOfAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getMalfunctionsOfAutomobile() method");
            throw new ConnectionPoolException("exception in getMalfunctionsOfAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in getMalfunctionsOfAutomobile() method");
            throw new Exception("exception in getMalfunctionsOfAutomobile() method", e);
        }
        return malfunctions;
    }


    //Interaction with MAINTENANCE
    @Override
    public List<Maintenance> getMaintenanceOfAutomobile(String automobileId) throws Exception {
        List<Maintenance> maintenanceList = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.maintenance " +
                            "WHERE (automobile_id = ?); ");
            preparedStatement.setString(1, automobileId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Maintenance maintenance = new Maintenance();

                maintenance.setId(resultSet.getInt("id"));
                maintenance.setType(resultSet.getString("type"));
                maintenance.setStartTime(resultSet.getDate("start_time"));
                maintenance.setFinishTime(resultSet.getDate("finish_time"));
                maintenance.setAutomobileId(automobileId);

                maintenanceList.add(maintenance);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getMaintenanceOfAutomobile() method");
            throw new SQLException("exception in getMaintenanceOfAutomobile() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getMaintenanceOfAutomobile() method");
            throw new ConnectionPoolException("exception in getMaintenanceOfAutomobile() method", e);
        } catch (Exception e) {
            logger.error("Exception in getMaintenanceOfAutomobile() method");
            throw new Exception("exception in getMaintenanceOfAutomobile() method", e);
        }
        return maintenanceList;
    }


    //Interaction with AUTOMOBILE ATTACHMENTS
    @Override
    public List<AutomobileAttachment> findAutomobileAttachments(String automobileId) throws Exception {
        List<AutomobileAttachment> automobileAttachments = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.automobile_attachment " +
                            "where (automobile_id = ?); ");
            preparedStatement.setString(1, automobileId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AutomobileAttachment automobileAttachment = new AutomobileAttachment();

                automobileAttachment.setId(resultSet.getInt("id"));
                automobileAttachment.setAutomobileId(automobileId);
                automobileAttachment.setDriverId(resultSet.getInt("driver_id"));
                automobileAttachment.setDateOfAttachment(resultSet.getDate("date_of_attachment"));
                automobileAttachment.setDateOfDetachment(resultSet.getDate("date_of_detachment"));

                automobileAttachments.add(automobileAttachment);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in findAutomobileAttachments() method");
            throw new SQLException("exception in findAutomobileAttachments() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAutomobileAttachments() method");
            throw new ConnectionPoolException("exception in findAutomobileAttachments() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAutomobileAttachments() method");
            throw new Exception("exception in findAutomobileAttachments() method", e);
        }
        return automobileAttachments;
    }


}
