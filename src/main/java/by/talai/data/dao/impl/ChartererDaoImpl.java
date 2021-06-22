package by.talai.data.dao.impl;

import by.talai.data.dao.AddressDao;
import by.talai.data.dao.ChartererDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
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

    static final String CREATE_CHARTERER_DAO_SQL =
            "INSERT INTO motor_depot.charterer (id, name, surname, own_address_id) VALUES (?, ?, ?, ?);";
    static final String CREATE_CHARTERER_DAO_SQL_NO_ID =
            "INSERT INTO motor_depot.charterer (name, surname, own_address_id) VALUES (?, ?, ?);";
    static final String GET_CHARTERER_SQL = "SELECT * FROM motor_depot.charterer WHERE id = ?;";
    static final String GET_ALL_CHARTERERS_SQL = "SELECT * FROM motor_depot.charterer;";
    static final String UPDATE_CHARTERER_SQL =
            "UPDATE motor_depot.charterer SET name = ?, surname = ?, own_address_id = ? WHERE (id = ?);";
    static final String DELETE_CHARTERER_SQL = "DELETE FROM motor_depot.charterer WHERE (id = ?);";
    static final String ADD_ADDRESS_TO_CHARTERER =
            "INSERT INTO motor_depot.charterer_use_address (charterer_id, address_id) VALUES (?, ?);";
    static final String FIND_ALL_USERS_SQL =
            "SELECT * FROM motor_depot.charterer_use_address, motor_depot.address WHERE (charterer_id = ?) " +
                    "and (motor_depot.address.id = motor_depot.charterer_use_address.address_id); ";
    static final String DELETE_USAGE_OF_ADDRESS =
            "DELETE FROM motor_depot.charterer_use_address WHERE (charterer_id = ?, address_id = ?);";
    static final String GET_LAST_INSERT_ID = "SELECT last_insert_id();";


    private final AddressDao addressDao = new AddressDaoImpl();


    public static final Logger logger = LoggerFactory.getLogger(ChartererDaoImpl.class);

    public ChartererDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createCharterer(Charterer charterer) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CHARTERER_DAO_SQL);

            preparedStatement.setInt(1, charterer.getId());
            preparedStatement.setString(2, charterer.getName());
            preparedStatement.setString(3, charterer.getSurname());
            preparedStatement.setInt(4, charterer.getOwnAddress().getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                logger.error("Sql exception in createCharterer() method");
                throw new DaoException("exception in createCharterer() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createCharterer() method");
            throw new DaoException("exception in createCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in createCharterer() method");
            throw new DaoException("exception in createCharterer() method", e);
        }
    }

    @Override
    public Charterer getCharterer(int id) throws Exception {
        Charterer charterer = new Charterer();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CHARTERER_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                charterer.setId(id);

                if (resultSet.next()) {
                    charterer.setName(resultSet.getString("name"));
                    charterer.setSurname(resultSet.getString("surname"));
                    int ownAddressId = resultSet.getInt("own_address_id");
                    charterer.setOwnAddress(addressDao.getAddress(ownAddressId));
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getCharterer() method");
                throw new DaoException("exception in getCharterer() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getCharterer() method");
            throw new DaoException("exception in getCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in getCharterer() method");
            throw new DaoException("exception in getCharterer() method", e);
        }
        return charterer;
    }

    @Override
    public List<Charterer> getAllCharterers() throws Exception {
        List<Charterer> charterers = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CHARTERERS_SQL);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Charterer charterer = new Charterer();

                    charterer.setId(resultSet.getInt("id"));
                    charterer.setName(resultSet.getString("name"));
                    charterer.setSurname(resultSet.getString("surname"));
                    int ownAddressId = resultSet.getInt("own_address_id");
                    charterer.setOwnAddress(addressDao.getAddress(ownAddressId));

                    charterers.add(charterer);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllCharterers() method");
                throw new DaoException("exception in getAllCharterers() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllCharterers() method");
            throw new DaoException("exception in getAllCharterers() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllCharterers() method");
            throw new DaoException("exception in getAllCharterers() method", e);
        }
        return charterers;
    }

    @Override
    public void updateCharterer(Charterer charterer) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_CHARTERER_SQL);

            preparedStatement.setString(1, charterer.getName());
            preparedStatement.setString(2, charterer.getSurname());
            preparedStatement.setInt(3, charterer.getOwnAddress().getId());

            preparedStatement.setInt(4, charterer.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateCharterer() method");
                throw new DaoException("exception in updateCharterer() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateCharterer() method");
            throw new DaoException("exception in updateCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateCharterer() method");
            throw new DaoException("exception in updateCharterer() method", e);
        }
    }

    @Override
    public void deleteCharterer(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CHARTERER_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                logger.error("Sql exception in deleteCharterer() method");
                throw new DaoException("exception in deleteCharterer() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteCharterer() method");
            throw new DaoException("exception in deleteCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteCharterer() method");
            throw new DaoException("exception in deleteCharterer() method", e);
        }
    }

    @Override
    public void addAddressToCharterer(int addressId, int chartererId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(ADD_ADDRESS_TO_CHARTERER);
            preparedStatement.setInt(1, chartererId);
            preparedStatement.setInt(2, addressId);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in addAddressToCharterer() method");
                throw new DaoException("exception in addAddressToCharterer() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in addAddressToCharterer() method");
            throw new DaoException("exception in addAddressToCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in addAddressToCharterer() method");
            throw new DaoException("exception in addAddressToCharterer() method", e);
        }
    }

    @Override
    public Set<Address> findUsedAddresses(int chartererId) throws Exception {
        Set<Address> addresses = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS_SQL);
            preparedStatement.setInt(1, chartererId);

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
                logger.error("Sql exception in findUsedAddresses() method");
                throw new DaoException("exception in findUsedAddresses() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in findUsedAddresses() method");
            throw new DaoException("exception in findUsedAddresses() method", e);
        } catch (Exception e) {
            logger.error("Exception in findUsedAddresses() method");
            throw new DaoException("exception in findUsedAddresses() method", e);
        }
        return addresses;
    }

    @Override
    public void deleteUsageOfAddress(int addressId, int chartererId) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USAGE_OF_ADDRESS);

            preparedStatement.setInt(1, chartererId);
            preparedStatement.setInt(2, addressId);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteUsageOfAddress() method");
                throw new DaoException("exception in deleteUsageOfAddress() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteUsageOfAddress() method");
            throw new DaoException("exception in deleteUsageOfAddress() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteUsageOfAddress() method");
            throw new DaoException("exception in deleteUsageOfAddress() method", e);
        }
    }

    @Override
    public int createCharterer(String name, String surname, Address address) throws DaoException {
        int id = 0;
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CHARTERER_DAO_SQL_NO_ID);


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, address.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();
                try (PreparedStatement preparedStatementForId = connection.prepareStatement(GET_LAST_INSERT_ID);
                     ResultSet resultSet = preparedStatementForId.executeQuery()) {

                    if (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    logger.error("Sql exception in createAddress() method");
                    throw new DaoException("exception in createAddress() method", e);
                }
            } catch (SQLException e) {
                logger.error("Sql exception in createCharterer() method");
                throw new DaoException("exception in createCharterer() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createCharterer() method");
            throw new DaoException("exception in createCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in createCharterer() method");
            throw new DaoException("exception in createCharterer() method", e);
        }
        return id;
    }

    @Override
    public int createCharterer(String name, String surname, String country, String region, String locality,
                               String street, String building, String apartment) throws DaoException {
        int id = 0;
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CHARTERER_DAO_SQL_NO_ID);


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            int addressId = addressDao.createAddress(country, region, locality, street, building, apartment);
            preparedStatement.setInt(3, addressId);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();
                try (PreparedStatement preparedStatementForId = connection.prepareStatement(GET_LAST_INSERT_ID);
                     ResultSet resultSet = preparedStatementForId.executeQuery()) {

                    if (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    logger.error("Sql exception in createAddress() method");
                    throw new DaoException("exception in createAddress() method", e);
                }
            } catch (SQLException e) {
                logger.error("Sql exception in createCharterer() method");
                throw new DaoException("exception in createCharterer() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createCharterer() method");
            throw new DaoException("exception in createCharterer() method", e);
        } catch (Exception e) {
            logger.error("Exception in createCharterer() method");
            throw new DaoException("exception in createCharterer() method", e);
        }
        return id;
    }

}
