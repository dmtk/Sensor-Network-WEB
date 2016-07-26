package com.github.dmtk.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


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

    //bi-directional many-to-one association to Measurement
    @OneToMany(mappedBy="sensor")
    private List<Measurement> mesurements;

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
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.measuredQuantity != null ? this.measuredQuantity.hashCode() : 0);
        hash = 29 * hash + (this.coapURI != null ? this.coapURI.hashCode() : 0);
        hash = 29 * hash + (this.sensorNode != null ? this.sensorNode.hashCode() : 0);
        hash = 29 * hash + (this.mesurements != null ? this.mesurements.hashCode() : 0);
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
        final Sensor other = (Sensor) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.measuredQuantity == null) ? (other.measuredQuantity != null) : !this.measuredQuantity.equals(other.measuredQuantity)) {
            return false;
        }
        if ((this.coapURI == null) ? (other.coapURI != null) : !this.coapURI.equals(other.coapURI)) {
            return false;
        }
        if (this.sensorNode != other.sensorNode && (this.sensorNode == null || !this.sensorNode.equals(other.sensorNode))) {
            return false;
        }
        if (this.mesurements != other.mesurements && (this.mesurements == null || !this.mesurements.equals(other.mesurements))) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Sensor{" + "id=" + id + ", name=" + name + ", measuredQuantity=" + measuredQuantity + ", coapURI=" + coapURI + ", sensorNode=" + sensorNode + ", mesurements=" + mesurements + '}';
    }

    

}
