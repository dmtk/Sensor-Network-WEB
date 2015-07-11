package com.github.dmtk;

import com.github.dmtk.entity.NetworkEventFacadeLocal;
import com.github.dmtk.entity.SensorNodeFacadeLocal;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OverviewAction implements Action {

    
    private NetworkEventFacadeLocal networkEventFacade;
    private SensorNodeFacadeLocal sensorNodeFacade;

    
    public OverviewAction() {
        try {
        
            InitialContext ic = new InitialContext();
            networkEventFacade = (NetworkEventFacadeLocal) ic.lookup("java:comp/env/NetworkEventFacade");
            sensorNodeFacade = (SensorNodeFacadeLocal) ic.lookup("java:comp/env/SensorNodeFacade");
            
            
        } catch (NamingException ex) {
            
        }
    }
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        int itemsPerWebPage = 20;
        request.setAttribute("events", networkEventFacade.findByDate(itemsPerWebPage));
        request.setAttribute("nodes", sensorNodeFacade.findAll());
        return "/jsp/overview.jsp";
    }
}