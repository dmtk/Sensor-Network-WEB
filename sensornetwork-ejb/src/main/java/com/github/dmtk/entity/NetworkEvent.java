package com.github.dmtk.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//select AVG(value) from networkevent where date > '2016-01-19 01:20:00' AND date < '2016-01-19 01:21:00';

@Entity
@NamedQueries({
    @NamedQuery(name = "NetworkEvent.findById",
            query = "SELECT n FROM NetworkEvent n WHERE n.source.id = :id"),
    @NamedQuery(name = "NetworkEvent.findByDate",
            query = "SELECT n FROM NetworkEvent n ORDER BY n.date DESC"),
    @NamedQuery(name = "NetworkEvent.getAvg",
            query = "SELECT AVG(n.value) FROM NetworkEvent n where n.date > :start AND n.date < :end AND n.source.id = :id")})

public class NetworkEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private SensorNode source;
    private @Temporal(TemporalType.TIMESTAMP)
    java.util.Date date;
    private String label;
    private double value;

    public NetworkEvent() {

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

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

}
