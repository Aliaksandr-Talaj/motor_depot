package by.talai.model;

import by.talai.model.personnel.Driver;
import by.talai.model.stock.Automobile;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class AutomobileAttachment implements Serializable {

    private int id;
    private Automobile automobile;
    private Driver driver;

    private Date dateOfAttachment;
    private Date dateOfDetachment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public by.talai.model.stock.Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(by.talai.model.stock.Automobile automobile) {
        this.automobile = automobile;
    }

    public by.talai.model.personnel.Driver getDriver() {
        return driver;
    }

    public void setDriver(by.talai.model.personnel.Driver driver) {
        this.driver = driver;
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

        StringBuilder sb = new StringBuilder();
        sb.append("AutomobileAttachment{" +
                "id=");
        sb.append(id);
        sb.append(",\n automobileId=");
        if (automobile != null) {
            sb.append(automobile.getId());
        } else {
            sb.append("null");
        }
        sb.append(",\n driverId=");
        if (driver != null) {
            sb.append(driver.getId());
        } else {
            sb.append("null");
        }
        sb.append(",\n dateOfAttachment=");
        sb.append(dateOfAttachment);
        sb.append(",\n dateOfDetachment=");
        sb.append(dateOfDetachment);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutomobileAttachment)) return false;
        AutomobileAttachment that = (AutomobileAttachment) o;
        return getId() == that.getId() && getAutomobile().equals(that.getAutomobile())
                && getDriver().equals(that.getDriver()) && getDateOfAttachment().equals(that.getDateOfAttachment())
                && Objects.equals(getDateOfDetachment(), that.getDateOfDetachment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAutomobile(), getDriver(), getDateOfAttachment(), getDateOfDetachment());
    }
}
