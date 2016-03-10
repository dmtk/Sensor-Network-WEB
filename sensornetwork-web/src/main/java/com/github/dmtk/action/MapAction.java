package com.github.dmtk.action;

import com.github.dmtk.dao.NetworkEventDAOLocal;
import com.github.dmtk.dao.SensorNodeDAOLocal;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dmytro
 */
class MapAction implements Action{
    
    private SensorNodeDAOLocal sensorNodeFacade;

    
    public MapAction() {
        try {
        
            InitialContext ic = new InitialContext();
            sensorNodeFacade = (SensorNodeDAOLocal) ic.lookup("java:comp/env/SensorNodeFacade");
            
            
        } catch (NamingException ex) {
            
        }
    }
    
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("nodes", sensorNodeFacade.findAll());
        return "/jsp/map.jsp";
    }
}
