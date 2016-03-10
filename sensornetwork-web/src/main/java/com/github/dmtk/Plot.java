package com.github.dmtk;

import com.github.dmtk.entity.NetworkEvent;
import com.github.dmtk.dao.NetworkEventDAOLocal;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Plot", urlPatterns = {"/plot"})
public class Plot extends HttpServlet {

    @EJB
    private NetworkEventDAOLocal networkEventFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer nodeId = 1;//by default
        try {
            nodeId = Integer.parseInt(request.getParameter("nodeId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List listEvents = networkEventFacade.findById(nodeId, 1000);

        double[] data = new double[listEvents.size()];
        Iterator it = listEvents.iterator();
        int j = 0;
        while (it.hasNext()) {

            data[j] = ((NetworkEvent) it.next()).getValue();
            j++;
        }

        Map< String, Object> map = new HashMap<>();
        boolean isValid = false;
        String[] temperature = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            temperature[i] = String.valueOf(data[i]);
        }
        isValid = true;
        map.put("temperature", data);
        map.put("isValid", isValid);
        
        write(response, map);

    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));
    }

}
