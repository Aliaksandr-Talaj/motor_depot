package by.talai.service.impl;

import by.talai.data.dao.DeliveryDao;
import by.talai.data.dao.RequestDao;
import by.talai.data.dao.StatusDao;
import by.talai.data.dao.impl.DeliveryDaoImpl;
import by.talai.data.dao.impl.ExecutionStatusDaoImpl;
import by.talai.data.dao.impl.RequestDaoImpl;
import by.talai.model.Delivery;
import by.talai.model.Request;
import by.talai.model.Status;
import by.talai.service.RequestService;
import by.talai.service.dto.RequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

public class RequestServiceImpl implements RequestService {

    public static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    private final RequestDao requestDao = new RequestDaoImpl();
    private final DeliveryDao deliveryDao = new DeliveryDaoImpl();

    public RequestServiceImpl() throws Exception {
    }


    @Override
    public void addNewRequest(Request request, int userId) throws Exception {
        String id = generateId(userId);
        request.setId(id);
        addNewRequest(request);
    }

    @Override
    public void addNewRequest(Request request) throws Exception {
        if (request.getExecutionStatus() == null) {
            Status status = new Status();
            status.setId(1);
            request.setExecutionStatus(status);
        }
        requestDao.createRequest(request);
        List<Delivery> deliveryList = request.getDeliveryList();

        if (deliveryList != null && !deliveryList.isEmpty()) {
            for (Delivery delivery : deliveryList) {
                deliveryDao.addOrUpdateDelivery(delivery, true);
            }
        }
    }

    @Override
    public void updateRequest(Request request) throws Exception {
        requestDao.updateRequest(request);

        List<Delivery> deliveryList = request.getDeliveryList();

        if (deliveryList != null && !deliveryList.isEmpty()) {
            for (Delivery delivery : deliveryList) {
                deliveryDao.addOrUpdateDelivery(delivery, false);
            }
        }
    }

    @Override
    public Request getRequest(String id) throws Exception {
        return requestDao.getRequest(id);
    }

    @Override
    public List<Request> getAllRequests() throws Exception {
        List<Request> requests = requestDao.getAllRequests();
        for (Request request : requests) {
            List<Delivery> deliveryList = deliveryDao.getAllDeliveriesOfRequest(request);
            request.setDeliveryList(deliveryList);
        }
        return requests;
    }

    @Override
    public List<RequestDto> getRequestDtoList() throws Exception {
        List<RequestDto> requestDtoList = requestDao.getAllRequestsDto();

        return requestDtoList;
    }

    @Override
    public boolean validateDates(Date loadingDate, Date unloadingDate) {
        Date now = new Date(new java.util.Date().getTime());
        return !loadingDate.after(unloadingDate)
                && !loadingDate.before(now);
    }

    private String generateId(int userId) {
        return String.valueOf(new java.util.Date().getTime()) +
                '-' +
                userId;
    }

    @Override
    public void quenchRequest(Request request) throws Exception {
        StatusDao statusDao = new ExecutionStatusDaoImpl();
        request.setExecutionStatus(statusDao.findStatus(2));
        requestDao.updateRequest(request);
    }
}
