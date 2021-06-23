package by.talai.service.impl;

import by.talai.data.dao.DeliveryDao;
import by.talai.data.dao.EquipmentDao;
import by.talai.data.dao.RequestDao;
import by.talai.data.dao.impl.DeliveryDaoImpl;
import by.talai.data.dao.impl.EquipmentDaoImpl;
import by.talai.data.dao.impl.RequestDaoImpl;
import by.talai.model.Delivery;
import by.talai.model.Request;
import by.talai.model.stock.Equipment;
import by.talai.service.RequestService;
import by.talai.service.dto.RequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequestServiceImpl implements RequestService {

    public static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    private final RequestDao requestDao = new RequestDaoImpl();
    private final DeliveryDao deliveryDao = new DeliveryDaoImpl();

    public RequestServiceImpl() throws Exception {
    }

    @Override
    public void addNewRequest(Request request) throws Exception {
        requestDao.createRequest(request);

        List<Delivery> deliveryList = request.getDeliveryList();

        if (deliveryList != null && !deliveryList.isEmpty()) {
            for (Delivery delivery : deliveryList) {
                deliveryDao.addOrUpdateDelivery(delivery);
            }
        }
    }

    @Override
    public void updateRequest(Request request) throws Exception {
        requestDao.updateRequest(request);

        List<Delivery> deliveryList = request.getDeliveryList();

        if (deliveryList != null && !deliveryList.isEmpty()) {
            for (Delivery delivery : deliveryList) {
                deliveryDao.addOrUpdateDelivery(delivery);
            }
        }
    }

    @Override
    public Request getRequest(int id) throws Exception {
        return requestDao.getRequest(id);
    }

    @Override
    public List<Request> getAllRequests() throws Exception {
        return requestDao.getAllRequests();
    }

    @Override
    public List<RequestDto> getRequestDtoList() throws Exception {
        List<RequestDto> requestDtoList = new ArrayList<>();
        List<Request> requests = requestDao.getAllRequests();
        EquipmentDao equipmentDao = new EquipmentDaoImpl();
        for (Request request : requests) {
            RequestDto requestDto = new RequestDto();
            requestDto.setRequest(request);
            Set<Equipment> equipmentSet = equipmentDao.getAllEquipmentOfRequest(request.getId());
            requestDto.setEquipmentSet(equipmentSet);
            requestDtoList.add(requestDto);
        }
        return requestDtoList;
    }

    @Override
    public boolean validateDates(Date loadingDate, Date unloadingDate) {
        Date now = new Date(new java.util.Date().getTime());
        return !loadingDate.after(unloadingDate)
                && !loadingDate.before(now);
    }


}
