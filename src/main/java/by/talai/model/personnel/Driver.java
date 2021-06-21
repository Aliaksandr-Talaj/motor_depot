package by.talai.model.personnel;

import by.talai.model.AutomobileAttachment;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Driver extends User {

    private List<AutomobileAttachment> automobileAttachments;

    public Driver() {
        super();
    }

    public Driver(int id, String name, String surname, String login,
                  String password, List<AutomobileAttachment> automobileAttachments) {
        super(id, name, surname, login, password);
        this.automobileAttachments = automobileAttachments;
    }

    public List<AutomobileAttachment> getAutomobileAttachments() {
        return automobileAttachments;
    }

    public void setAutomobileAttachments(List<AutomobileAttachment> automobileAttachments) {
        this.automobileAttachments = automobileAttachments;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "automobileAttachment=" + automobileAttachments +
                ",\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n surname='" + surname + '\'' +
                ",\n login='" + login + '\'' +
                ",\n password='" + password + '\'' +
                ",\n role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(getAutomobileAttachments(), driver.getAutomobileAttachments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAutomobileAttachments());
    }
}
