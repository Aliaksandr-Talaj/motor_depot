package by.talai.data.dao.impl;

import by.talai.data.dao.CargoDao;
import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.DeliveryDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Cargo;
import by.talai.model.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DeliveryDaoImpl implements DeliveryDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final CargoDao cargoDao = new CargoDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(DeliveryDaoImpl.class);

    public DeliveryDaoImpl() throws Exception {
    }


    @Override
    public void createDelivery(Delivery delivery) throws Exception {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " +
                            "motor_depot.delivery (id, loading_place_id, loading_date, destination_id, term, " +
                            "request_id, status_id)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, delivery.getId());
            preparedStatement.setInt(2, delivery.getLoadingPlace().getId());
            preparedStatement.setDate(3, delivery.getLoadingDate());
            preparedStatement.setInt(4, delivery.getDestination().getId());
            preparedStatement.setDate(5, delivery.getTerm());
            preparedStatement.setInt(6, delivery.getRequest().getId());
            preparedStatement.setInt(7, delivery.getStatus().getId());

            List<Cargo> cargoList = delivery.getCargoList();
            if (cargoList != null && !cargoList.isEmpty()) {
                for (Cargo cargo : cargoList) {
                    cargoDao.createCargo(cargo);
                }
            }
            try (connection; preparedStatement) {
                preparedStatement.executeUpdate();
                connection.commit();

                connectionPool.returnConnectionToPool(connection, preparedStatement);

            } catch (SQLException e) {
                logger.error("Sql exception in createDelivery() method");
                throw new SQLException("exception in createDelivery() method", e);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Connection pool exception in createDelivery() method");
            throw new ConnectionPoolException("exception in createDelivery() method", e);
        } catch (Exception e) {
            logger.error("Exception in createDelivery() method");
            throw new Exception("exception in createDelivery() method", e);
        }
    }

    @Override
    public Delivery getDelivery(int id) {
        return null;
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return null;
    }

    @Override
    public void updateDelivery(Delivery delivery) {

    }

    @Override
    public void deleteDelivery(int id) {

    }
}
