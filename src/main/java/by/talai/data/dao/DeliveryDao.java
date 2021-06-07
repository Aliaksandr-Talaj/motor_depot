package by.talai.data.dao;


import by.talai.model.Delivery;

import java.util.List;

public interface DeliveryDao {
    // create delivery
    String createDelivery(Delivery delivery);

    // get delivery by id
    Delivery getDelivery(String id);

    // get all delivery
    List<Delivery> getAllDeliveries();

    //update delivery
    void updateDelivery(Delivery delivery);

    // delete delivery
    boolean deleteDelivery(String id);
}
