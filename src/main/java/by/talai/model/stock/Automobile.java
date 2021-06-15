package by.talai.model.stock;

import by.talai.model.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Automobile implements Serializable {

    private String id;
    private String brand;
    private String model;
    private FuelType fuelType;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getCarrying() {
        return carrying;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    public AutomobileType getType() {
        return automobileType;
    }

    public void setType(AutomobileType automobileType) {
        this.automobileType = automobileType;
    }

    public Set<Equipment> getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(Set<Equipment> equipmentSet) {
        this.equipmentSet = equipmentSet;
    }

    public int getPlatformLength() {
        return platformLength;
    }

    public void setPlatformLength(int platformLength) {
        this.platformLength = platformLength;
    }

    public int getPlatformWidth() {
        return platformWidth;
    }

    public void setPlatformWidth(int platformWidth) {
        this.platformWidth = platformWidth;
    }

    public double getCargoHeightLimit() {
        return cargoHeightLimit;
    }

    public void setCargoHeightLimit(double cargoHeightLimit) {
        this.cargoHeightLimit = cargoHeightLimit;
    }

    public double getCargoVolumeLimit() {
        return cargoVolumeLimit;
    }

    public void setCargoVolumeLimit(double cargoVolumeLimit) {
        this.cargoVolumeLimit = cargoVolumeLimit;
    }

    public List<Malfunction> getMalfunctions() {
        return malfunctions;
    }

    public void setMalfunctions(List<Malfunction> malfunctions) {
        this.malfunctions = malfunctions;
    }

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(List<Maintenance> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }

    public Status getTechnicalStatus() {
        return technicalStatus;
    }

    public void setTechnicalStatus(Status technicalStatus) {
        this.technicalStatus = technicalStatus;
    }

    public Set<LoadingType> getLoadingTypes() {
        return loadingTypes;
    }

    public void setLoadingTypes(Set<LoadingType> loadingTypes) {
        this.loadingTypes = loadingTypes;
    }

    public AutomobileType getAutomobileType() {
        return automobileType;
    }

    public void setAutomobileType(AutomobileType automobileType) {
        this.automobileType = automobileType;
    }

    public List<AutomobileAttachment> getAutomobileAttachmentList() {
        return automobileAttachmentList;
    }

    public void setAutomobileAttachmentList(List<AutomobileAttachment> automobileAttachmentList) {
        this.automobileAttachmentList = automobileAttachmentList;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "id='" + id + '\'' +
                ",\n brand='" + brand + '\'' +
                ",\n model='" + model + '\'' +
                ",\n fuelType=" + fuelType +
                ",\n carrying=" + carrying +
                ",\n automobileType=" + automobileType +
                ",\n equipmentSet=" + equipmentSet +
                ",\n loadingTypes=" + loadingTypes +
                ",\n platformLength=" + platformLength +
                ",\n platformWidth=" + platformWidth +
                ",\n cargoHeightLimit=" + cargoHeightLimit +
                ",\n cargoVolumeLimit=" + cargoVolumeLimit +
                ",\n malfunctions=" + malfunctions +
                ",\n maintenanceList=" + maintenanceList +
                ",\n technicalStatus=" + technicalStatus +
                ",\n automobileAttachmentList=" + automobileAttachmentList +
                '}';
    }
}
