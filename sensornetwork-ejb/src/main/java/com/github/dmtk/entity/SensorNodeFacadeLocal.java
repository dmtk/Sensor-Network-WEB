package com.github.dmtk.entity;

import java.util.List;
import javax.ejb.Local;

@Local
public interface SensorNodeFacadeLocal {

    void create(SensorNode sensorNode);

    void edit(SensorNode sensorNode);

    void remove(SensorNode sensorNode);

    SensorNode find(Object id);

    List<SensorNode> findAll();

    List<SensorNode> findRange(int[] range);

    int count();
    
}
