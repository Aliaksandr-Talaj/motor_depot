package by.talai.service.impl;

import by.talai.data.dao.RideDao;
import by.talai.data.dao.impl.RideDaoImpl;
import by.talai.model.Ride;
import by.talai.service.RideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RideServiceImpl implements RideService {

    private final RideDao rideDao = new RideDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(RideServiceImpl.class);

    public RideServiceImpl() throws Exception {
    }

@Override
    public List<Ride> getRides() throws Exception {
        return rideDao.getAllRides();
    }


}
