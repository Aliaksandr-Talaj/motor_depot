package by.talai.model;

import java.io.Serializable;
import java.util.Objects;

public class Status implements Serializable {

    private int id;
    private String status;

    public Status() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ",\n status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status)) return false;
        Status status1 = (Status) o;
        return getId() == status1.getId() && getStatus().equals(status1.getStatus());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
