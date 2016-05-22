package com.github.dmtk.logic;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.entity.Sensor;
import com.github.dmtk.entity.SensorNode;
import com.github.dmtk.utils.EventLabelTrigger;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import org.apache.commons.lang.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

@Service
@Singleton
public class CoapEngine {

    private static final Map<Integer, CoapObserveRelation> activeCoAPConnection = new HashMap<Integer, CoapObserveRelation>();
    private static final Map<Integer, CoapObserveRelation> unactiveCoAPConnection = new HashMap<Integer, CoapObserveRelation>();
    private final static Logger log = LogManager.getLogger(CoapEngine.class);

    @Autowired
    private SensorService sensorService;
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private SensorNodeService sensorNodeService;

    @PostConstruct
    public void startUp() {

        String url = "coap://wsnet.me:5683";
        parseCoapResources(url);
        startCoapObserveRelations();

    }

    public void startCoapObserveRelations() {
        Thread t = new Thread(new Runnable() {

            public void run() {
                List<Sensor> list = sensorService.getList();
                for (Sensor sensor : list) {

                    addCoAPConnection(sensor);
                }
            }
        });
        t.start();
    }

    public void parseCoapResources(String url) {

        CoapClient client = new CoapClient(url + "/.well-known/core");
        CoapResponse response = client.get();
        String text = response.getResponseText();
        Pattern p = Pattern.compile("<([\\d\\w/]{1,})>");
        Matcher m = p.matcher(text);

        while (m.find()) {
            String uri = m.group(1);
            Sensor sensor1 = new Sensor();
            sensor1.setCoapURI(url + uri);

            String quantityStr = uri.substring(uri.lastIndexOf("/") + 1);

            if (quantityStr.startsWith("SensorNode")) {
                SensorNode sn = new SensorNode();
                sn.setName(quantityStr);
                sensorNodeService.save(sn);
            } else {
                sensor1.setMeasuredQuantity(quantityStr);
                sensor1.setName(uri.substring(1));
                sensorService.save(sensor1);
            }

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

                String str = response.getResponseText();

                if (!str.isEmpty() && NumberUtils.isNumber(str)) {
                    double value = Double.parseDouble(str);
                    save(value);
                } else {
                    log.error(sensor.getCoapURI() + " not a number, remove from active coap observe connections pull ");
                    CoapObserveRelation relation = activeCoAPConnection.get(sensor.getId());
                    relation.proactiveCancel();
                    activeCoAPConnection.remove(sensor.getId());
                }
            }

            @Override
            public void onError() {
                log.error(sensor.getCoapURI() + " CoAP connection error");
                CoapObserveRelation relation = activeCoAPConnection.get(sensor.getId());
                relation.proactiveCancel();
                unactiveCoAPConnection.put(sensor.getId(), relation);
                activeCoAPConnection.remove(sensor.getId());
            }

            public void save(double value) {

                Measurement m = new Measurement();
                m.setDate(new Date());
                m.setSensor(sensor);
                m.setValue(value);
                EventLabelTrigger.chooseLabel(m);
                try {
                    measurementService.save(m);
                } catch (CannotCreateTransactionException ex) {
                    log.error("Cannot create transaction " + sensor.getName());
                }

            }
        }

        CoapHandlerImpl ch = new CoapHandlerImpl(sensor);
        CoapObserveRelation relation = client.observe(ch);
        activeCoAPConnection.put(sensor.getId(), relation);

    }

    public void restartUnactiveCoapConnection() {
        Iterator it = unactiveCoAPConnection.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            ((CoapObserveRelation) entry.getValue()).reregister();
        }
    }
}
