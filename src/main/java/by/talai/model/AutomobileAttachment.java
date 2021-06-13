package by.talai.model;

import java.io.Serializable;
import java.sql.Date;

public class AutomobileAttachment implements Serializable {

    private String  AutomobileId;
    private int DriverId;

    private Date dateOfAttachment;
    private Date dateOfDetachment;

    public String getAutomobileId() {
        return AutomobileId;
    }

    public void setAutomobileId(String automobileId) {
        AutomobileId = automobileId;
    }

    public int getDriverId() {
        return DriverId;
    }

    public void setDriverId(int driverId) {
        DriverId = driverId;
    }

    public Date getDateOfAttachment() {
        return dateOfAttachment;
    }

    public void setDateOfAttachment(Date dateOfAttachment) {
        this.dateOfAttachment = dateOfAttachment;
    }

    public Date getDateOfDetachment() {
        return dateOfDetachment;
    }

    public void setDateOfDetachment(Date dateOfDetachment) {
        this.dateOfDetachment = dateOfDetachment;
    }

    @Override
    public String toString() {
        return "AutomobileAttachment{" +
                "AutomobileId='" + AutomobileId + '\'' +
                ",\n DriverId=" + DriverId +
                ",\n dateOfAttachment=" + dateOfAttachment +
                ",\n dateOfDetachment=" + dateOfDetachment +
                '}';
    }
}
