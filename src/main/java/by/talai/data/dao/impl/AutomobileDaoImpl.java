package by.talai.data.dao.impl;

import by.talai.data.dao.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Equipment;
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
import java.util.List;
import java.util.Set;


public class AutomobileDaoImpl implements AutomobileDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
    EquipmentDao equipmentDao = new EquipmentDaoImpl();
    MalfunctionDao malfunctionDao = new MalfunctionDaoImpl();
    TechnicalStatusDao technicalStatusDao = new TechnicalStatusDaoImpl();
    FuelTypeDao fuelTypeDao = new FuelTypeDaoImpl();
    AutomobileTypeDao automobileTypeDao = new AutomobileTypeDaoImpl();


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
            preparedStatement.setString(1, automobile.getId());
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

            preparedStatement.executeUpdate();

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

            Set<Equipment> equipmentSet = equipmentDao.getAllEquipmentOnAutomobile(id);
            automobile.setEquipmentSet(equipmentSet);

            List<Maintenance> maintenanceList = maintenanceDao.getMaintenanceOfAutomobile(id);
            automobile.setMaintenanceList(maintenanceList);

            List<Malfunction> malfunctions = malfunctionDao.getMalfunctionsOfAutomobile(id);
            automobile.setMalfunctions(malfunctions);

            int technicalStatusId = resultSet.getInt("technical_status");
            automobile.setTechnicalStatus(technicalStatusDao.findTechnicalStatus(technicalStatusId));

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

                Set<Equipment> equipmentSet = equipmentDao.getAllEquipmentOnAutomobile(id);
                automobile.setEquipmentSet(equipmentSet);

                List<Maintenance> maintenanceList = maintenanceDao.getMaintenanceOfAutomobile(id);
                automobile.setMaintenanceList(maintenanceList);

                List<Malfunction> malfunctions = malfunctionDao.getMalfunctionsOfAutomobile(id);
                automobile.setMalfunctions(malfunctions);

                int technicalStatusId = resultSet.getInt("technical_status");
                automobile.setTechnicalStatus(technicalStatusDao.findTechnicalStatus(technicalStatusId));

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

            preparedStatement.setString(11, automobile.getId());

            preparedStatement.executeUpdate();

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
}
