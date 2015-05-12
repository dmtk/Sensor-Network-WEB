package com.github.dmtk;

import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class SensorNodes{

    public List getList(){
       
        return NetworkController.getActiveNodePull();
    }
}
