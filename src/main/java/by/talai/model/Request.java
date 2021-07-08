package by.talai.model;

import by.talai.model.stock.AutomobileType;
import by.talai.model.stock.Equipment;
import by.talai.model.stock.LoadingType;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Request implements Serializable {

    private String id;
    private Date fillingDate;
    private Charterer charterer;
    private AutomobileType requiredAutomobileType;
    private LoadingType requiredLoadingType;
    private Status executionStatus;
    private Set<Equipment> equipmentSet;

    private List<Delivery> deliveryList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFillingDate() {
        return fillingDate;
    }

    public void setFillingDate(Date fillingDate) {
        this.fillingDate = fillingDate;
    }

    public Charterer getCharterer() {
        return charterer;
    }

    public void setCharterer(Charterer charterer) {
        this.charterer = charterer;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public AutomobileType getRequiredAutomobileType() {
        return requiredAutomobileType;
    }

    public void setRequiredAutomobileType(AutomobileType requiredAutomobileType) {
        this.requiredAutomobileType = requiredAutomobileType;
    }

    public LoadingType getRequiredLoadingType() {
        return requiredLoadingType;
    }

    public void setRequiredLoadingType(LoadingType requiredLoadingType) {
        this.requiredLoadingType = requiredLoadingType;
    }

    public Status getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(Status executionStatus) {
        this.executionStatus = executionStatus;
    }

    public Set<Equipment> getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(Set<Equipment> equipmentSet) {
        this.equipmentSet = equipmentSet;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ",\n fillingDate=" + fillingDate +
                ",\n charterer=" + charterer +
                ",\n requiredAutomobileType=" + requiredAutomobileType +
                ",\n requiredLoadingType=" + requiredLoadingType +
                ",\n executionStatus=" + executionStatus +
                ",\n deliveryList=" + deliveryList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return getId().equals(request.getId()) && getFillingDate().equals(request.getFillingDate())
                && getCharterer().equals(request.getCharterer())
                && Objects.equals(getRequiredAutomobileType(), request.getRequiredAutomobileType())
                && Objects.equals(getRequiredLoadingType(), request.getRequiredLoadingType())
                && getExecutionStatus().equals(request.getExecutionStatus())
                && getDeliveryList().equals(request.getDeliveryList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
