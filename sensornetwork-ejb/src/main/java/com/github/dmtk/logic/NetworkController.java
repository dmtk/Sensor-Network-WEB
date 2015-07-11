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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;

@Singleton
public class NetworkController {

    private static Map<Integer, SensorNode> activeNodePull = new HashMap<Integer, SensorNode>();

    @EJB
    private SensorNodeFacadeLocal sensorNodeFacade;
    @EJB
    private NetworkEventFacadeLocal networkEventFacade;

    public synchronized void handle(int id, double value) {

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

    private String data = "";//realtime received(emulated) data

    boolean coapIsConnected = false;

    @PostConstruct
    public void work() {
        readSensorNodes();//read nodes stored information from DB

        initCoapConnection();
        
        Thread checker = new Thread(new Runnable() {
            public void run() {
                while(true){
                    if(!coapIsConnected){
                        initCoapConnection();
                    }else{
                        try {
                            Thread.sleep(600000);//10 min
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        checker.start();

    }
    
    private void initCoapConnection(){
        CoapClient client = new CoapClient("coap://wsnet.me:5683/SensorNode1");
        CoapObserveRelation relation = client.observe(new CoapHandler() {
            @Override
            public void onLoad(CoapResponse response) {

                int id = 1;
                double value = Double.parseDouble(response.getResponseText());
                handle(id, value);
                data = "Sensor " + 1 + " Value " + value;

            }

            @Override
            public void onError() {
                data = "CoAP connection is failed";
                coapIsConnected = false;
            }
        });
        coapIsConnected = true;
    }

    public String getData() {
        return data;
    }

    public void reconnect() {

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
