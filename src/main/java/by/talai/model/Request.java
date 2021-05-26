package by.talai.model;

import java.sql.Date;
import java.util.List;

public class Request {
    private int id;
    private Date fillingDate;
    private String charterer;

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

    public String getCharterer() {
        return charterer;
    }

    public void setCharterer(String charterer) {
        this.charterer = charterer;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", fillingDate=" + fillingDate +
                ", charterer='" + charterer + '\'' +
                ", deliveryList=" + deliveryList +
                '}';
    }
}
