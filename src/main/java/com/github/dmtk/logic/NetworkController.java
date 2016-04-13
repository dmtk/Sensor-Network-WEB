package com.github.dmtk.logic;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.entity.Sensor;
import com.github.dmtk.utils.EventLabelTrigger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Singleton

public class NetworkController {

    private static Map<Integer, Sensor> activeNodePull = new HashMap<Integer, Sensor>();

    @Autowired
    private SensorService sensorNodeService;
    @Autowired
    private MeasurementService networkEventService;

    public void handle(int id, double value) {

        if (!activeNodePull.containsKey(id)) {
            Sensor sensorNode = new Sensor(id);

            activeNodePull.put(id, sensorNode);
            sensorNodeService.save(sensorNode);
            Measurement n = new Measurement();
            n.setSource(id);
            n.setDate(new Date());
            n.setValue(value);
            EventLabelTrigger.chooseLabel(n);
            networkEventService.save(n);
        } else {

            sensorNodeService.save(activeNodePull.get(id));
            Measurement n = new Measurement();
            n.setDate(new Date());
            n.setSource(id);
            n.setValue(value);
            EventLabelTrigger.chooseLabel(n);
            networkEventService.save(n);
        }

    }

    private String data = "Test";//realtime received data

    boolean coapIsConnected = false;

    @PostConstruct
    public void work() {
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    handle(1,Math.random()*100);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();

    }

    public void addCoAPConnection(String addr, boolean observable, final int id) {
        CoapClient client = new CoapClient(addr);
        if (observable) {
            CoapObserveRelation relation = client.observe(new CoapHandler() {
                @Override
                public void onLoad(CoapResponse response) {

                    double value = Double.parseDouble(response.getResponseText());
                    handle(id, value);
                    data = "Sensor " + id + " Value " + value;

                }

                @Override
                public void onError() {
                    data = "CoAP connection is failed";
                    coapIsConnected = false;
                }
            });
            coapIsConnected = true;
        } else {
            double value = Double.parseDouble(client.get().getResponseText());
            handle(id, value);
            data = "Sensor " + id + " Value " + value;
        }
    }

    private void initCoapConnection(String uri) {
        CoapClient client = new CoapClient("coap://wsnet.me:5683/SensorNode4/Temperature");
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

    public void readSensorNodes() {
        try {
            List<Sensor> list = sensorNodeService.getList();

            Iterator i = list.iterator();
            while (i.hasNext()) {
                Sensor temp = (Sensor) i.next();
                Integer id = temp.getId();
                activeNodePull.put(id, temp);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static List getActiveNodePull() {

        return new ArrayList<Sensor>(activeNodePull.values());
    }

}
