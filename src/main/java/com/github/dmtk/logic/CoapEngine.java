package com.github.dmtk.logic;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.entity.Sensor;
import com.github.dmtk.entity.SensorNode;
import com.github.dmtk.utils.EventLabelTrigger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import org.apache.commons.lang.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.WebLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

@Service
@Singleton
public class CoapEngine {

    private final List<Sensor> activeCoAPConnections = new ArrayList<Sensor>();
    private final List<Sensor> unactiveCoAPConnections = new ArrayList<Sensor>();
    private final static Logger log = LogManager.getLogger(CoapEngine.class);
    
    @Autowired
    private SensorService sensorService;
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private SensorNodeService sensorNodeService;

    public List getActiveConnection(){
        return activeCoAPConnections;
    }
    public List getUnactiveConnection(){
        return unactiveCoAPConnections;
    }
    @PostConstruct
    public void startUp() {

        String url = "coap://wsnet.me:5683";
        try {
            parseCoapResources(url);
            startCoapObserveRelations();
        } catch (Exception e) {
            log.error(e);
        }

    }

    public void startCoapObserveRelations() {
        Thread t = new Thread(new Runnable() {

            public void run() {

                try {
                    List<Sensor> list = sensorService.getList();
                    createConnections(list);

                    while (true) {
                        try {
                            //sleep 5 minutes
                            Thread.sleep(300000);
                            if (!unactiveCoAPConnections.isEmpty()) {
                                createConnections(unactiveCoAPConnections);
                            }
                        } catch (InterruptedException ex) {
                            log.error(ex);
                        }
                    }
                } catch (Exception ex) {
                    log.error(ex.getStackTrace());
                }
            }

            private void createConnections(Collection<Sensor> collection) {
                for (Sensor sensor : collection) {

                    addCoAPConnection(sensor);
                }
            }

        });
        t.start();
    }

    public void parseCoapResources(String url) {

        CoapClient client = new CoapClient(url + "/.well-known/core");

        Set<WebLink> links = client.discover();

        for (WebLink w : links) {

            String uri = w.getURI();//m.group(1);
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
                double value = Double.parseDouble(str);
                save(value);

            }

            @Override
            public void onError() {
                log.error(sensor.getCoapURI() + " CoAP connection error");
                unactiveCoAPConnections.add(sensor);
                activeCoAPConnections.remove(sensor);
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
        CoapResponse resp = client.get();
        String str = resp.getResponseText();
        if (!str.isEmpty() && NumberUtils.isNumber(str)) {
            CoapObserveRelation relation = client.observe(ch);
            activeCoAPConnections.add(sensor);
        } else {
            log.error(sensor.getCoapURI() + " response does not contain a number");

        }

    }

    
}
