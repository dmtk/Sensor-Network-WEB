package com.github.dmtk.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
 @NamedQuery(name="NetworkEvent.findById",
                query="SELECT n FROM NetworkEvent n WHERE n.source.id = :id")
public class NetworkEvent implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private SensorNode source;
    private  @Temporal(TemporalType.TIMESTAMP) java.util.Date date;
    private String label;

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
    
    

}
