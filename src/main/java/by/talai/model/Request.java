package by.talai.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Request implements Serializable {

    private int id;
    private Date fillingDate;
    private Charterer charterer;
    private AutomobileType requiredAutomobileType;
    private LoadingType requiredLoadingType;
    private Status executionStatus;

    private List<Delivery> deliveryList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
