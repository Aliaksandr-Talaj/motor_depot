package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.Request;
import by.talai.service.dto.RequestDto;

import java.util.List;

/**
 * The interface Request dao.
 */
public interface RequestDao {
    /**
     * Create request.
     *
     * @param request the request
     * @throws Exception the exception
     */
// create Request
    void createRequest(Request request) throws Exception;

    // get Request by id

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
// get all Requests
    List<Request> getAllRequests() throws Exception;

    /**
     * Update request.
     *
     * @param request the request
     * @throws Exception the exception
     */
//update Request
    void updateRequest(Request request) throws Exception;

    // delete Request

    /**
     * Delete request.
     *
     * @param id the id
     * @throws Exception the exception
     */
    void deleteRequest(String id) throws Exception;

    /**
     * Gets all requests dto.
     *
     * @return the all requests dto
     * @throws DaoException the dao exception
     */
    List<RequestDto> getAllRequestsDto() throws DaoException;
}
