package com.github.dmtk;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class NetworkEvent implements Serializable {

    @Id
    private Long id;
    private SensorNode source;
    private  @Temporal(TemporalType.DATE) java.util.Date date;
    private String label;

    public NetworkEvent() {
        date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
