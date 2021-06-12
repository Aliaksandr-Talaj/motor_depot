package by.talai.model.personnel;

public class Dispatcher extends User {

    public Dispatcher() {
        super();
    }

    public Dispatcher(String id, String name, String surname, String login, String password) {
        super(id, name, surname, login, password);
    }

    @Override
    public String toString() {
        return "Dispatcher{" +
                "id='" + id + '\'' +
                ",\n name='" + name + '\'' +
                ",\n surname='" + surname + '\'' +
                ",\n login='" + login + '\'' +
                ",\n password='" + password + '\'' +
                '}';
    }
}
