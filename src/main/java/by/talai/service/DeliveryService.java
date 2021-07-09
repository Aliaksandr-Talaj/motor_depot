package by.talai.service;

import by.talai.model.Delivery;
import by.talai.model.stock.Automobile;
import by.talai.service.dto.AutomobileLoadingDto;

/**
 * The interface Delivery service.
 */
public interface DeliveryService {

    /**
     * Gets delivery.
     *
     * @param id the id
     * @return the delivery
     * @throws Exception the exception
     */
    Delivery getDelivery(int id) throws Exception;

    /**
     * Add delivery delivery.
     *
     * @param delivery the delivery
     * @return the delivery
     * @throws Exception the exception
     */
    Delivery addDelivery(Delivery delivery) throws Exception;

    /**
     * Gets automobile loading dto.
     *
     * @param automobile the automobile
     * @param delivery   the delivery
     * @return the automobile loading dto
     */
    AutomobileLoadingDto getAutomobileLoadingDto(Automobile automobile, Delivery delivery);
}
