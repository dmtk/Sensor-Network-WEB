package com.github.dmtk;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class Events{

    public List getList(){
       
        
        List<NetworkEvent>ne =new LinkedList();
            for(int i=0;i<10;i++){
                NetworkEvent event = new NetworkEvent();
                event.setSource(new SensorNode());
                event.setDate(new Date());
                event.setLabel("Warning");
                ne.add(event);
            }
        
        return ne;
    }
}
