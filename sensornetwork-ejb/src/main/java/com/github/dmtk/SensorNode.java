package com.github.dmtk;

import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class SensorNode implements Serializable {

    private Long id;
    private String name;
    private int number;
    private int parentid = 1;
    private String measuredQuantity = "Temperature";
    private String type = "Sensor";
    private double value = 28.5;
    private String note = "Warning";

    SensorNode() {
        number = (int) Math.round(Math.random() * 100);
        name = String.valueOf(number);
    }

    SensorNode(String name, int number) {
        this.name = name;
        this.number =number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getMeasuredQuantity() {
        return measuredQuantity;
    }

    public void setMeasuredQuantity(String measuredQuantity) {
        this.measuredQuantity = measuredQuantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getNumber() {
        return number;
    }

    public void setId(int number) {
        this.number =  number;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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

}
