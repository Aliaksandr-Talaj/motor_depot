package by.talai.data.dao.impl;

import by.talai.data.dao.AddressDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(AddressDaoImpl.class);

    public AddressDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createAddress(Address address) throws Exception {

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO `motor_depot`.`address`" +
                            " (`id`, `country`, `region`, `locality`, `street`, `building`, `apartment`)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getRegion());
            preparedStatement.setString(4, address.getLocality());
            preparedStatement.setString(5, address.getStreet());
            preparedStatement.setString(6, address.getBuilding());
            preparedStatement.setString(7, address.getApartment());

            preparedStatement.executeUpdate();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in createAddress() method");
            throw new SQLException("exception in createAddress() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createAddress() method");
            throw new ConnectionPoolException("exception in createAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in createAddress() method");
            throw new Exception("exception in createAddress() method", e);
        }
    }

    @Override
    public Address getAddress(int id) throws Exception {

        Address address = new Address();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.address where id=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            address.setId(id);
            address.setCountry((resultSet.getString("country")));
            address.setRegion(resultSet.getString("region"));
            address.setLocality(resultSet.getString("locality"));
            address.setStreet(resultSet.getString("street"));
            address.setBuilding(resultSet.getString("building"));
            address.setApartment(resultSet.getString("apartment"));

            connectionPool.returnConnectionToPool(connection, preparedStatement, resultSet);

        } catch (SQLException e) {
            logger.error("Sql exception in getAddress() method");
            throw new SQLException("exception in getAddress() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAddress() method");
            throw new ConnectionPoolException("exception in getAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAddress() method");
            throw new Exception("exception in getAddress() method", e);
        }
        return address;
    }

    @Override
    public List<Address> getAllAddresses() throws Exception {

        List<Address> addresses = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from motor_depot.address; ");
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
            logger.error("Sql exception in getAllAddresses() method");
            throw new SQLException("exception in getAllAddresses() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllAddresses() method");
            throw new ConnectionPoolException("exception in getAllAddresses() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllAddresses() method");
            throw new Exception("exception in getAllAddresses() method", e);
        }
        return addresses;
    }

    @Override
    public void updateAddress(Address address) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `motor_depot`.`address` " +
                            "SET `country` = ?, `region` = ?, `locality` = ?, `street` = ?, `building` = ?," +
                            " `apartment` = ? " +
                            "WHERE (`id` = ?);");

            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getRegion());
            preparedStatement.setString(3, address.getLocality());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getBuilding());
            preparedStatement.setString(6, address.getApartment());

            preparedStatement.setInt(7, address.getId());

            preparedStatement.executeUpdate();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in updateAddress() method");
            throw new SQLException("exception in updateAddress() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateAddress() method");
            throw new ConnectionPoolException("exception in updateAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateAddress() method");
            throw new Exception("exception in updateAddress() method", e);
        }

    }

    @Override
    public void deleteAddress(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `motor_depot`.`address` WHERE (`id` = ?);");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            connectionPool.returnConnectionToPool(connection, preparedStatement);

        } catch (SQLException e) {
            logger.error("Sql exception in deleteAddress() method");
            throw new SQLException("exception in deleteAddress() method", e);
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteAddress() method");
            throw new ConnectionPoolException("exception in deleteAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteAddress() method");
            throw new Exception("exception in deleteAddress() method", e);
        }
    }


}
