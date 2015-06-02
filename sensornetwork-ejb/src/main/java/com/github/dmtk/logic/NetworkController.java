package com.github.dmtk.logic;

import com.github.dmtk.entity.NetworkEventFacadeLocal;
import com.github.dmtk.entity.NetworkEvent;
import com.github.dmtk.entity.SensorNodeFacadeLocal;
import com.github.dmtk.entity.SensorNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;


@Singleton
public class NetworkController {

    private static Map<Integer, SensorNode> activeNodePull = new HashMap<Integer, SensorNode>();

    @EJB
    private SensorNodeFacadeLocal sensorNodeFacade;
    @EJB
    private NetworkEventFacadeLocal networkEventFacade;
         
    public synchronized void handle(int id, int value) {

        if (!activeNodePull.containsKey(id)) {
            SensorNode sensorNode = new SensorNode(id);
            sensorNode.setLastMeasuredValue(value);
            activeNodePull.put(id, sensorNode);
            sensorNodeFacade.create(sensorNode);
            NetworkEvent n = new NetworkEvent();
            n.setSource(sensorNode);
            n.setDate(new Date());
            n.setValue(value);
            EventLabelTrigger.chooseLabel(n);
            networkEventFacade.create(n);
        } else {
            activeNodePull.get(id).setLastMeasuredValue(value);
            sensorNodeFacade.edit(activeNodePull.get(id));
            NetworkEvent n = new NetworkEvent();
            n.setDate(new Date());
            n.setSource(activeNodePull.get(id));
            n.setValue(value);
            EventLabelTrigger.chooseLabel(n);
            networkEventFacade.edit(n);
        }

    }
    
       
    private String data = "";
    
    @PostConstruct
    public void emulate() {
        readSensorNodes();//read nodes stored information from DB
        
        System.out.println("Comport starts");
        Thread myThready;
        myThready = new Thread(new Runnable() {
            @Override
            public void run() {
                
                while (true) {
                    int value = (int) Math.round(Math.random() * 100 - 20);
                    int id = (int) Math.round(Math.random() * 7+1);
                    data = "Sensor " + id + " Value " + value;
                    handle(id,value);
                    
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ComPort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        myThready.start();
    }
    
     public String getData() {
        return data;
    }

    public void readSensorNodes() {
        try {
            List<SensorNode> list = sensorNodeFacade.findAll();
            System.out.println(list.size());
            Iterator i = list.iterator();
            while (i.hasNext()) {
                SensorNode temp = (SensorNode) i.next();
                Integer id = temp.getId();
                activeNodePull.put(id, temp);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static List getActiveNodePull(){

        return new ArrayList<SensorNode>(activeNodePull.values());
    }
    
}
