package by.talai.service;

import by.talai.model.Delivery;
import by.talai.model.Ride;
import by.talai.model.stock.Automobile;

import java.util.List;

public interface RideService {
    Ride createRide(Delivery delivery, Automobile automobile, int dispatcherId) throws Exception;

    Ride createRide(int deliveryId, String automobileId, int dispatcherId) throws Exception;

    List<Ride> getRides() throws Exception;
}
