package com.github.dmtk.web.server;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.logic.MeasurementService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.github.dmtk.web.client.sampleservice.GWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author nbuser
 */
@Configurable
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    @Autowired
    private MeasurementService measurementService;
    private static final long serialVersionUID = -15020842597334403L;
    
    public String myMethod() {
        Measurement m= measurementService.getLast();
        return m.getSensor().getName()+" "+m.getValue();
    }

}
