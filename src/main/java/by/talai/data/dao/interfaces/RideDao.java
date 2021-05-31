package by.talai.data.dao.interfaces;


import by.talai.model.Ride;

import java.util.List;

public interface RideDao {
    // createRide
    String createRide(Ride ride);

    // get Ride by id
    Ride getRide(String id);

    // get all Rides
    List<Ride> getAllRides();

    //update Ride
    void updateRide(Ride ride);

    // delete Ride
    boolean deleteRide(String id);
}
