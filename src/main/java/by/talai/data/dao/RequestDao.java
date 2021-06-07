package by.talai.data.dao;

import by.talai.model.Request;

import java.util.List;

public interface RequestDao {
    // create Request
    String createRequest(Request request);

    // get Request by id
    Request getRequest(String id);

    // get all Requests
    List<Request> getAllRequests();

    //update Request
    void updateRequest(Request request);

    // delete Request
    boolean deleteRequest(String id);
}
