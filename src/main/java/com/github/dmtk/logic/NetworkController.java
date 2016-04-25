package com.github.dmtk.logic;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.entity.Sensor;
import com.github.dmtk.utils.EventLabelTrigger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        try {
            String url = "coap://wsnet.me:5683";
            CoapClient client = new CoapClient(url + "/.well-known/core");
            CoapResponse response = client.get();
            String text = response.getResponseText();
            Pattern p = Pattern.compile("<([\\d\\w/]{1,})>");
            Matcher m = p.matcher(text);
            while (m.find()) {
                Sensor sensor1 = new Sensor();
                sensor1.setCoapURI(url + m.group(1));
                sensor1.setMeasuredQuantity("Temp");
                sensorService.save(sensor1);
            }
        } catch (Exception ex) {
            //TO DO Log4j
        }
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

                try {
                    double value = Double.parseDouble(response.getResponseText());
                    handle(sensor.getId(), value);
                } catch (NumberFormatException ex) {
                    
                        //TO DO Log4j
                        
                }

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
