package by.talai.service;

import by.talai.data.dao.DeliveryDao;
import by.talai.data.dao.RequestDao;
import by.talai.data.dao.impl.DeliveryDaoImpl;
import by.talai.data.dao.impl.RequestDaoImpl;
import by.talai.model.Delivery;
import by.talai.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestService {

    public static final Logger logger = LoggerFactory.getLogger(RequestService.class);

    private final RequestDao requestDao = new RequestDaoImpl();
    private final DeliveryDao deliveryDao = new DeliveryDaoImpl();

    public RequestService() throws Exception {
    }

    public void addNewRequest(Request request) throws Exception {
        requestDao.createRequest(request);

        List<Delivery> deliveryList = request.getDeliveryList();

        if (deliveryList != null && !deliveryList.isEmpty()) {
            for (Delivery delivery : deliveryList) {
                deliveryDao.addOrUpdateDelivery(delivery);
            }
        }
    }


    public void updateRequest(Request request) throws Exception {
        requestDao.updateRequest(request);

        List<Delivery> deliveryList = request.getDeliveryList();

        if (deliveryList != null && !deliveryList.isEmpty()) {
            for (Delivery delivery : deliveryList) {
                deliveryDao.addOrUpdateDelivery(delivery);
            }
        }
    }

}
