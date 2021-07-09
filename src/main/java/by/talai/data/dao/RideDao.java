package by.talai.data.dao;


import by.talai.model.Ride;
import by.talai.model.stock.Automobile;

import java.util.List;

/**
 * The interface Ride dao.
 */
public interface RideDao {
    /**
     * Create ride ride.
     *
     * @param ride the ride
     * @return the ride
     * @throws Exception the exception
     */
// createRide
    Ride createRide(Ride ride) throws Exception;

    /**
     * Gets ride.
     *
     * @param id the id
     * @return the ride
     * @throws Exception the exception
     */
// get Ride by id
    Ride getRide(int id) throws Exception;

    /**
     * Gets all rides.
     *
     * @return the all rides
     * @throws Exception the exception
     */
// get all Rides
    List<Ride> getAllRides() throws Exception;

    /**
     * Gets all rides of automobile.
     *
     * @param automobile the automobile
     * @return the all rides of automobile
     * @throws Exception the exception
     */
    List<Ride> getAllRidesOfAutomobile(Automobile automobile) throws Exception;

    /**
     * Update ride.
     *
     * @param ride the ride
     * @throws Exception the exception
     */
//update Ride
    void updateRide(Ride ride) throws Exception;

    /**
     * Delete ride.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete Ride
    void deleteRide(int id) throws Exception;
}
