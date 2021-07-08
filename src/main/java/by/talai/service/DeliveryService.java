package by.talai.service;

import by.talai.model.Delivery;
import by.talai.model.stock.Automobile;
import by.talai.service.dto.AutomobileLoadingDto;

public interface DeliveryService {

    Delivery getDelivery(int id) throws Exception;

    Delivery addDelivery(Delivery delivery) throws Exception;

    AutomobileLoadingDto getAutomobileLoadingDto(Automobile automobile, Delivery delivery);
}
