package by.talai.service;

import by.talai.model.AutomobileAttachment;
import by.talai.model.stock.Automobile;
import by.talai.service.dto.AutomobilesDto;

import java.util.List;

public interface AutomobileService {

    void saveNewAutomobile(Automobile automobile) throws Exception;

    Automobile findAutomobileById(String id) throws Exception;

    List<Automobile> findAllAutomobiles() throws Exception;

    void updateAutomobile(Automobile automobile) throws Exception;

    AutomobilesDto getAllAutomobilesDto() throws Exception;

}
