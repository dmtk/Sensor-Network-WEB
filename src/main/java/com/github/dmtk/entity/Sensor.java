package com.github.dmtk.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "sensors")
public class Sensor implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name = "";
    private String measuredQuantity = "";
    private String coapURI="";
    
    @ManyToOne
    private SensorNode sensorNode;

    
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Sensor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    

    
    public String getMeasuredQuantity() {
        return measuredQuantity;
    }

    public void setMeasuredQuantity(String measuredQuantity) {
        this.measuredQuantity = measuredQuantity;
    }

    public String getCoapURI() {
        return coapURI;
    }

    public void setCoapURI(String coapURI) {
        this.coapURI = coapURI;
    }
    
    public SensorNode getSensorNode() {
        return sensorNode;
    }

    public void setSensorNode(SensorNode sensorNode) {
        this.sensorNode = sensorNode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Sensor)) {
            return false;
        }
        Sensor other = (Sensor) object;
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
