package com.github.dmtk;

import com.github.dmtk.entity.SensorNodeFacadeLocal;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SettingsAction implements Action{
    
    private SensorNodeFacadeLocal sensorNodeFacade;
    
    public SettingsAction() {
        try {
        
            InitialContext ic = new InitialContext();
            sensorNodeFacade = (SensorNodeFacadeLocal) ic.lookup("java:comp/env/SensorNodeFacade");
            
            
        } catch (NamingException ex) {
            
        }
    }
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("nodes", sensorNodeFacade.findAll());
        return "/jsp/settings.jsp";
    }
}
