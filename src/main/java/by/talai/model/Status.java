package by.talai.model;

import java.io.Serializable;

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
}
