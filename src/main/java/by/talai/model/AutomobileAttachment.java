package by.talai.model;

import by.talai.model.personnel.Driver;
import by.talai.model.stock.Automobile;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class AutomobileAttachment implements Serializable {

    private int id;
    private Automobile Automobile;
    private Driver Driver;

    private Date dateOfAttachment;
    private Date dateOfDetachment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public by.talai.model.stock.Automobile getAutomobile() {
        return Automobile;
    }

    public void setAutomobile(by.talai.model.stock.Automobile automobile) {
        Automobile = automobile;
    }

    public by.talai.model.personnel.Driver getDriver() {
        return Driver;
    }

    public void setDriver(by.talai.model.personnel.Driver driver) {
        Driver = driver;
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
                "id=" + id +
                ",\n Automobile=" + Automobile +
                ",\n Driver=" + Driver +
                ",\n dateOfAttachment=" + dateOfAttachment +
                ",\n dateOfDetachment=" + dateOfDetachment +
                '}';
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
