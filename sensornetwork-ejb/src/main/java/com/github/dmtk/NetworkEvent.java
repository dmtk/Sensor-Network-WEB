package com.github.dmtk;
import java.util.Date;

public class NetworkEvent{
    private SensorNode source;
    private Date date;
    private String label;

    public SensorNode getSource() {
        return source;
    }

    public void setSource(SensorNode source) {
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
    
}
