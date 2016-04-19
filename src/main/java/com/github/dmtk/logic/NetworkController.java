package com.github.dmtk.logic;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.entity.Sensor;
import com.github.dmtk.utils.EventLabelTrigger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static Map<Integer, Sensor> activeSensorPull = new HashMap<Integer, Sensor>();

    @Autowired
    private SensorService sensorService;
    @Autowired
    private MeasurementService measurementService;

    public void handle(int id, double value) {

        Measurement n = new Measurement();
        n.setDate(new Date());
        n.setSensor(id);
        n.setValue(value);
        EventLabelTrigger.chooseLabel(n);
        measurementService.save(n);
        
    }

    @PostConstruct
    public void startListeners() {

        Sensor sensor1 = new Sensor();
        sensor1.setId(1);
        sensor1.setCoapURI("coap://wsnet.me:5683/SensorNode4/Temperature");
        sensor1.setMeasuredQuantity("Temp");
        sensor1.setName("DS18B20");

        sensorService.save(sensor1);
        List<Sensor> list = sensorService.getList();
        for (Sensor sensor : list) {
            addCoAPConnection(sensor);
        }

    }

    public void addCoAPConnection(Sensor sensor) {
        CoapClient client = new CoapClient(sensor.getCoapURI());
        class CoapHandlerImpl implements CoapHandler {

            private final Sensor sensor;

            public CoapHandlerImpl(Sensor sensor) {
                this.sensor = sensor;
            }

            @Override
            public void onLoad(CoapResponse response) {

                double value = Double.parseDouble(response.getResponseText());
                handle(sensor.getId(), value);

            }

            @Override
            public void onError() {
                activeSensorPull.remove(sensor.getId());
            }

        };

        CoapHandlerImpl ch = new CoapHandlerImpl(sensor);

        CoapObserveRelation relation = client.observe(ch);

    }

    public static List getActiveNodePull() {

        return new ArrayList<Sensor>(activeSensorPull.values());
    }

}
