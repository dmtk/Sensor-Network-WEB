package com.github.dmtk;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/update"})
public class UpdateTemperature extends HttpServlet {

    private static ComPort comport;

    @Override
    public void init() {

        Thread myThready;
        comport = new ComPort();
        myThready = new Thread(new Runnable() {
            @Override
            public void run() {
                comport.start("COM3");
            }
        });

        myThready.start();
    }

    public UpdateTemperature() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SensorNode node1 = new SensorNode("node1",1);
        SensorNode node2 = new SensorNode("node2",2);
        SensorNode node3 = new SensorNode("node3",3);
        request.setAttribute("node1", node1);
        HttpSession session = request.getSession();
        session.setAttribute("node", node2);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Object> map = new HashMap<>();
        boolean isValid = false;
        String temperature = request.getParameter("temperature");
        isValid = true;
        temperature = comport.getData();
        map.put("temperature", temperature);
        map.put("isValid", isValid);
        write(response, map);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));
    }

}
