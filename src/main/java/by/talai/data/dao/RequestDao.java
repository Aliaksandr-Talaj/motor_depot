package by.talai.data.dao;

import by.talai.model.Request;

import java.util.List;

public interface RequestDao {
    // create Request
    void createRequest(Request request) throws Exception;

    // get Request by id

    Request getRequest(String id) throws Exception;

    // get all Requests
    List<Request> getAllRequests() throws Exception;

    //update Request
    void updateRequest(Request request) throws Exception;

    // delete Request

    void deleteRequest(String id) throws Exception;
}
