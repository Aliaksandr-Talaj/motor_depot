package by.talai.data.dao;


import by.talai.model.Delivery;
import by.talai.model.Request;

import java.util.List;

/**
 * The interface Delivery dao.
 */
public interface DeliveryDao {
    /**
     * Create delivery delivery.
     *
     * @param delivery the delivery
     * @return the delivery
     * @throws Exception the exception
     */
// create delivery
    Delivery createDelivery(Delivery delivery) throws Exception;

    /**
     * Gets delivery.
     *
     * @param id the id
     * @return the delivery
     * @throws Exception the exception
     */
// get delivery by id
    Delivery getDelivery(int id) throws Exception;

    /**
     * Gets all deliveries.
     *
     * @return the all deliveries
     * @throws Exception the exception
     */
// get all delivery
    List<Delivery> getAllDeliveries() throws Exception;

    /**
     * Update delivery.
     *
     * @param delivery the delivery
     * @throws Exception the exception
     */
//update delivery
    void updateDelivery(Delivery delivery) throws Exception;

    /**
     * Delete delivery.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete delivery
    void deleteDelivery(int id) throws Exception;

    /**
     * Gets all deliveries of request.
     *
     * @param request the request
     * @return the all deliveries of request
     * @throws Exception the exception
     */
    List<Delivery> getAllDeliveriesOfRequest(Request request) throws Exception;

    /**
     * Add or update delivery.
     *
     * @param delivery         the delivery
     * @param cargoListIsSaved the cargo list is saved
     * @throws Exception the exception
     */
    void addOrUpdateDelivery(Delivery delivery, boolean cargoListIsSaved) throws Exception;
}
