package com.healthcare.publichealth;

import java.util.Date;

public class OutbreakData {
    private String disease;
    private String location;
    private Date reportedDate;
    private int cases;

    // Getters and setters
    public String getDisease() { return disease; }
    public void setDisease(String disease) { this.disease = disease; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Date getReportedDate() { return reportedDate; }
    public void setReportedDate(Date reportedDate) { this.reportedDate = reportedDate; }
    public int getCases() { return cases; }
    public void setCases(int cases) { this.cases = cases; }
}