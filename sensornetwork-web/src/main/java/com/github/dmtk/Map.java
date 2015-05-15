package com.github.dmtk;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Map", urlPatterns = {"/map"})
public class Map extends HttpServlet {
    @EJB
    private SensorNodeFacadeLocal sensorNodeFacade;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        SensorNode s = new SensorNode();
        int number =(int) (Math.random()*100);
        s.setNumber(number);
        s.setValue(number/23);
        s.setName("Sensor");
        sensorNodeFacade.create(s);
               
    } catch (Exception ex) {
        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
    }
        request.getRequestDispatcher("jsp/map.jsp").forward(request, response);
    }
}
