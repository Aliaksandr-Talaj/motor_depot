package by.talai.data.dao;


import by.talai.model.Ride;

import java.util.List;

public interface RideDao {
    // createRide
    void createRide(Ride ride);

    // get Ride by id
    Ride getRide(int id);

    // get all Rides
    List<Ride> getAllRides();

    //update Ride
    void updateRide(Ride ride);

    // delete Ride
    void deleteRide(String id);
}
