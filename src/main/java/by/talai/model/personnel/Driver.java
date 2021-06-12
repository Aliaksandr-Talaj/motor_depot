package by.talai.model.personnel;

import by.talai.model.stock.Automobile;

public class Driver extends User {

    private Automobile automobile;

    public Driver() {
        super();
    }

    public Driver(String id, String name, String surname, String login, String password, Automobile automobile) {
        super(id, name, surname, login, password);
        this.automobile = automobile;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ",\n name='" + name + '\'' +
                ",\n surname='" + surname + '\'' +
                ",\n login='" + login + '\'' +
                ",\n password='" + password + '\'' +
                ",\n automobile=" + automobile +
                '}';
    }
}
