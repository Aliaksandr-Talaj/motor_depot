package by.talai.model.stock;

import java.io.Serializable;
import java.sql.Date;

public class Malfunction implements Serializable {

    private int id;
    private String problem;
    private Date detectionTime;
    private Date fixTime;
    private Automobile automobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Date getDetectionTime() {
        return detectionTime;
    }

    public void setDetectionTime(Date detectionTime) {
        this.detectionTime = detectionTime;
    }

    public Date getFixTime() {
        return fixTime;
    }

    public void setFixTime(Date fixTime) {
        this.fixTime = fixTime;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    @Override
    public String toString() {
        return "Malfunction{" +
                "id=" + id +
                ",\n problem='" + problem + '\'' +
                ",\n detectionTime=" + detectionTime +
                ",\n fixTime=" + fixTime +
                ",\n automobile='" + automobile + '\'' +
                '}';
    }
}
