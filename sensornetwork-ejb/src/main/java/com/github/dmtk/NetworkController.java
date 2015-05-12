package com.github.dmtk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkController {

    private static Map<Integer,SensorNode> activeNodePull=new HashMap<Integer,SensorNode>();
    private static List<NetworkEvent> events = new ArrayList<NetworkEvent>();
    public static void handle(int node, int value){
    
        
        if(!activeNodePull.containsKey(node)){
            SensorNode sensorNode=new SensorNode(node);
            sensorNode.setValue(value);
            activeNodePull.put(node,sensorNode);
            NetworkEvent n = new NetworkEvent();
            n.setSource(sensorNode);
            events.add(n);
            
        }else{
            
            activeNodePull.get(node).setValue(value);
            NetworkEvent n = new NetworkEvent();
            n.setSource(activeNodePull.get(node));
            events.add(n);
        }
      
    }
    
    public static List getActiveNodePull(){
        
        return new ArrayList<SensorNode>(activeNodePull.values());
    }

    static List getEvents() {
        return events;
    }
    
    
}
