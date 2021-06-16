package by.talai.data.dao;


import by.talai.model.Delivery;
import by.talai.model.Request;

import java.util.List;

public interface DeliveryDao {
    // create delivery
    void createDelivery(Delivery delivery) throws Exception;

    // get delivery by id
    Delivery getDelivery(int id) throws Exception;

    // get all delivery
    List<Delivery> getAllDeliveries() throws Exception;

    //update delivery
    void updateDelivery(Delivery delivery) throws Exception;

    // delete delivery
    void deleteDelivery(int id) throws Exception;

    List<Delivery> getAllDeliveriesOfRequest(Request request) throws Exception;

    void addOrUpdateDelivery(Delivery delivery) throws Exception;
}
