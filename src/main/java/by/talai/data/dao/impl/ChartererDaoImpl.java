package by.talai.data.dao.impl;

import by.talai.data.dao.AddressDao;
import by.talai.data.dao.ChartererDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Address;
import by.talai.model.Charterer;
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

public class ChartererDaoImpl implements ChartererDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final AddressDao addressDao = new AddressDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(ChartererDaoImpl.class);

    public ChartererDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createCharterer(Charterer charterer) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.charterer (id, name, own_address_id)" +
                            " VALUES (?, ?, ?);");
            preparedStatement.setInt(1, charterer.getId());
            preparedStatement.setString(2, charterer.getName());
            preparedStatement.setInt(3, charterer.getOwnAddress().getId());

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in createCharterer() method");
            throw new SQLException("exception in createCharterer() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createCharterer() method");
            throw new ConnectionPoolException("exception in createCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in createCharterer() method");
            throw new Exception("exception in createCharterer() method", e);
        }
    }

    @Override
    public Charterer getCharterer(int id) throws Exception {
        Charterer charterer = new Charterer();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.charterer where id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            charterer.setId(id);
            charterer.setName(resultSet.getString("name"));

            int ownAddressId = resultSet.getInt("own_address_id");
            charterer.setOwnAddress(addressDao.getAddress(ownAddressId));

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getCharterer() method");
            throw new SQLException("exception in getCharterer() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getCharterer() method");
            throw new ConnectionPoolException("exception in getCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in getCharterer() method");
            throw new Exception("exception in getCharterer() method", e);
        }
        return charterer;
    }

    @Override
    public List<Charterer> getAllCharterers() throws Exception {
        List<Charterer> charterers = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.charterer; ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Charterer charterer = new Charterer();

                charterer.setId(resultSet.getInt("id"));
                charterer.setName(resultSet.getString("name"));

                int ownAddressId = resultSet.getInt("own_address_id");
                charterer.setOwnAddress(addressDao.getAddress(ownAddressId));

                charterers.add(charterer);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getAllCharterers() method");
            throw new SQLException("exception in getAllCharterers() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllCharterers() method");
            throw new ConnectionPoolException("exception in getAllCharterers() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllCharterers() method");
            throw new Exception("exception in getAllCharterers() method", e);
        }
        return charterers;
    }

    @Override
    public void updateCharterer(Charterer charterer) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.charterer " +
                            "SET name = ?, own_address_id = ? " +
                            "WHERE (id = ?);");

            preparedStatement.setString(1, charterer.getName());
            preparedStatement.setInt(2, charterer.getOwnAddress().getId());

            preparedStatement.setInt(3, charterer.getId());

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in updateCharterer() method");
            throw new SQLException("exception in updateCharterer() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateCharterer() method");
            throw new ConnectionPoolException("exception in updateCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateCharterer() method");
            throw new Exception("exception in updateCharterer() method", e);
        }
    }

    @Override
    public void deleteCharterer(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.charterer WHERE (`id` = ?);");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in deleteCharterer() method");
            throw new SQLException("exception in deleteCharterer() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteCharterer() method");
            throw new ConnectionPoolException("exception in deleteCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteCharterer() method");
            throw new Exception("exception in deleteCharterer() method", e);
        }
    }

    @Override
    public void addAddressToCharterer(int addressId, int chartererId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.charterer_use_address (charterer_id, address_id)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, chartererId);
            preparedStatement.setInt(2, addressId);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in addAddressToCharterer() method");
            throw new SQLException("exception in addAddressToCharterer() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addAddressToCharterer() method");
            throw new ConnectionPoolException("exception in addAddressToCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in addAddressToCharterer() method");
            throw new Exception("exception in addAddressToCharterer() method", e);
        }
    }

    @Override
    public Set<Address> findUsedAddresses(int chartererId) throws Exception {
        Set<Address> addresses = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.charterer_use_address, motor_depot.address " +
                            "WHERE (charterer_id = ?) " +
                            "and (motor_depot.address.id = motor_depot.charterer_use_address.address_id); ");
            preparedStatement.setInt(1, chartererId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Address address = new Address();

                address.setId(resultSet.getInt("id"));
                address.setCountry((resultSet.getString("country")));
                address.setRegion(resultSet.getString("region"));
                address.setLocality(resultSet.getString("locality"));
                address.setStreet(resultSet.getString("street"));
                address.setBuilding(resultSet.getString("building"));
                address.setApartment(resultSet.getString("apartment"));

                addresses.add(address);
            }

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in findUsedAddresses() method");
            throw new SQLException("exception in findUsedAddresses() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findUsedAddresses() method");
            throw new ConnectionPoolException("exception in findUsedAddresses() method", e);
        } catch (Exception e) {
            logger.error("Exception in findUsedAddresses() method");
            throw new Exception("exception in findUsedAddresses() method", e);
        }
        return addresses;
    }

    @Override
    public void deleteUsageOfAddress(int addressId, int chartererId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.charterer_use_address " +
                            "WHERE (charterer_id = ?, address_id = ?);");

            preparedStatement.setInt(1, chartererId);
            preparedStatement.setInt(2,addressId);

            preparedStatement.executeUpdate();
            connection.commit();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in deleteUsageOfAddress() method");
            throw new SQLException("exception in deleteUsageOfAddress() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteUsageOfAddress() method");
            throw new ConnectionPoolException("exception in deleteUsageOfAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteUsageOfAddress() method");
            throw new Exception("exception in deleteUsageOfAddress() method", e);
        }
    }
}
