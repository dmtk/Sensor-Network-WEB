package com.github.dmtk.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "sensornodes")

public class SensorNode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name="";
    
    //bi-directional many-to-one association to Sensor 
    @OneToMany(mappedBy="sensorNode")
    private List<Sensor> sensors;

    public SensorNode() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Sensor> getSensor() {
        return sensors;
    }

    public void setSensor(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 37 * hash + (this.sensors != null ? this.sensors.hashCode() : 0);
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
        final SensorNode other = (SensorNode) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.sensors != other.sensors && (this.sensors == null || !this.sensors.equals(other.sensors))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SensorNode{" + "id=" + id + ", name=" + name + ", sensors=" + sensors + '}';
    }
    

 
}
