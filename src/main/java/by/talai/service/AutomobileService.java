package by.talai.service;

import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.stock.Automobile;
import by.talai.service.dto.AutomobilesDto;

import java.util.List;

public interface AutomobileService {

    void saveNewAutomobile(Automobile automobile) throws Exception;

    Automobile findAutomobileById(String id) throws Exception;

    List<Automobile> findAllAutomobiles() throws Exception;

    void updateAutomobile(Automobile automobile) throws Exception;

    AutomobilesDto getAllAutomobilesDto() throws Exception;

    void addNewAutomobile(String id, String brand, String model, String fuel, String carrying, String type,
                          String equipment1, String equipment2, String equipment3, String top, String rear,
                          String side, String platformLength, String platformWidth, String heightLimit,
                          String volumeLimit) throws Exception;
}
