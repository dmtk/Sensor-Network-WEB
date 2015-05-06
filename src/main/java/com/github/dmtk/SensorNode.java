package com.github.dmtk;

public class SensorNode {
    private String name;
    private int id;
    
    SensorNode(){
        id=(int) Math.random()*100;
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
    
}
