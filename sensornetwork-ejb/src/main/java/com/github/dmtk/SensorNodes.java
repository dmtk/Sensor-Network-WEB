package com.github.dmtk;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class SensorNodes{

    public List getList(){
       
        List<SensorNode>sn =new LinkedList();
            for(int i=0;i<10;i++){
                 SensorNode node = new SensorNode();
                sn.add(node);
            }
        
        return sn;
    }
}
