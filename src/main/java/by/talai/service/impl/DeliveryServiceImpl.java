package by.talai.service.impl;

import by.talai.data.dao.DeliveryDao;
import by.talai.data.dao.impl.DeliveryDaoImpl;
import by.talai.model.Delivery;
import by.talai.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryDao deliveryDao = new DeliveryDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    public DeliveryServiceImpl() throws Exception {
    }


    @Override
    public Delivery getDelivery(int id) throws Exception {
        return deliveryDao.getDelivery(id);
    }
}
