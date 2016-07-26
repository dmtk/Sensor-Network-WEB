package com.github.dmtk.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "measurements")
@NamedQueries({
    @NamedQuery(name = "findBySensorId", query = "SELECT m FROM Measurement m WHERE m.sensor.id = :sensorId"),
    @NamedQuery(name = "findBySensorIdOrderByDate", query = "SELECT m FROM Measurement m WHERE m.sensor.id = :sensorId ORDER BY m.date DESC"),
    @NamedQuery(name = "getOrderByDate", query = "SELECT m FROM Measurement m ORDER BY m.date DESC"),
    @NamedQuery(name = "findBySensorNameOrderByDate", query = "SELECT m FROM Measurement m WHERE m.sensor.name = :sensorName ORDER BY m.date DESC"),
    @NamedQuery(name = "getCount", query = "SELECT COUNT(m) FROM Measurement m"),})

public class Measurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Sensor sensor;

    private @Temporal(TemporalType.TIMESTAMP)
    java.util.Date date;
    private Double value;
    private String label;

    public Measurement() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 71 * hash + (this.sensor != null ? this.sensor.hashCode() : 0);
        hash = 71 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 71 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash = 71 * hash + (this.label != null ? this.label.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Measurement other = (Measurement) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.sensor != other.sensor && (this.sensor == null || !this.sensor.equals(other.sensor))) {
            return false;
        }
        if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
            return false;
        }
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Measurement{" + "id=" + id + ", sensor=" + sensor + ", date=" + date + ", value=" + value + ", label=" + label + '}';
    }
    
    

}
