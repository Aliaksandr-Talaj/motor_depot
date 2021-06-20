package by.talai.service.dto;

import by.talai.model.*;
import by.talai.model.stock.Maintenance;
import by.talai.model.stock.Malfunction;

import java.util.List;
import java.util.Set;

public class AutomobileDto {

    private String id;
    private String brand;
    private String model;
    private String fuelType;
    private int carrying;
    private AutomobileType automobileType;
    private Set<Equipment> equipmentSet;
    private Set<LoadingType> loadingTypes;
    private int platformLength;
    private int platformWidth;
    private double cargoHeightLimit;
    private double cargoVolumeLimit;

    private List<Malfunction> malfunctions;
    private List<Maintenance> maintenanceList;
    private Status technicalStatus;
    private List<AutomobileAttachment> automobileAttachmentList;


}
