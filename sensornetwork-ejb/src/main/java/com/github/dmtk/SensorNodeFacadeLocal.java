/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dmtk;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
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
