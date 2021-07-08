package by.talai.service.impl;

import by.talai.data.dao.RideDao;
import by.talai.data.dao.StatusDao;
import by.talai.data.dao.impl.ExecutionStatusDaoImpl;
import by.talai.data.dao.impl.RideDaoImpl;
import by.talai.model.Delivery;
import by.talai.model.Request;
import by.talai.model.Ride;
import by.talai.model.stock.Automobile;
import by.talai.service.AutomobileService;
import by.talai.service.DeliveryService;
import by.talai.service.RideService;
import by.talai.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

public class RideServiceImpl implements RideService {

    private final RideDao rideDao = new RideDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(RideServiceImpl.class);

    public RideServiceImpl() throws Exception {
    }

    @Override
    public Ride createRide(Delivery delivery, Automobile automobile, int dispatcherId) throws Exception {
        Ride ride = new Ride();
        Request request = delivery.getRequest();
        UserService userService = new UserServiceImpl();
        ride.setCargoList(delivery.getCargoList());
        ride.setLoadingDate(delivery.getLoadingDate());
        ride.setTerm(delivery.getTerm());
        ride.setAutomobile(automobile);
        ride.setLoadingPlace(delivery.getLoadingPlace());
        ride.setDestination(delivery.getDestination());
        ride.setRequest(request);
        ride.setDispatcher(userService.findUser(dispatcherId));
        ride.setDate(new Date(new java.util.Date().getTime()));
        StatusDao statusDao = new ExecutionStatusDaoImpl();
        ride.setExecutionStatus(statusDao.findStatus(1));
        return rideDao.createRide(ride);
    }

    @Override
    public Ride createRide(int deliveryId, String automobileId, int dispatcherId) throws Exception {
        DeliveryService deliveryService = new DeliveryServiceImpl();
        AutomobileService automobileService = new AutomobileServiceImpl();
        Delivery delivery = deliveryService.getDelivery(deliveryId);
        Automobile automobile = automobileService.findAutomobileById(automobileId);
        return createRide(delivery, automobile, dispatcherId);
    }


    @Override
    public List<Ride> getRides() throws Exception {
        return rideDao.getAllRides();
    }


}
