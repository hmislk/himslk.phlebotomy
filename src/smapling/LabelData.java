/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smapling;

import java.util.Date;

/**
 *
 * @author buddh
 */
public class LabelData {

    private Long sampleId;
    private String sampleIdString;
    private String patientName;
    private String testNames;
    private Date sampleDate;

    public Long getSampleId() {
        return sampleId;
    }

    public void setSampleId(Long sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleIdString() {
        return sampleIdString;
    }

    public void setSampleIdString(String sampleIdString) {
        this.sampleIdString = sampleIdString;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTestNames() {
        return testNames;
    }

    public void setTestNames(String testNames) {
        this.testNames = testNames;
    }

    public Date getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(Date sampleDate) {
        this.sampleDate = sampleDate;
    }
    
    
    
}
