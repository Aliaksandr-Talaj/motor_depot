package by.talai.service;

import by.talai.model.Request;

public interface RequestService {

    void addNewRequest(Request request) throws Exception;

    void updateRequest(Request request) throws Exception;
}
