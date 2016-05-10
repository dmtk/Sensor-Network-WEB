package com.github.dmtk.logic;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.entity.Sensor;
import com.github.dmtk.utils.EventLabelTrigger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Singleton
public class CoapEngine {

    private static final Map<Integer, CoapObserveRelation> activeCoAPConnection = new HashMap<Integer, CoapObserveRelation>();
    private final static Logger log = LogManager.getLogger(CoapEngine.class);
    
    @Autowired
    private SensorService sensorService;
    @Autowired
    private MeasurementService measurementService;

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
                String uri=m.group(1);
                Sensor sensor1 = new Sensor();
                sensor1.setCoapURI(url + uri);
                sensor1.setMeasuredQuantity(uri.substring(uri.lastIndexOf("/")));
                sensor1.setName(uri);
                sensorService.save(sensor1);
            }
        } catch (Exception ex) {
            log.error(ex);
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
                    save(value);
                } catch (NumberFormatException ex) {
                    log.error(sensor.getCoapURI() + " not a number, remove from active coap observe connections pull " + ex);
                    CoapObserveRelation relation = activeCoAPConnection.get(sensor.getId());
                    relation.reactiveCancel();
                    activeCoAPConnection.remove(sensor.getId());
                }

            }

            @Override
            public void onError() {
                log.error(sensor.getCoapURI() + " CoAP connection error");
            }

            public void save(double value) {

                Measurement m = new Measurement();
                m.setDate(new Date());
                m.setSensor(sensor);
                m.setValue(value);
                EventLabelTrigger.chooseLabel(m);
                measurementService.save(m);

            }

        }

        CoapHandlerImpl ch = new CoapHandlerImpl(sensor);
        CoapObserveRelation relation = client.observe(ch);
        activeCoAPConnection.put(sensor.getId(), relation);

    }

}
