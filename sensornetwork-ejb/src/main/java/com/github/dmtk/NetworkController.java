package com.github.dmtk;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
            sensorNode.setValue(value);
            activeNodePull.put(id, sensorNode);
            sensorNodeFacade.create(sensorNode);
            NetworkEvent n = new NetworkEvent();
            n.setSource(sensorNode);
            n.setDate(new Date());
            EventLabelTrigger.chooseLabel(n);
            networkEventFacade.create(n);
        } else {
            activeNodePull.get(id).setValue(value);
            sensorNodeFacade.edit(activeNodePull.get(id));
            NetworkEvent n = new NetworkEvent();
            n.setDate(new Date());
            n.setSource(activeNodePull.get(id));
            EventLabelTrigger.chooseLabel(n);
            networkEventFacade.edit(n);
        }

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

    public static List getActiveNodePull() {

        return new ArrayList<SensorNode>(activeNodePull.values());
    }

}
