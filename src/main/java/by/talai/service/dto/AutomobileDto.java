package by.talai.service.dto;

import by.talai.model.*;
import by.talai.model.stock.*;

import java.util.List;
import java.util.Objects;
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

    @Override
    public String toString() {
        return "AutomobileDto{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", carrying=" + carrying +
                ", automobileType=" + automobileType +
                ", equipmentSet=" + equipmentSet +
                ", loadingTypes=" + loadingTypes +
                ", platformLength=" + platformLength +
                ", platformWidth=" + platformWidth +
                ", cargoHeightLimit=" + cargoHeightLimit +
                ", cargoVolumeLimit=" + cargoVolumeLimit +
                ", malfunctions=" + malfunctions +
                ", maintenanceList=" + maintenanceList +
                ", technicalStatus=" + technicalStatus +
                ", automobileAttachmentList=" + automobileAttachmentList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutomobileDto)) return false;
        AutomobileDto that = (AutomobileDto) o;
        return carrying == that.carrying && platformLength == that.platformLength
                && platformWidth == that.platformWidth
                && Double.compare(that.cargoHeightLimit, cargoHeightLimit) == 0
                && Double.compare(that.cargoVolumeLimit, cargoVolumeLimit) == 0
                && Objects.equals(id, that.id) && Objects.equals(brand, that.brand)
                && Objects.equals(model, that.model) && Objects.equals(fuelType, that.fuelType)
                && Objects.equals(automobileType, that.automobileType)
                && Objects.equals(equipmentSet, that.equipmentSet) && Objects.equals(loadingTypes, that.loadingTypes)
                && Objects.equals(malfunctions, that.malfunctions)
                && Objects.equals(maintenanceList, that.maintenanceList)
                && Objects.equals(technicalStatus, that.technicalStatus)
                && Objects.equals(automobileAttachmentList, that.automobileAttachmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, automobileType);
    }
}
