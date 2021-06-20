package by.talai.data.dao.impl;

import by.talai.data.dao.AddressDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
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

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    static final String CREATE_ADDRESS_SQL = "INSERT INTO motor_depot.address" +
            " (id, country, region, locality, street, building, apartment) VALUES (?, ?, ?, ?, ?, ?, ?);";
    static final String CREATE_ADDRESS_SQL_NO_ID = "INSERT INTO motor_depot.address" +
            " (country, region, locality, street, building, apartment) VALUES (?, ?, ?, ?, ?, ?);";
    static final String GET_ADDRESS_SQL = "SELECT * FROM motor_depot.address WHERE id = ?;";
    static final String GET_ALL_ADDRESSES = "SELECT * FROM motor_depot.address; ";
    static final String UPDATE_ADDRESS = "UPDATE motor_depot.address " +
            "SET country = ?, region = ?, locality = ?, street = ?, building = ?, apartment = ? WHERE (id = ?);";
    static final String DELETE_ADDRESS = "DELETE FROM motor_depot.address WHERE (id = ?);";
    static final String GET_LAST_INSERT_ID = "SELECT last_insert_id();";

    static final Logger logger = LoggerFactory.getLogger(AddressDaoImpl.class);

    public AddressDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public int createAddress(Address address) throws Exception {
        int id = address.getId();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement;
            if (id == 0) {
                preparedStatement = connection.prepareStatement(CREATE_ADDRESS_SQL_NO_ID);

                preparedStatement.setString(1, address.getCountry());
                preparedStatement.setString(2, address.getRegion());
                preparedStatement.setString(3, address.getLocality());
                preparedStatement.setString(4, address.getStreet());
                preparedStatement.setString(5, address.getBuilding());
                preparedStatement.setString(6, address.getApartment());
            } else {
                preparedStatement = connection.prepareStatement(CREATE_ADDRESS_SQL);

                preparedStatement.setString(2, address.getCountry());
                preparedStatement.setString(3, address.getRegion());
                preparedStatement.setString(4, address.getLocality());
                preparedStatement.setString(5, address.getStreet());
                preparedStatement.setString(6, address.getBuilding());
                preparedStatement.setString(7, address.getApartment());
            }

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                if (id == 0) {
                    try (PreparedStatement preparedStatementForId = connection.prepareStatement(GET_LAST_INSERT_ID);
                         ResultSet resultSet = preparedStatementForId.executeQuery()) {
                        resultSet.next();
                        id = resultSet.getInt(1);
                    } catch (SQLException e) {
                        logger.error("Sql exception in createAddress() method");
                        throw new DaoException("exception in createAddress() method", e);
                    }
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createAddress() method");
                throw new DaoException("exception in createAddress() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createAddress() method");
            throw new DaoException("exception in createAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in createAddress() method");
            throw new DaoException("exception in createAddress() method", e);
        }
        return id;
    }

    @Override
    public Address getAddress(int id) throws Exception {

        Address address = new Address();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ADDRESS_SQL);
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                address.setId(id);
                resultSet.next();
                address.setCountry((resultSet.getString("country")));
                address.setRegion(resultSet.getString("region"));
                address.setLocality(resultSet.getString("locality"));
                address.setStreet(resultSet.getString("street"));
                address.setBuilding(resultSet.getString("building"));
                address.setApartment(resultSet.getString("apartment"));


            } catch (SQLException e) {
                logger.error("Sql exception in getAddress() method");
                throw new DaoException("exception in getAddress() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAddress() method");
            throw new DaoException("exception in getAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAddress() method");
            throw new DaoException("exception in getAddress() method", e);
        }
        return address;
    }

    @Override
    public List<Address> getAllAddresses() throws Exception {

        List<Address> addresses = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ADDRESSES);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

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


            } catch (SQLException e) {
                logger.error("Sql exception in getAllAddresses() method");
                throw new DaoException("exception in getAllAddresses() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllAddresses() method");
            throw new DaoException("exception in getAllAddresses() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllAddresses() method");
            throw new DaoException("exception in getAllAddresses() method", e);
        }
        return addresses;
    }

    @Override
    public void updateAddress(Address address) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);

            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getRegion());
            preparedStatement.setString(3, address.getLocality());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getBuilding());
            preparedStatement.setString(6, address.getApartment());

            preparedStatement.setInt(7, address.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateAddress() method");
                throw new DaoException("exception in updateAddress() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateAddress() method");
            throw new DaoException("exception in updateAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateAddress() method");
            throw new DaoException("exception in updateAddress() method", e);
        }

    }

    @Override
    public void deleteAddress(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteAddress() method");
                throw new DaoException("exception in deleteAddress() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteAddress() method");
            throw new DaoException("exception in deleteAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteAddress() method");
            throw new DaoException("exception in deleteAddress() method", e);
        }
    }

    @Override
    public int createAddress(String country, String region, String locality, String street,
                             String building, String apartment) throws DaoException {
        int id;
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(CREATE_ADDRESS_SQL_NO_ID);

            preparedStatement.setString(1, country);
            preparedStatement.setString(2, region);
            preparedStatement.setString(3, locality);
            preparedStatement.setString(4, street);
            preparedStatement.setString(5, building);
            preparedStatement.setString(6, apartment);


            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                try (PreparedStatement preparedStatementForId = connection.prepareStatement(GET_LAST_INSERT_ID);
                     ResultSet resultSet = preparedStatementForId.executeQuery()) {
                    resultSet.next();
                    id = resultSet.getInt(1);
                } catch (SQLException e) {
                    logger.error("Sql exception in createAddress() method");
                    throw new DaoException("exception in createAddress() method", e);
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createAddress() method");
                throw new DaoException("exception in createAddress() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createAddress() method");
            throw new DaoException("exception in createAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in createAddress() method");
            throw new DaoException("exception in createAddress() method", e);
        }
        return id;
    }


}
