package by.talai.model.stock;

import java.io.Serializable;

public class TechnicalStatus implements Serializable {

    private int id;

    private String status;

    public TechnicalStatus() {

    }

    public TechnicalStatus(int id, String status) {
        this.id = id;
        this.status = status;
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
        return "TechnicalStatus{" +
                "id=" + id +
                ",\n status='" + status + '\'' +
                '}';
    }
}
