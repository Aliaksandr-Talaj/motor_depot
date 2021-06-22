package by.talai.service.dto;

import by.talai.model.AutomobileAttachment;
import by.talai.model.personnel.Driver;

import java.util.Objects;

public class DriverDto {

    private Driver driver;

    private AutomobileAttachment currentAttachment;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public AutomobileAttachment getCurrentAttachment() {
        return currentAttachment;
    }

    public void setCurrentAttachment(AutomobileAttachment currentAttachment) {
        this.currentAttachment = currentAttachment;
    }

    @Override
    public String toString() {
        return "DriverDto{" +
                "driver=" + driver +
                ", currentAttachment=" + currentAttachment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriverDto)) return false;
        DriverDto driverDto = (DriverDto) o;
        return getDriver().equals(driverDto.getDriver()) && Objects.equals(getCurrentAttachment(), driverDto.getCurrentAttachment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDriver(), getCurrentAttachment());
    }
}
