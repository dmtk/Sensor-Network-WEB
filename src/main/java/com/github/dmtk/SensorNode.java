package com.github.dmtk;

public class SensorNode {
    private String name;
    private int id;
    private int parentid=1;
    private String measuredQuantity="Temperature";
    private String type = "Sensor";
    private double value=28.5;
    
    SensorNode(){
        id=(int) Math.round(Math.random()*100);
        name=String.valueOf(id);
    }
    
    SensorNode(String name, int id){
        this.name=name;
        this.id=id;
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
    
}
