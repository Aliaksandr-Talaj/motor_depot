package by.talai.data.dao;


import by.talai.model.Delivery;

import java.util.List;

public interface DeliveryDao {
    // create delivery
    void createDelivery(Delivery delivery) throws Exception;

    // get delivery by id
    Delivery getDelivery(int id);

    // get all delivery
    List<Delivery> getAllDeliveries();

    //update delivery
    void updateDelivery(Delivery delivery);

    // delete delivery
    void deleteDelivery(int id);
}
