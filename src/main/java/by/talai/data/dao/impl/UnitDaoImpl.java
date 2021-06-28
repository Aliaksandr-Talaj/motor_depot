package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.UnitDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDaoImpl implements UnitDao {

    static final String CREATE_UNIT_SQL = "INSERT INTO motor_depot.unit (id, type, length, width, height, weight)" +
            " VALUES (?, ?, ?, ?, ?, ?);";
    static final String CREATE_UNIT_SQL_NO_ID = "INSERT INTO motor_depot.unit (type, length, width, height, weight)" +
            " VALUES (?, ?, ?, ?, ?);";
    static final String GET_UNIT_SQL = "SELECT * FROM motor_depot.unit WHERE id = ?;";
    static final String GET_ALL_UNITS_SQL = "SELECT * FROM motor_depot.unit; ";
    static final String UPDATE_UNIT_SQL = "UPDATE motor_depot.unit " +
            "SET type = ?, length = ?, width = ?, height = ?, weight = ? WHERE (id = ?);";
    static final String DELETE_UNIT_SQL = "DELETE FROM motor_depot.unit WHERE (id = ?);";
    static final String GET_LAST_INSERT_ID_SQL = "SELECT last_insert_id();";


    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger logger = LoggerFactory.getLogger(UnitDaoImpl.class);

    public UnitDaoImpl() throws Exception {
    }

    @Override
    public void createUnit(Unit unit) throws Exception {

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_UNIT_SQL);
            preparedStatement.setInt(1, unit.getId());
            preparedStatement.setString(2, unit.getType());

            preparedStatement.setInt(3, unit.getLength());
            preparedStatement.setInt(4, unit.getWidth());
            preparedStatement.setDouble(5, unit.getHeight());
            preparedStatement.setDouble(6, unit.getWeight());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in createUnit() method");
                throw new DaoException("exception in createUnit() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createUnit() method");
            throw new DaoException("exception in createUnit() method", e);
        } catch (Exception e) {
            logger.error("Exception in createUnit() method");
            throw new DaoException("exception in createUnit() method", e);
        }
           }

    @Override
    public int createUnitReturnId(Unit unit) throws Exception {
        int id=0;
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(CREATE_UNIT_SQL_NO_ID);

            preparedStatement.setString(1, unit.getType());
            preparedStatement.setInt(2, unit.getLength());
            preparedStatement.setInt(3, unit.getWidth());
            preparedStatement.setDouble(4, unit.getHeight());
            preparedStatement.setDouble(5, unit.getWeight());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();
                try (PreparedStatement preparedStatementForId = connection.prepareStatement(GET_LAST_INSERT_ID_SQL);
                     ResultSet resultSet = preparedStatementForId.executeQuery()) {

                    if (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    logger.error("Sql exception in createUnit() method");
                    throw new DaoException("exception in createAddress() method", e);
                }

            } catch (SQLException e) {
                logger.error("Sql exception in createUnit() method");
                throw new DaoException("exception in createUnit() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createUnit() method");
            throw new DaoException("exception in createUnit() method", e);
        } catch (Exception e) {
            logger.error("Exception in createUnit() method");
            throw new DaoException("exception in createUnit() method", e);
        }
        return id;
    }

    @Override
    public Unit getUnit(int id) throws Exception {
        Unit unit = new Unit();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_UNIT_SQL);
            preparedStatement.setInt(1, id);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                unit.setId(id);

                if (resultSet.next()) {
                    unit.setType(resultSet.getString("type"));
                    unit.setLength(resultSet.getInt("length"));
                    unit.setWidth(resultSet.getInt("width"));
                    unit.setHeight(resultSet.getDouble("height"));
                    unit.setWeight(resultSet.getDouble("weight"));
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getUnit() method");
                throw new DaoException("exception in getUnit() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getUnit() method");
            throw new DaoException("exception in getUnit() method", e);
        } catch (Exception e) {
            logger.error("Exception in getUnit() method");
            throw new DaoException("exception in getUnit() method", e);
        }
        return unit;
    }

    @Override
    public List<Unit> getAllUnits() throws Exception {

        List<Unit> unitList = new ArrayList<>();
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_ALL_UNITS_SQL);

            try (connection; preparedStatement; ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Unit unit = new Unit();

                    unit.setId(resultSet.getInt("id"));
                    unit.setType(resultSet.getString("type"));
                    unit.setLength(resultSet.getInt("length"));
                    unit.setWidth(resultSet.getInt("width"));
                    unit.setHeight(resultSet.getDouble("height"));
                    unit.setWeight(resultSet.getDouble("weight"));

                    unitList.add(unit);
                }


            } catch (SQLException e) {
                logger.error("Sql exception in getAllUnits() method");
                throw new DaoException("exception in getAllUnits() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in getAllUnits() method");
            throw new DaoException("exception in getAllUnits() method", e);
        } catch (Exception e) {
            logger.error("Exception in getAllUnits() method");
            throw new DaoException("exception in getAllUnits() method", e);
        }
        return unitList;
    }

    @Override
    public void updateUnit(Unit unit) throws Exception {

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_UNIT_SQL);


            preparedStatement.setString(1, unit.getType());
            preparedStatement.setInt(2, unit.getLength());
            preparedStatement.setInt(3, unit.getWidth());
            preparedStatement.setDouble(4, unit.getHeight());
            preparedStatement.setDouble(5, unit.getWeight());

            preparedStatement.setInt(6, unit.getId());

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in updateUnit() method");
                throw new DaoException("exception in updateUnit() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in updateUnit() method");
            throw new DaoException("exception in updateUnit() method", e);
        } catch (Exception e) {
            logger.error("Exception in updateUnit() method");
            throw new DaoException("exception in updateUnit() method", e);
        }

    }

    @Override
    public void deleteUnit(int id) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_UNIT_SQL);

            preparedStatement.setInt(1, id);

            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();


            } catch (SQLException e) {
                logger.error("Sql exception in deleteUnit() method");
                throw new DaoException("exception in deleteUnit() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in deleteUnit() method");
            throw new DaoException("exception in deleteUnit() method", e);
        } catch (Exception e) {
            logger.error("Exception in deleteUnit() method");
            throw new DaoException("exception in deleteUnit() method", e);
        }
    }
}
