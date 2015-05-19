package com.github.dmtk;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SensorNode implements Serializable {

    @Id
    private Integer id;
    private String name = "Name";
    private Long parentid;
    private String measuredQuantity = "Temperature";
    private int adcValue = 10; //quantized value received directly from analog-to-digital converter
    private double value = 28.5;
    private Location loc;
    private String note = "Warning";
    private String type = "SENSOR";

    public void setId(Integer id) {
        this.id = id;
    }

    public SensorNode() {

    }

    public SensorNode(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.setId((Integer) id);
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getMeasuredQuantity() {
        return measuredQuantity;
    }

    public void setMeasuredQuantity(String measuredQuantity) {
        this.measuredQuantity = measuredQuantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof SensorNode)) {
            return false;
        }
        SensorNode other = (SensorNode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.dmtk.NewEntity[ id=" + id + " ]";
    }

    public int getAdcValue() {
        return adcValue;
    }

    public void setAdcValue(int adcValue) {
        this.adcValue = adcValue;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
