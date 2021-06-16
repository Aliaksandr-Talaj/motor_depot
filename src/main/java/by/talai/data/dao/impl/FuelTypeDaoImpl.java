package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.FuelTypeDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.FuelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FuelTypeDaoImpl implements FuelTypeDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(FuelTypeDaoImpl.class);

    public FuelTypeDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createFuelType(FuelType fuelType) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.fuel (id, type)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, fuelType.getId());
            preparedStatement.setString(2, fuelType.getType());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in createFuelType() method");
                throw new SQLException("exception in createFuelType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createFuelType() method");
            throw new ConnectionPoolException("exception in createFuelType() method", e);
        } catch (Exception e) {
            logger.error("Exception in createFuelType() method");
            throw new Exception("exception in createFuelType() method", e);
        }
    }

    @Override
    public FuelType findFuelType(int id) throws Exception {
        FuelType fuelType = new FuelType();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.fuel WHERE id = ?;");
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                fuelType.setId(id);
                fuelType.setType(resultSet.getString("type"));

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in findFuelType() method");
                throw new SQLException("exception in findFuelType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findFuelType() method");
            throw new ConnectionPoolException("exception in findFuelType() method", e);
        } catch (Exception e) {
            logger.error("Exception in findFuelType() method");
            throw new Exception("exception in findFuelType() method", e);
        }
        return fuelType;
    }

    @Override
    public Set<FuelType> findAllFuelTypes() throws Exception {
        Set<FuelType> fuelTypes = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.fuel; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    FuelType fuelType = new FuelType();

                    fuelType.setId(resultSet.getInt("id"));
                    fuelType.setType(resultSet.getString("type"));

                    fuelTypes.add(fuelType);
                }

                connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

            } catch (SQLException e) {
                logger.error("Sql exception in findAllFuelTypes() method");
                throw new SQLException("exception in findAllFuelTypes() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findAllFuelTypes() method");
            throw new ConnectionPoolException("exception in findAllFuelTypes() method", e);
        } catch (Exception e) {
            logger.error("Exception in findAllFuelTypes() method");
            throw new Exception("exception in findAllFuelTypes() method", e);
        }
        return fuelTypes;
    }

    @Override
    public void updateFuelType(FuelType fuelType) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.fuel " +
                            "SET type = ? WHERE (id = ?);");


            preparedStatement.setString(1, fuelType.getType());
            preparedStatement.setInt(2, fuelType.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in updateFuelType() method");
                throw new SQLException("exception in updateFuelType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateFuelType() method");
            throw new ConnectionPoolException("exception in updateFuelType() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateFuelType() method");
            throw new Exception("exception in updateFuelType() method", e);
        }
    }

    @Override
    public void deleteFuelType(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.fuel WHERE (id = ?);");

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in deleteFuelType() method");
                throw new SQLException("exception in deleteFuelType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteFuelType() method");
            throw new ConnectionPoolException("exception in deleteFuelType() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteFuelType() method");
            throw new Exception("exception in deleteFuelType() method", e);
        }
    }
}
