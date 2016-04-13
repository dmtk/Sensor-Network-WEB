package com.github.dmtk.web;

import com.github.dmtk.entity.Measurement;
import com.github.dmtk.logic.NetworkController;
import com.github.dmtk.logic.MeasurementService;
import com.github.dmtk.logic.SensorService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {

    @Autowired
    private NetworkController controller;
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private SensorService sensorService;

    private final Properties menu = new Properties();

    FrontController() {
        menu.setProperty("overview", "Overview");
        menu.setProperty("charts", "Charts");
        menu.setProperty("datalog", "DataLog");
        menu.setProperty("export", "Export");
        menu.setProperty("options", "Options");

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "login");

        return "login";
    }

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "overview");
        request.setAttribute("menu", menu);
        request.setAttribute("measurements", measurementService.getList());
        return "main";
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public String perform2(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "options");
        request.setAttribute("measurements", measurementService.getList());
        request.setAttribute("menu", menu);
        return "main";
    }

    @RequestMapping(value = "/datalog", method = RequestMethod.GET)
    public String perform4(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "datalog");
        request.setAttribute("measurements", measurementService.getList());
        request.setAttribute("menu", menu);
        return "main";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String perform1(HttpServletRequest request, HttpServletResponse response) {

        return "redirect:overview";
    }

    @RequestMapping(value = "/charts", method = RequestMethod.GET)
    public String perform3(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("activePage", "charts");
        request.setAttribute("menu", menu);
        request.setAttribute("sensors", sensorService.getList());
        return "main";
    }

    @RequestMapping(value = "/plot", method = RequestMethod.POST)
    public void perform5(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer nodeId = 1;//by default
        try {
            nodeId = Integer.parseInt(request.getParameter("nodeId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List listEvents = measurementService.getList();

        double[] data = new double[listEvents.size()];
        Iterator it = listEvents.iterator();
        int j = 0;
        while (it.hasNext()) {

            data[j] = ((Measurement) it.next()).getValue();
            j++;
        }

        Map< String, Object> map = new HashMap< String, Object>();
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean isValid = false;
        String temperature;
        isValid = true;
        temperature = controller.getData();
        map.put("temperature", temperature);
        map.put("isValid", isValid);
        write(response, map);

    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));
    }
}
