package com.github.dmtk;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
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
        SensorNode node1 = new SensorNode();
        SensorNode node2 = new SensorNode();
        SensorNode node3 = new SensorNode();
        request.setAttribute("node1", node1);
        HttpSession session = request.getSession();
        session.setAttribute("node2", node2);
        
        ServletContext context = getServletContext();
        context.setAttribute("node3", node3);
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
        
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
