package by.talai.data.dao;

import by.talai.model.Request;

import java.util.List;

public interface RequestDao {
    // create Request
    String createRequest(Request request);

    // get Request by id
    Request getRequest(int id);

    // get all Requests
    List<Request> getAllRequests();

    //update Request
    void updateRequest(Request request);

    // delete Request
    void deleteRequest(String id);
}
