package by.talai.service.dto;

import by.talai.model.Charterer;
import by.talai.model.Status;
import by.talai.model.stock.AutomobileType;
import by.talai.model.stock.Equipment;
import by.talai.model.stock.LoadingType;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;


public class RequestDto implements Serializable {

    private String requestId;
    private Date fillingDate;
    private Charterer charterer;
    private AutomobileType requiredAutomobileType;
    private LoadingType requiredLoadingType;
    private Status executionStatus;
    private Set<Equipment> equipmentSet;

    @Override
    public String toString() {
        return "RequestDto{" +
                "requestId='" + requestId + '\'' +
                ", fillingDate=" + fillingDate +
                ", charterer=" + charterer +
                ", requiredAutomobileType=" + requiredAutomobileType +
                ", requiredLoadingType=" + requiredLoadingType +
                ", executionStatus=" + executionStatus +
                ", equipmentSet=" + equipmentSet +
                '}';
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestDto)) return false;
        RequestDto that = (RequestDto) o;
        return Objects.equals(getRequestId(),
                that.getRequestId()) && Objects.equals(getFillingDate(),
                that.getFillingDate()) && Objects.equals(getCharterer(),
                that.getCharterer()) && Objects.equals(getRequiredAutomobileType(),
                that.getRequiredAutomobileType()) && Objects.equals(getRequiredLoadingType(),
                that.getRequiredLoadingType()) && Objects.equals(getExecutionStatus(),
                that.getExecutionStatus()) && Objects.equals(getEquipmentSet(),
                that.getEquipmentSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestId());
    }
}
