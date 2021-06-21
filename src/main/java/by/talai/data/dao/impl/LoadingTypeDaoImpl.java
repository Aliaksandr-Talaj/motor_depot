package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.LoadingTypeDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.stock.LoadingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class LoadingTypeDaoImpl implements LoadingTypeDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(LoadingTypeDaoImpl.class);

    public LoadingTypeDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public void createLoadingType(LoadingType loadingType) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.loading_type (id, loading_type)" +
                            " VALUES (?, ?);");
            preparedStatement.setInt(1, loadingType.getId());
            preparedStatement.setString(2, loadingType.getType());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in createLoadingType() method");
                throw new DaoException("exception in createLoadingType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createLoadingType() method");
            throw new DaoException("exception in createLoadingType() method", e);
        } catch (Exception e) {
            logger.error("Exception in createLoadingType() method");
            throw new DaoException("exception in createLoadingType() method", e);
        }
    }

    @Override
    public LoadingType getLoadingType(int loadingTypeId) throws Exception {
        LoadingType loadingType = new LoadingType();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.loading_type WHERE id = ?;");
            preparedStatement.setInt(1, loadingTypeId);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                loadingType.setId(loadingTypeId);

                if (resultSet.next()) {
                    loadingType.setType(resultSet.getString("loading_type"));
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getLoadingType() method");
                throw new DaoException("exception in getLoadingType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getLoadingType() method");
            throw new DaoException("exception in getLoadingType() method", e);
        } catch (Exception e) {
            logger.error("Exception in getLoadingType() method");
            throw new DaoException("exception in getLoadingType() method", e);
        }
        return loadingType;
    }

    @Override
    public Set<LoadingType> getAllLoadingTypes() throws Exception {
        Set<LoadingType> loadingTypes = new HashSet<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM motor_depot.loading_type; ");

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    LoadingType loadingType = new LoadingType();

                    loadingType.setId(resultSet.getInt("id"));
                    loadingType.setType(resultSet.getString("loading_type"));

                    loadingTypes.add(loadingType);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllLoadingTypes() method");
                throw new DaoException("exception in getAllLoadingTypes() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllLoadingTypes() method");
            throw new DaoException("exception in getAllLoadingTypes() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllLoadingTypes() method");
            throw new DaoException("exception in getAllLoadingTypes() method", e);
        }
        return loadingTypes;
    }

    @Override
    public void updateLoadingType(LoadingType loadingType) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE motor_depot.loading_type " +
                            "SET loading_type = ? WHERE (id = ?);");


            preparedStatement.setString(1, loadingType.getType());

            preparedStatement.setInt(2, loadingType.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateLoadingType() method");
                throw new DaoException("exception in updateLoadingType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateLoadingType() method");
            throw new DaoException("exception in updateLoadingType() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateLoadingType() method");
            throw new DaoException("exception in updateLoadingType() method", e);
        }
    }

    @Override
    public void deleteLoadingType(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM motor_depot.loading_type WHERE (id = ?);");

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteLoadingType() method");
                throw new DaoException("exception in deleteLoadingType() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteLoadingType() method");
            throw new DaoException("exception in deleteLoadingType() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteLoadingType() method");
            throw new DaoException("exception in deleteLoadingType() method", e);
        }
    }
}
