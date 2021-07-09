package by.talai.service;

import by.talai.model.Request;
import by.talai.service.dto.RequestDto;

import java.sql.Date;
import java.util.List;

/**
 * The interface Request service.
 */
public interface RequestService {

    /**
     * Add new request.
     *
     * @param request the request
     * @throws Exception the exception
     */
    void addNewRequest(Request request) throws Exception;

    /**
     * Add new request.
     *
     * @param request the request
     * @param userId  the user id
     * @throws Exception the exception
     */
    void addNewRequest(Request request, int userId) throws Exception;

    /**
     * Update request.
     *
     * @param request the request
     * @throws Exception the exception
     */
    void updateRequest(Request request) throws Exception;

    /**
     * Gets request.
     *
     * @param id the id
     * @return the request
     * @throws Exception the exception
     */
    Request getRequest(String id) throws Exception;

    /**
     * Gets all requests.
     *
     * @return the all requests
     * @throws Exception the exception
     */
    List<Request> getAllRequests() throws Exception;

    /**
     * Gets request dto list.
     *
     * @return the request dto list
     * @throws Exception the exception
     */
    List<RequestDto> getRequestDtoList() throws Exception;

    /**
     * Validate dates boolean.
     *
     * @param loadingDate   the loading date
     * @param unloadingDate the unloading date
     * @return the boolean
     */
    boolean validateDates(Date loadingDate, Date unloadingDate);

    /**
     * Quench request.
     *
     * @param request the request
     * @throws Exception the exception
     */
    void quenchRequest(Request request) throws Exception;
}
