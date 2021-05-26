package by.talai.model.personnel;

import by.talai.model.stock.Automobile;

public class Driver extends User {

    private Automobile automobile;

    public Driver() {
        super();
    }

    public Driver(String id, String name, String surname, Automobile automobile) {
        super(id, name, surname);
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
                "automobile=" + automobile +
                '}';
    }
}
