package by.talai.model.personnel;

import by.talai.model.AutomobileAttachment;

import java.util.List;
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
}
