package by.talai.service.dto;

import by.talai.model.Request;
import by.talai.model.stock.Equipment;

import java.util.Objects;
import java.util.Set;

public class RequestDto {

    private Request request;

    private Set<Equipment> equipmentSet;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Set<Equipment> getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(Set<Equipment> equipmentSet) {
        this.equipmentSet = equipmentSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestDto)) return false;
        RequestDto that = (RequestDto) o;
        return getRequest().equals(that.getRequest()) && Objects.equals(equipmentSet, that.equipmentSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequest(), equipmentSet);
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "request=" + request +
                ", equipmentSet=" + equipmentSet +
                '}';
    }
}
