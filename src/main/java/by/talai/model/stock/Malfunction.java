package by.talai.model.stock;

import java.io.Serializable;
import java.sql.Date;

public class Malfunction implements Serializable {

    private String problem;
    private Date detectionTime;
    private Date fixTime;

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

    @Override
    public String toString() {
        return "Malfunction{" +
                "problem='" + problem + '\'' +
                ",\n detectionTime=" + detectionTime +
                ",\n fixTime=" + fixTime +
                '}';
    }
}
