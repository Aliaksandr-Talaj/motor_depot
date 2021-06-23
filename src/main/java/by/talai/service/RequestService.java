package by.talai.service;

import by.talai.model.Request;
import by.talai.service.dto.RequestDto;

import java.sql.Date;
import java.util.List;

public interface RequestService {

    void addNewRequest(Request request) throws Exception;

    void updateRequest(Request request) throws Exception;

    Request getRequest(int id) throws Exception;

    List<Request> getAllRequests() throws Exception;

    List<RequestDto> getRequestDtoList() throws Exception;

    boolean validateDates(Date loadingDate, Date unloadingDate);
}
