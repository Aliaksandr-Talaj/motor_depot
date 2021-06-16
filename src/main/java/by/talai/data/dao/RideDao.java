package by.talai.data.dao;


import by.talai.model.Ride;

import java.util.List;

public interface RideDao {
    // createRide
    void createRide(Ride ride) throws Exception;

    // get Ride by id
    Ride getRide(int id) throws Exception;

    // get all Rides
    List<Ride> getAllRides() throws Exception;

    //update Ride
    void updateRide(Ride ride) throws Exception;

    // delete Ride
    void deleteRide(int id) throws Exception;
}
