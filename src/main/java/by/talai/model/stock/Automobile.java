package by.talai.model.stock;

import java.util.List;
import java.util.Set;

public class Automobile {

    private String id;
    private String brand;
    private String model;
    private String fuel;
    private int carrying;
    private String type;
    private Set<String> equipmentSet;
    private int platformLength;
    private int platformWidth;
    private double cargoHeightLimit;
    private double cargoVolumeLimit;

    private List<Malfunction> malfunctions;
    private TechnicalStatus status;

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

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getCarrying() {
        return carrying;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(Set<String> equipmentSet) {
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

    private List<Maintenance> maintenanceList;

    public TechnicalStatus getStatus() {
        return status;
    }

    public void setStatus(TechnicalStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "id='" + id + '\'' +
                ",\n brand='" + brand + '\'' +
                ",\n model='" + model + '\'' +
                ",\n fuel='" + fuel + '\'' +
                ",\n carrying=" + carrying +
                ",\n type='" + type + '\'' +
                ",\n equipmentSet=" + equipmentSet +
                ",\n platformLength=" + platformLength +
                ",\n platformWidth=" + platformWidth +
                ",\n cargoHeightLimit=" + cargoHeightLimit +
                ",\n cargoVolumeLimit=" + cargoVolumeLimit +
                ",\n malfunctions=" + malfunctions +
                ",\n maintenanceList=" + maintenanceList +
                '}';
    }
}
