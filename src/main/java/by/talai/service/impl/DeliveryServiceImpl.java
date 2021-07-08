package by.talai.service.impl;

import by.talai.data.dao.DeliveryDao;
import by.talai.data.dao.impl.DeliveryDaoImpl;
import by.talai.model.*;
import by.talai.model.stock.Automobile;
import by.talai.service.AutomobileService;
import by.talai.service.CargoService;
import by.talai.service.DeliveryService;
import by.talai.service.dto.AutomobileLoadingDto;
import by.talai.service.dto.DeliveryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryDao deliveryDao = new DeliveryDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    public DeliveryServiceImpl() throws Exception {
    }


    @Override
    public Delivery getDelivery(int id) throws Exception {
        return deliveryDao.getDelivery(id);
    }

    @Override
    public Delivery addDelivery(Delivery delivery) throws Exception {
        if (delivery.getExecutionStatus() == null) {
            Status status = new Status();
            status.setId(1);
            delivery.setExecutionStatus(status);
        }
        delivery = deliveryDao.createDelivery(delivery);

        List<Cargo> cargoList = delivery.getCargoList();
        if (cargoList != null && !cargoList.isEmpty()) {
            CargoService cargoService = new CargoServiceImpl();
            for (Cargo cargo : cargoList) {
                cargo.setDelivery(delivery);
                cargoService.saveCargo(cargo);
            }
        }
        return delivery;
    }


    // finding out loading variants of the delivery
    public List<DeliveryDto> processDeliveries(Request request) throws Exception {
        List<DeliveryDto> deliveryDtoList = new ArrayList<>();

        List<Delivery> deliveryList = request.getDeliveryList();

        AutomobileService automobileService = new AutomobileServiceImpl();

        for (Delivery delivery : deliveryList) {
            DeliveryDto deliveryDto = new DeliveryDto();
            Set<Automobile> suitableAutomobiles = automobileService.findSuitableAutomobiles(request);
            Set<AutomobileLoadingDto> automobileLoadingDtoSet = new HashSet<>();

            for (Automobile automobile : suitableAutomobiles) {
                AutomobileLoadingDto automobileLoadingDto = getAutomobileLoadingDto(automobile, delivery);
                automobileLoadingDtoSet.add(automobileLoadingDto);
            }

            deliveryDto.setAutomobileLoadingDtoSet(automobileLoadingDtoSet);
            deliveryDto.setDelivery(delivery);

            deliveryDtoList.add(deliveryDto);
        }

        return deliveryDtoList;
    }

@Override
    public AutomobileLoadingDto getAutomobileLoadingDto(Automobile automobile, Delivery delivery) {
        AutomobileLoadingDto automobileLoadingDto = new AutomobileLoadingDto();
        automobileLoadingDto.setAutomobile(automobile);
        Map<Cargo, Double> fitQuantityMap = getFitQuantityMap(automobile, delivery);
    System.out.println(fitQuantityMap);
        automobileLoadingDto.setFitQuantityOfCargoMap(fitQuantityMap);
        automobileLoadingDto.setLoadingPercent(countLoadingPercent(automobile, fitQuantityMap));
    System.out.println(countLoadingPercent(automobile,fitQuantityMap));
        return automobileLoadingDto;
    }


    private double countLoadingPercent(Automobile automobile, Map<Cargo, Double> fitQuantityMap) {
        int carrying = automobile.getCarrying();
        double cargoWeight = 0;

        for (Cargo cargo : fitQuantityMap.keySet()) {
            cargoWeight = cargoWeight + cargo.getUnit().getWeight() * fitQuantityMap.get(cargo);
        }
        return (cargoWeight / carrying) * 100;
    }

    // returns a map, where every cargo as key has its quantity as value
    private Map<Cargo, Double> getFitQuantityMap(Automobile automobile, Delivery delivery) {
        System.out.println(automobile);
        List<Cargo> cargoList = delivery.getCargoList();
        Map<Cargo, Double> fitQuantityMap = new HashMap<>();
        for (Cargo cargo : cargoList) {
            System.out.println(cargo);
           fitQuantityMap.put(cargo, countFitQuantity(automobile, cargo));
        }
        return fitQuantityMap;
    }


    //counts how many units of the cargo can be contained by the automobile
    private double countFitQuantity(Automobile automobile, Cargo cargo) {
        double cargoQuantity = cargo.getQuantity();

        int platformLength = automobile.getPlatformLength();
        System.out.println(platformLength);
        int platformWidth = automobile.getPlatformWidth();
        System.out.println(platformWidth);
        double heightLimit = automobile.getCargoHeightLimit();
        System.out.println(heightLimit);

        Unit unit = cargo.getUnit();
        int unitLength = Math.max(unit.getLength(), unit.getWidth());
        System.out.println(unitLength);
        int unitWidth = Math.min(unit.getLength(), unit.getWidth());
        System.out.println(unitWidth);
        double unitHeight = unit.getHeight();
        System.out.println(unitHeight);

        int freeLength = platformLength;
        int freeWidth = platformWidth;

        //first iteration
        int xRows1 = 0;
        int yRows1 = 0;
        int zRows = 0;
        int total1 = 0;

        if (platformWidth <= unitLength) {
            xRows1 = platformWidth / unitLength;
            yRows1 = platformLength / unitWidth;

            freeWidth = platformWidth - xRows1 * unitLength;
            freeLength = platformLength - yRows1 * unitWidth;
        } else {
            xRows1 = freeWidth / unitWidth;
            yRows1 = freeLength / unitLength;

            freeWidth = freeWidth - xRows1 * unitWidth;
            freeLength = freeLength - yRows1 * unitLength;
        }

        zRows = (int) (heightLimit / unitHeight);

        total1 = xRows1 * yRows1 * zRows;
        System.out.println("t1"+total1);
        //second iteration

        int xRows2 = 0;
        int yRows2 = 0;

        int total2 = 0;
        int total3 = 0;


        xRows2 = freeWidth / unitWidth;
        yRows2 = platformLength / unitLength;

        total2 = xRows2 * yRows2 * zRows;
        System.out.println(total2);
        if (total2 == 0) {
            int xRows3 = 0;
            int yRows3 = 0;

            xRows3 = platformWidth / unitLength;
            yRows3 = freeLength / unitWidth;

            total3 = xRows3 * yRows3 * zRows;
            System.out.println(total3);
        }

        int total = total1 + total2 + total3;

        if (cargoQuantity > total) {
            System.out.println(total);
            return total;
        } else {
            System.out.println(cargoQuantity);
            return cargoQuantity;
        }

    }

}
