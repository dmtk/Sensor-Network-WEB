package com.github.dmtk.action;

import com.github.dmtk.dao.NetworkEventDAOLocal;
import com.github.dmtk.dao.SensorNodeDAOLocal;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OverviewAction implements Action {

    
    private NetworkEventDAOLocal networkEventFacade;
    private SensorNodeDAOLocal sensorNodeFacade;

    
    public OverviewAction() {
        try {
        
            InitialContext ic = new InitialContext();
            networkEventFacade = (NetworkEventDAOLocal) ic.lookup("java:comp/env/NetworkEventFacade");
            sensorNodeFacade = (SensorNodeDAOLocal) ic.lookup("java:comp/env/SensorNodeFacade");
            
            
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