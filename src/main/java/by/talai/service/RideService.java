package by.talai.service;

import by.talai.model.Delivery;
import by.talai.model.Ride;
import by.talai.model.stock.Automobile;

import java.util.List;

/**
 * The interface Ride service.
 */
public interface RideService {
    /**
     * Create ride ride.
     *
     * @param delivery     the delivery
     * @param automobile   the automobile
     * @param dispatcherId the dispatcher id
     * @return the ride
     * @throws Exception the exception
     */
    Ride createRide(Delivery delivery, Automobile automobile, int dispatcherId) throws Exception;

    /**
     * Create ride ride.
     *
     * @param deliveryId   the delivery id
     * @param automobileId the automobile id
     * @param dispatcherId the dispatcher id
     * @return the ride
     * @throws Exception the exception
     */
    Ride createRide(int deliveryId, String automobileId, int dispatcherId) throws Exception;

    /**
     * Gets rides.
     *
     * @return the rides
     * @throws Exception the exception
     */
    List<Ride> getRides() throws Exception;

    /**
     * Gets rides of automobile.
     *
     * @param automobile the automobile
     * @return the rides of automobile
     * @throws Exception the exception
     */
    List<Ride> getRidesOfAutomobile(Automobile automobile) throws Exception;

    /**
     * Accept ride.
     *
     * @param rideId the ride id
     * @throws Exception the exception
     */
    void acceptRide(int rideId) throws Exception;

    /**
     * Complete ride.
     *
     * @param rideId the ride id
     * @throws Exception the exception
     */
    void completeRide(int rideId) throws Exception;

    /**
     * Cancel ride.
     *
     * @param rideId the ride id
     * @throws Exception the exception
     */
    void cancelRide(int rideId) throws Exception;
}
